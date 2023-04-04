package com.edu.ucne.uniqueapp.data.remote.dto

import java.util.Date

data class CitaDto(
    val citaId: Int= 0,
    val clienteId: Int,
    val servicioId: Int,
    val estadoId: Int,
    val nombre: String,
    val apellido:String,
    val fecha: String,
    val hora: String


)
