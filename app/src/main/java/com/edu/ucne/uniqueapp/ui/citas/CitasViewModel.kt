package com.edu.ucne.uniqueapp.ui.citas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import com.edu.ucne.uniqueapp.data.remote.dto.ServiciosDto
import com.edu.ucne.uniqueapp.data.repository.CitaRepositoryImp
import com.edu.ucne.uniqueapp.data.repository.ServicioRepositoryImp
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
data class ServicioListState(
    val isLoading: Boolean = false,
    val servicios: List<ServiciosDto> = emptyList(),
    val error: String = ""
)

@HiltViewModel
class CitasViewModel @Inject constructor(
    private val citaRepository: CitaRepositoryImp,
    private val serviciosRepos: ServicioRepositoryImp
):ViewModel() {
    var citaId by mutableStateOf(0)
    var servicioId by mutableStateOf(0)
    var servicios by mutableStateOf("")
    var clienteId by mutableStateOf(0)
    var nombre by mutableStateOf("")
    var apellido by mutableStateOf("")
    var fecha by mutableStateOf("")
    var hora by mutableStateOf("12:00")

    var uiStateServicios = MutableStateFlow(ServicioListState())
        private set
    var uiState = MutableStateFlow(CitasListState())
        private set
    var uiStateCita = MutableStateFlow(CitasState())
        private set


    private fun Limpiar() {
       uiStateCita.update {
           it.copy(isLoading = false, CitaDto(), "")
       }
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
                        it.copy(citas = result.data, isLoading = false)
                    }
                    nombre = nombre
                    apellido = apellido
                    uiStateCita.value.citas!!.fecha.substring(0,10)
                    hora = hora.substring(11,16)
                }
                is Resource.Error -> {
                    uiStateCita.update { it.copy(error = result.message ?: "Error desconocido",
                        isLoading = false) }
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
                        fecha  +"T"+hora+":00.157"
                    )
                )
            }
        }
    fun postCita() {
        viewModelScope.launch {
            citaRepository.postCita(
                 CitaDto(
                    clienteId = 1,
                    servicioId = servicioId,
                    estadoId = 1,
                    nombre = nombre,
                    apellido = apellido,
                    fecha =  fecha +"T"+hora+":00.157"
                )
            )
        }
    }

    fun cancelarCita(id: Int){
        viewModelScope.launch {
            var cita1: CitaDto? = null
            citaRepository.getCitabyId(id).collect {
                when( it ){
                    is Resource.Success ->{
                        cita1 = it.data
                    }
                    else ->{

                    }
                }
            }
            citaRepository.putCita(id, citaDto = CitaDto(
                id, cita1!!.clienteId,cita1!!.servicioId,
                2,cita1!!.nombre,cita1!!.apellido,cita1!!.fecha
            ))
        }
    }
    fun setServicio(servicioId: Int, descripcion: String) {
        this.servicioId = servicioId
        this.servicios = descripcion
    }
    fun guardar(){
        if (citaId == 0){
            postCita()
        }
        else {
            putCita()
        }
    }
   fun obtenerLista(){
        citaRepository.getCitas(clienteId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(citas = result.data ?: emptyList(), isLoading = false)
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
            serviciosRepos.getServicios().onEach { result ->
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
