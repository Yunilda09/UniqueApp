package com.edu.ucne.uniqueapp.ui.cliente

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.uniqueapp.data.remote.dto.ClientesDto
import com.edu.ucne.uniqueapp.data.remote.dto.NailsDto
import com.edu.ucne.uniqueapp.data.repository.ClientesRepositoryImp
import com.edu.ucne.uniqueapp.data.repository.NailsRepositoryImp
import com.edu.ucne.uniqueapp.data.util.Resource
import com.edu.ucne.uniqueapp.ui.nails.NailsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ClienteListState(
    val isLoading: Boolean = false,
    val cliente: List<ClientesDto> = emptyList(),
    val error: String = ""
)

data class ClienteUiState(
    val isLoading: Boolean = false,
    val cliente: ClientesDto? = null,
    val error: String = ""
)

@HiltViewModel
class ClienteViewModel @Inject constructor(
    private val clientesRepositoryImp: ClientesRepositoryImp

) : ViewModel() {
    var clienteId by mutableStateOf(0)
    var nombre by mutableStateOf("")
    var apellido by mutableStateOf("")
    var telefono by mutableStateOf("")
    var fecha by mutableStateOf("")
    var horario by mutableStateOf("")


    var uiState = MutableStateFlow(ClienteListState())
        private set

    var uiStateCliente = MutableStateFlow(ClienteUiState())
        private set

    private fun Limpiar() {
        nombre = ""
        apellido = ""
        telefono=""
        fecha = ""
        horario = ""
    }

    fun setCliente(id: Int) {
        clienteId = id
        Limpiar()

        clientesRepositoryImp.getClientesbyId(clienteId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateCliente.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateCliente.update {
                        it.copy(cliente = result.data)
                    }
                    nombre = nombre
                    apellido = apellido
                    telefono = uiStateCliente.value.cliente!!.telefono
                    fecha = uiStateCliente.value.cliente!!.fecha
                    horario = uiStateCliente.value.cliente!!.horario

                }
                is Resource.Error -> {
                    uiStateCliente.update {
                        it.copy(error = result.message ?: "Error desconocido")
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
    fun putNail() {
        viewModelScope.launch {
            clientesRepositoryImp.putClientes(
                clienteId, ClientesDto(
                    clienteId = clienteId,
                    nombre,
                    apellido,
                    uiStateCliente.value.cliente!!.telefono,
                    uiStateCliente.value.cliente!!.fecha,
                    uiStateCliente.value.cliente!!.horario,
                )
            )
        }
    }

    init {
        clientesRepositoryImp.getClientes().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(cliente = result.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }
}