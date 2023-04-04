package com.edu.ucne.uniqueapp.ui.citas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import com.edu.ucne.uniqueapp.data.repository.CitaRepositoryImp
import com.edu.ucne.uniqueapp.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

data class CitasListState(
    val isLoading: Boolean = false,
    val citas: List<CitaDto> = emptyList(),
    val error: String = ""
)
 data class CitasState(
     val isLoading: Boolean = false,
     val citas: CitaDto? = null,
     val error: String = ""
 )

@HiltViewModel
class CitasViewModel @Inject constructor(
    private val citaRepository: CitaRepositoryImp
):ViewModel() {
    var citaId by mutableStateOf(0)
    var servicioId by mutableStateOf(0)
    var servicios by mutableStateOf("")
    var clienteId by mutableStateOf(0)
    var nombre by mutableStateOf("")
    var apellido by mutableStateOf("")
    var fecha by mutableStateOf("")
    var hora by mutableStateOf("")

    val opcionesServicios = listOf("Spa", "Salon", "Nails")
    var uiState = MutableStateFlow(CitasListState())
        private set
    var uiStateCita = MutableStateFlow(CitasState())
        private set

    private fun Limpiar() {
        nombre = ""
        apellido = ""
        fecha = ""
        hora = ""
    }

    fun setCita(id: Int) {
        citaId = id
        Limpiar()
        citaRepository.getCitabyId(citaId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateCita.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateCita.update {
                        it.copy(citas = result.data)
                    }
                    nombre = nombre
                    apellido = apellido
                    uiStateCita.value.citas!!.fecha
                    uiStateCita.value.citas!!.hora
                }
                is Resource.Error -> {
                    uiStateCita.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }
        fun putCita() {
            viewModelScope.launch {
                citaRepository.putCita(
                    citaId, CitaDto(
                        citaId = citaId,
                        clienteId = clienteId,
                        servicioId = servicioId,
                        estadoId = 1,
                        nombre,
                        apellido,
                        fecha,
                       hora,
                    )
                )
            }
        }
        init {
            citaRepository.getCitas().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        uiState.update {
                            it.copy(citas = result.data ?: emptyList())
                        }
                    }
                    is Resource.Error -> {
                        uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
