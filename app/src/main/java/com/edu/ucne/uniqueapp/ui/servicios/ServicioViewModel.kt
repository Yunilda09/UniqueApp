package com.edu.ucne.uniqueapp.ui.servicios

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import com.edu.ucne.uniqueapp.data.remote.dto.ServiciosDto
import com.edu.ucne.uniqueapp.data.repository.ServicioRepositoryImp
import com.edu.ucne.uniqueapp.data.util.Resource
import com.edu.ucne.uniqueapp.ui.citas.ServicioListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.math.cos

data class ServiciosListState(
    val isLoading: Boolean = false,
    val servicios: List<ServiciosDto> = emptyList(),
    val error: String = ""
)
data class ServiciosState(
    val isLoading: Boolean = false,
    val servicios: ServiciosDto ? =  null,
    val error: String = ""
)
@HiltViewModel
class ServicioViewModel @Inject constructor(

    private val servicioRepos: ServicioRepositoryImp
): ViewModel(){
    var servicioId by mutableStateOf("")
    var tipoId by mutableStateOf("")
    var descripcion by mutableStateOf("")
    var costo by mutableStateOf("")

    var uiState = MutableStateFlow(ServiciosListState())
        private set
    var uiStateServicios = MutableStateFlow(ServiciosState())
        private set

    fun setServicios(id: Int) {
        servicioId = ""
        if (id == 0)
            return

        servicioId = ""
        servicioRepos.getServicioById(id).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateServicios.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateServicios.update {
                        it.copy(servicios = result.data ?:
                        ServiciosDto(0, 0, "", 0.0), isLoading = false)
                    }
                    tipoId = tipoId
                    descripcion = uiStateServicios.value.servicios!!.descripcion
                    costo = costo
                }
                is Resource.Error -> {
                    uiStateServicios.update {
                        it.copy(
                            error = result.message ?: "Error desconocido",
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)

    }
    fun obtenerLista(){
        servicioRepos.getServicios().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(servicios = result.data ?: emptyList(), isLoading = false)
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido",
                        isLoading = false) }
                }
            }
        }.launchIn(viewModelScope)
    }
    init {
        servicioRepos.getServicios().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(servicios = result.data ?: emptyList(), isLoading = false)
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido",
                        isLoading = false) }
                }
            }
        }.launchIn(viewModelScope)
    }

}
