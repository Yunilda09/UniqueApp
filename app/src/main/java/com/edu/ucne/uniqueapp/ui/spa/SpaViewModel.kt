package com.edu.ucne.uniqueapp.ui.spa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.uniqueapp.data.remote.dto.SalonDto
import com.edu.ucne.uniqueapp.data.remote.dto.SpaDto
import com.edu.ucne.uniqueapp.data.repository.SpaRepositoryImp
import com.edu.ucne.uniqueapp.data.util.Resource
import com.edu.ucne.uniqueapp.ui.salon.SalonListState
import com.edu.ucne.uniqueapp.ui.salon.SalonUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SpaListState(
    val isLoading: Boolean = false,
    val spa: List<SpaDto> = emptyList(),
    val error: String = ""
)

data class SpaUiSate(
    val isLoading: Boolean = false,
    val spa: SpaDto? = null,
    val error: String = ""
)
@HiltViewModel
class SpaViewModel @Inject constructor(
    private val spaRepository: SpaRepositoryImp

): ViewModel(){
    var spaId by mutableStateOf(0)
    var spaServicio by mutableStateOf("")
    var precio by mutableStateOf("")
    var fecha by mutableStateOf("")
    var horario by mutableStateOf("")

    val opcionesspaServicio = listOf("Depilacion", "Masaje", "Facial")

    var uiState = MutableStateFlow(SpaListState())
        private set

    var SpaUiSate = MutableStateFlow(SpaUiSate())
        private set

    private fun Limpiar() {
        spaServicio = ""
        precio = ""
        fecha = ""
        horario = ""
    }

    fun setSpa(id: Int){
        spaId = id
        Limpiar()

        spaRepository.getSpabyId(spaId).onEach { resultad ->
            when (resultad) {
                is Resource.Loading -> {
                    SpaUiSate.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    SpaUiSate.update {
                        it.copy(spa = resultad.data)
                    }
                    spaServicio = SpaUiSate.value.spa!!.spaServicio
                    SpaUiSate.value.spa!!.precio


                }
                is Resource.Error -> {
                    SpaUiSate.update {
                        it.copy(error = resultad.message ?: "Error desconocido")
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
     fun putSpa(){
         viewModelScope.launch {
             spaRepository.putSpa(
                 spaId, SpaDto(
                 spaId = spaId,
                 spaServicio,
                 SpaUiSate.value.spa!!.precio,

                  )
             )
         }
         }
    init {
        spaRepository.getSpa().onEach { resultad ->
            when (resultad) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(spa = resultad.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = resultad.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }
}
