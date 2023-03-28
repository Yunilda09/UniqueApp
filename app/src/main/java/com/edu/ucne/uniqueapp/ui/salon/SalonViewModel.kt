package com.edu.ucne.uniqueapp.ui.salon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.uniqueapp.data.remote.dto.SalonDto
import com.edu.ucne.uniqueapp.data.repository.SalonRepositoryImp
import com.edu.ucne.uniqueapp.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class SalonListState(
    val isLoading: Boolean = false,
    val salon: List<SalonDto> = emptyList(),
    val error: String = ""
)

data class SalonUiState(
    val isLoading: Boolean = false,
    val salon: SalonDto? = null,
    val error: String = ""
)

@HiltViewModel
class SalonViewModel @Inject constructor(
    private val salonRepository: SalonRepositoryImp
) : ViewModel() {
    var salonId by mutableStateOf(0)
    var salonServicio by mutableStateOf("")
    var precio by mutableStateOf("")
    var fecha by mutableStateOf("")
    var horario by mutableStateOf("")

    val opcionessalonServicio = listOf("Color", "Corte", "Secado")

    var uiState = MutableStateFlow(SalonListState())
        private set

    var uiStateSalon = MutableStateFlow(SalonUiState())
        private set

    private fun Limpiar() {
        salonServicio = ""
        precio = ""
        fecha = ""
        horario = ""
    }

    fun setSalon(id: Int) {
        salonId = id
        Limpiar()
        salonRepository.getSalonbyId(salonId).onEach { resultado ->
            when (resultado) {
                is Resource.Loading -> {
                    uiStateSalon.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateSalon.update {
                        it.copy(salon = resultado.data)
                    }
                    salonServicio = uiStateSalon.value.salon!!.salonServicio
                    uiStateSalon.value.salon!!.precio
                    fecha = uiStateSalon.value.salon!!.fecha
                    horario = uiStateSalon.value.salon!!.horario

                }
                is Resource.Error -> {
                    uiStateSalon.update {
                        it.copy(error = resultado.message ?: "Error desconocido")
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
    /* fun putSalon(){
         viewModelScope.launch {
             salonRepository.putSalon(salonId, SalonDto(
                 salonServicio,
                 uiStateSalon.value.salon!!.precio,
                 uiStateSalon.value.salon!!.fecha,
                 horario,
                 salonId = salonId ))
         }
         }*/
    init {
        salonRepository.getSalon().onEach { resultado ->
            when (resultado) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(salon = resultado.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = resultado.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }
}

