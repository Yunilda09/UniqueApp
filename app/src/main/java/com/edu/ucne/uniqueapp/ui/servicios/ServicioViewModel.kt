package com.edu.ucne.uniqueapp.ui.servicios

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.uniqueapp.data.repository.ServicioRepositoryImp
import com.edu.ucne.uniqueapp.data.util.Resource
import com.edu.ucne.uniqueapp.ui.citas.ServicioListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


class ServicioViewModel @Inject constructor(
    private val servicioRepos: ServicioRepositoryImp
): ViewModel(){
    var servicioId by mutableStateOf("")
    var tipoId by mutableStateOf("")
    var descripcion by mutableStateOf("")
    var costo by mutableStateOf("")

    var uiStateServicios = MutableStateFlow(ServicioListState())
        private set

    fun obtenerLista(){
        servicioRepos.getServicios().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateServicios.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateServicios.update {
                        it.copy(servicios = result.data ?: emptyList(), isLoading = false)
                    }
                }
                is Resource.Error -> {
                    uiStateServicios.update { it.copy(error = result.message ?: "Error desconocido",
                        isLoading = false) }
                }
            }
        }.launchIn(viewModelScope)
    }
    init {
        servicioRepos.getServicios().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateServicios.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateServicios.update {
                        it.copy(servicios = result.data ?: emptyList(), isLoading = false)
                    }
                }
                is Resource.Error -> {
                    uiStateServicios.update { it.copy(error = result.message ?: "Error desconocido",
                        isLoading = false) }
                }
            }
        }.launchIn(viewModelScope)
    }

}
