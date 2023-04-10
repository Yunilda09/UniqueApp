package com.edu.ucne.uniqueapp.ui.citas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.uniqueapp.data.remote.dto.Cita
import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import com.edu.ucne.uniqueapp.data.remote.dto.ServiciosDto
import com.edu.ucne.uniqueapp.data.remote.dto.toCita
import com.edu.ucne.uniqueapp.data.repository.CitaRepositoryImp
import com.edu.ucne.uniqueapp.data.repository.ClientesRepositoryImp
import com.edu.ucne.uniqueapp.data.repository.EstadoRepositoryImp
import com.edu.ucne.uniqueapp.data.repository.ServicioRepositoryImp
import com.edu.ucne.uniqueapp.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CitasListState(
    val isLoading: Boolean = false,
    val listCitasDto: List<CitaDto> = emptyList(),
    val error: String = ""
)

data class CitasListUiState(
    val citas: List<Cita> = emptyList()
)

data class ClienteUiState(
    val clienteId: Int = 0,
    val nombre: String = "",
    val apellido: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

data class CitasState(
    val isLoading: Boolean = false,
    val cita: CitaDto = CitaDto(),
    val citaACancelar: CitaDto? = null,
    val servicio: String? = null,
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
    private val servicioRepos: ServicioRepositoryImp,
    private val clienteRepos: ClientesRepositoryImp,
    private val estadoRepos: EstadoRepositoryImp,
    private val serviciosRepos: ServicioRepositoryImp,
) : ViewModel() {

    var citaId by mutableStateOf(0)
    private var servicioId by mutableStateOf(0)
    var servicios by mutableStateOf("")
    var servicioError by mutableStateOf("Debe seleccionar un servicio")
    private var clienteId by mutableStateOf(0)
    var estadoId by mutableStateOf(0)
    var nombre by mutableStateOf("")
    var nombreError by mutableStateOf("El nombre debe tener al menos 3 letras")
    var apellido by mutableStateOf("")
    var apellidoError by mutableStateOf("El apellido debe tener al menos 3 letras")
    var fecha by mutableStateOf("")
    var fechaError by mutableStateOf("Debes seleccionar un fecha")
    var hora by mutableStateOf("")
    var horaError by mutableStateOf("Debe seleecionar una hora")

    private val citasListState = MutableStateFlow(CitasListUiState())
    val listUiState = citasListState.asStateFlow()

    var uiStateServicios = MutableStateFlow(ServicioListState())
        private set

    var uiState = MutableStateFlow(CitasListState())
        private set

    private var uiStateCita = MutableStateFlow(CitasState())
        private set

    var uiStateCliente = MutableStateFlow(ClienteUiState())
        private set


    fun setCita(id: Int) {
        estadoId = 0
        if (id == 0)
            return

        citaId = id
        citaRepository.getCitaById(id).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateCita.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateCita.update {
                        it.copy(cita = result.data ?: CitaDto(), isLoading = false)
                    }
                    asignarNombre(uiStateCita.value.cita.nombre)
                    asignarApellido(uiStateCita.value.cita.apellido)
                    servicioId = uiStateCita.value.cita.servicioId
                    buscarServicio(uiStateCita.value.cita.servicioId)
                    estadoId = uiStateCita.value.cita.estadoId
                    asignarFecha(uiStateCita.value.cita.fecha.substring(0, 10))
                    asignarHora(uiStateCita.value.cita.fecha.substring(11, 16))
                }
                is Resource.Error -> {
                    uiStateCita.update {
                        it.copy(
                            error = result.message ?: "Error desconocido",
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)

    }

    private fun buscarServicio(id: Int) {

        servicioRepos.getServicioById(id).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateCita.update {
                        it.copy(isLoading = true)
                    }
                }
                is Resource.Success -> {
                    uiStateCita.update {
                        it.copy(isLoading = false, servicio = result.data!!.descripcion)
                    }
                    servicios = uiStateCita.value.servicio ?: ""
                }
                is Resource.Error -> {
                    uiStateCita.update {
                        it.copy(isLoading = false, error = result.message ?: "Error")
                    }
                }
            }
        }.launchIn(viewModelScope)


    }

    private fun putCita() {
        viewModelScope.launch {
            citaRepository.putCita(
                citaId, CitaDto(
                    citaId = citaId,
                    clienteId = clienteId,
                    servicioId = servicioId,
                    estadoId = 1,
                    nombre = nombre,
                    apellido = apellido,
                    fecha = fecha + "T" + hora + ":00.157"
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
                    fecha = fecha + "T" + hora + ":00.157"
                )
            )
        }
    }

    fun cancelarCita(id: Int) {
        citaRepository.getCitaById(id).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateCita.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateCita.update {
                        it.copy(citaACancelar = result.data, isLoading = false)
                    }

                }
                is Resource.Error -> {
                    uiStateCita.update {
                        it.copy(
                            error = result.message ?: "Error desconocido",
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
        if (uiStateCita.value.citaACancelar != null) {
            viewModelScope.launch {
                citaRepository.putCita(
                    id, citaDto = CitaDto(
                        citaId = uiStateCita.value.citaACancelar?.citaId ?: 0,
                        clienteId = uiStateCita.value.citaACancelar?.clienteId ?: 0,
                        servicioId = uiStateCita.value.citaACancelar?.servicioId ?: 0,
                        estadoId = 2,
                        nombre = uiStateCita.value.citaACancelar?.nombre ?: "",
                        apellido = uiStateCita.value.citaACancelar?.apellido ?: "",
                        fecha = uiStateCita.value.citaACancelar?.fecha ?: ""
                    )
                )
            }
        }
    }

    fun asignarServicio(servicioId: Int, descripcion: String) {
        this.servicioId = servicioId
        this.servicios = descripcion
    }

    fun guardar() {
        if (citaId == 0) {
            postCita()
        } else {
            putCita()
        }
    }

    fun validar() = horaError.isEmpty()
            && fechaError.isEmpty()
            && nombreError.isEmpty()
            && apellidoError.isEmpty()
            && servicioError.isEmpty()
    fun obtenerLista(){
        val listaAux: MutableList<Cita> = mutableListOf()
        citaRepository.getCitas(clienteId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(isLoading = false, listCitasDto = result.data ?: emptyList())
                    }
                    result.data?.forEach { citaDto ->
                        var desc by mutableStateOf("")
                        var est by mutableStateOf("")
                        servicioRepos.getServicioById(citaDto.servicioId).collect { resul ->
                            desc = resul.data?.descripcion ?: ""
                        }
                        estadoRepos.getEstadoById(citaDto.estadoId).collect {
                            est = it.data?.descripcion ?: ""
                        }
                        listaAux.add(
                            citaDto.toCita(
                                desc, est
                            )
                        )
                    }
                }
                is Resource.Error -> {
                    uiState.update {
                        it.copy(
                            error = result.message ?: "Error desconocido",
                            isLoading = false
                        )
                    }
                }
            }
            citasListState.value = citasListState.value.copy(
                citas = listaAux
            )
        }.launchIn(viewModelScope)
    }
    fun  setCliente(clienteId: Int){
        this.clienteId = clienteId
        clienteRepos.getClienteById(clienteId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateCliente.update {
                        it.copy(isLoading = true)
                    }
                }
                is Resource.Success -> {
                    uiStateCliente.update {
                        it.copy(
                            clienteId = result.data?.clienteId ?: 0,
                            nombre = result.data?.nombres ?: "",
                            apellido = result.data?.apellidos ?: "",
                            isLoading = false
                        )
                    }

                }
                is Resource.Error -> {
                    uiStateCliente.update {
                        it.copy(isLoading = false, error = result.message ?: "Error")
                    }

                }
            }
        }.launchIn(viewModelScope)
        obtenerLista()
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
                    uiStateServicios.update {
                        it.copy(
                            error = result.message ?: "Error desconocido",
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
        setCliente(1)
    }

    private fun asignarNombre(input: String) {
        this.nombre = input
        nombreError = if (nombre.length > 2)
            ""
        else
            "El nombre debe tener al menos 3 letras"
    }
    fun asignarApellido(input: String) {
        this.apellido = input
        apellidoError = if (apellido.length > 2)
            ""
        else {
            "El apellido debe tener al menos 3 letras"
        }

    }
    fun asignarServicio(input: String) {
        servicios = input
        servicioError = if (servicioId > 0)
            ""
        else
            "Debe seleccionar un servicio"
    }

    fun asignarFecha(input: String) {
        this.fecha = input
        fechaError = if (fecha.isNotEmpty())
            ""
        else
            "Debe seleccionar una fecha"
    }

    fun asignarHora(input: String) {
        this.hora = input
        horaError = if (hora.isNotEmpty())
            ""
        else {
            "Debe seleccionar una hora"
        }
    }
}

