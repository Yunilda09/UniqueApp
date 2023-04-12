package com.edu.ucne.uniqueapp.ui.servicios

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.uniqueapp.data.remote.dto.ServiciosDto
import com.edu.ucne.uniqueapp.data.repository.ServicioRepositoryImp
import com.edu.ucne.uniqueapp.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class ServiciosListState(
    val isLoading: Boolean = false,
    val servicios: List<ServiciosDto> = emptyList(),
    val servicioAux: List<ServiciosDto> = emptyList(),
    val error: String = ""
)

data class ServiciosState(
    val isLoading: Boolean = false,
    val servicios: ServiciosDto? = null,
    val error: String = ""
)

@HiltViewModel
class ServicioViewModel @Inject constructor(

    private val servicioRepos: ServicioRepositoryImp
) : ViewModel() {
    var servicioId by mutableStateOf("")
    var tipoId by mutableStateOf("")
    var descripcion by mutableStateOf("")
    var costo by mutableStateOf("")

    var _uiState = MutableStateFlow(ServiciosListState())
        private set
    val uiState = _uiState.asStateFlow()
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
                        it.copy(
                            servicios = result.data ?: ServiciosDto(0, 0, "", 0.0),
                            isLoading = false
                        )
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

    fun cambiarFiltro(id: Int) {
            when (id) {
                0 -> {
                    _uiState.update {
                        it.copy(servicios = _uiState.value.servicioAux)
                    }
                }
                1 -> {
                    _uiState.update {
                        it.copy(servicios = _uiState.value.servicioAux.filter { s -> s.tipoId ==  1 })
                    }
                }
                2 -> {
                    _uiState.update {
                        it.copy(servicios = _uiState.value.servicioAux.filter { s -> s.tipoId ==  2 })
                    }
                }
                3 -> {
                    _uiState.update {
                        it.copy(servicios = _uiState.value.servicioAux.filter { s -> s.tipoId ==  3 })
                    }
                }
            }

    }

    fun obtenerLista() {
        servicioRepos.getServicios().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            servicios = result.data ?: emptyList()
                        )
                    }
                    _uiState.update {
                        it.copy(
                            servicioAux = _uiState.value.servicios, isLoading = false
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            error = result.message ?: "Error desconocido",
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    init {
      obtenerLista()
    }

}
