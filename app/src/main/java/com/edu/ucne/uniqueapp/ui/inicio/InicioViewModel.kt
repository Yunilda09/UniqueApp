package com.edu.ucne.uniqueapp.ui.inicio

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import com.edu.ucne.uniqueapp.data.remote.dto.ClientesDto
import com.edu.ucne.uniqueapp.data.repository.CitaRepositoryImp
import com.edu.ucne.uniqueapp.data.repository.ClientesRepositoryImp
import com.edu.ucne.uniqueapp.data.util.Resource
import com.edu.ucne.uniqueapp.ui.citas.CitasState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Usuario(
    val id: Int = 0,
    val nombre: String = "",
    val apellido: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
    val citasProximas: List<CitaDto> = emptyList()
)

@HiltViewModel
class InicioViewModel @Inject constructor(
    private val clientesRepos: ClientesRepositoryImp,
    private val citasRepos: CitaRepositoryImp
) : ViewModel() {
    var uiState = MutableStateFlow(Usuario())
        private set

    fun setUsuario(id: Int) {
        viewModelScope.launch {
            clientesRepos.getClientesbyId(id).onEach { resul ->
                when (resul) {
                    is Resource.Loading -> {
                        uiState.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Resource.Success -> {
                        uiState.update {
                            it.copy(
                                id = id, nombre = resul.data!!.nombre,
                                apellido = resul.data!!.apellido, isLoading = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        uiState.update { it.copy(error = resul.message ?: "Error desconocido") }
                    }

                }

            }

            getCitasProximas()
        }
    }

    private fun getCitasProximas() {
        viewModelScope.launch {
            citasRepos.getCitasProximas(uiState.value.id).onEach { resul ->
                when (resul) {
                    is Resource.Loading -> {
                        uiState.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Resource.Success -> {
                        uiState.update {
                            it.copy(
                                citasProximas = resul.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        uiState.update { it.copy(error = resul.message ?: "Error desconocido") }
                    }

                }

            }
        }
    }

}