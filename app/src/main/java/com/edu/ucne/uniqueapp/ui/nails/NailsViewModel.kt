package com.edu.ucne.uniqueapp.ui.nails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.uniqueapp.data.remote.dto.NailsDto
import com.edu.ucne.uniqueapp.data.repository.NailsRepositoryImp
import com.edu.ucne.uniqueapp.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class NailsListState(
    val isLoading: Boolean = false,
    val nails: List<NailsDto> = emptyList(),
    val error: String = ""
)

data class NailsUiState(
    val isLoading: Boolean = false,
    val nail: NailsDto? = null,
    val error: String = ""
)

@HiltViewModel
class NailsViewModel @Inject constructor(
    private val nailsRepository: NailsRepositoryImp

) : ViewModel() {
    var nailsId by mutableStateOf(0)
    var nailsServicio by mutableStateOf("")
    var precio by mutableStateOf("")
    var fecha by mutableStateOf("")
    var horario by mutableStateOf("")


    val opcionesnailsServicio = listOf("Uñas Resina", "Uñas de gel", "Uñas esculpidas")

    var uiState = MutableStateFlow(NailsListState())
        private set

    var uiStateNail = MutableStateFlow(NailsUiState())
        private set

    private fun Limpiar() {
        nailsServicio = ""
        precio = ""
        fecha = ""
        horario = ""
    }

    fun setNail(id: Int) {
        nailsId = id
        Limpiar()

        nailsRepository.getNailsbyId(nailsId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateNail.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateNail.update {
                        it.copy(nail = result.data)
                    }
                    nailsServicio = uiStateNail.value.nail!!.nailsServicio
                    uiStateNail.value.nail!!.precio
                    fecha = uiStateNail.value.nail!!.fecha
                    horario = uiStateNail.value.nail!!.horario

                }
                is Resource.Error -> {
                    uiStateNail.update {
                        it.copy(error = result.message ?: "Error desconocido")
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
    fun putNail() {
        viewModelScope.launch {
            nailsRepository.putNails(
                nailsId, NailsDto(
                    nailsId = nailsId,
                    nailsServicio,
                    uiStateNail.value.nail!!.precio,
                    uiStateNail.value.nail!!.fecha,
                    uiStateNail.value.nail!!.horario,
                )
            )
        }
    }

    init {
        nailsRepository.getNails().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(nails = result.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }
}
