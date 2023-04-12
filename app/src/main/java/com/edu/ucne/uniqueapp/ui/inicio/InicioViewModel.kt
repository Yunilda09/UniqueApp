package com.edu.ucne.uniqueapp.ui.inicio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.uniqueapp.data.remote.dto.Cita
import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import com.edu.ucne.uniqueapp.data.remote.dto.toCita
import com.edu.ucne.uniqueapp.data.repository.CitaRepositoryImp
import com.edu.ucne.uniqueapp.data.repository.ClientesRepositoryImp
import com.edu.ucne.uniqueapp.data.repository.ServicioRepositoryImp
import com.edu.ucne.uniqueapp.data.util.Resource
import com.edu.ucne.uniqueapp.ui.citas.CitasListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Usuario(
    val id: Int = 1,
    val nombre: String = "",
    val apellido: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
    val citasProximas: List<CitaDto> = emptyList(),
    val citaACancelar: CitaDto? = null
)

@HiltViewModel
class InicioViewModel @Inject constructor(
    private val clientesRepos: ClientesRepositoryImp,
    private val servicioRepos: ServicioRepositoryImp,
    private val citasRepos: CitaRepositoryImp
) : ViewModel() {
    var uiState = MutableStateFlow(Usuario())
        private set
    var isLoading = uiState.value.citasProximas.size == 3

    private val citasListState = MutableStateFlow(CitasListUiState())
    val listUiState = citasListState.asStateFlow()



    fun setUsuario(id: Int) {
        clientesRepos.getClienteById(id).onEach { resul ->
            when (resul) {
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }
                is Resource.Success -> {
                    if (resul.data != null) {
                        uiState.update {
                            it.copy(
                                id = id, nombre = resul.data.nombres,
                                apellido = resul.data.apellidos, isLoading = false
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = resul.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
        getCitasProximas()
    }

    fun getCitasProximas() {
        val listaAux: MutableList<Cita> = mutableListOf()
        citasRepos.getCitasProximas(uiState.value.id).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(isLoading = false, citasProximas = result.data ?: emptyList())
                    }
                    result.data?.forEach { citaDto ->
                        var desc by mutableStateOf("")
                        servicioRepos.getServicioById(citaDto.servicioId).collect { resul ->
                            desc = resul.data?.descripcion ?: ""
                        }
                        listaAux.add(
                            citaDto.toCita(
                                desc,""
                            )
                        )
                    }
                }
                is Resource.Error -> {
                    uiState.update {
                        it.copy(
                            error = result.message ?: "Error desconocido",
                            isLoading = false
                        )
                    }
                }
            }
            citasListState.value = citasListState.value.copy(
                citas = listaAux
            )
        }.launchIn(viewModelScope)
    }


    fun cancelarCita(id: Int) {
        citasRepos.getCitaById(id).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(citaACancelar = result.data, isLoading = false)
                    }

                }
                is Resource.Error -> {
                    uiState.update {
                        it.copy(
                            error = result.message ?: "Error desconocido",
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
        if ( uiState.value.citaACancelar!=null) {
            viewModelScope.launch {
                citasRepos.putCita(
                    id, citaDto = CitaDto(
                        citaId = id,
                        clienteId = uiState.value.citaACancelar?.clienteId ?: 0,
                        servicioId = uiState.value.citaACancelar?.servicioId ?: 0,
                        estadoId = 2,
                        nombre = uiState.value.citaACancelar?.nombre ?: "",
                        apellido = uiState.value.citaACancelar?.apellido ?: "",
                        fecha = uiState.value.citaACancelar?.fecha ?: ""
                    )
                )
            }
        }
    }
}