package com.edu.ucne.uniqueapp.data.remote.dto

import java.util.Date

data class CitaDto(
    val citaId: Int?= null,
    val clienteId: Int,
    val servicioId: Int,
    val nombre: String,
    val apellido:String,
    val fecha: Date,
    val hora: Date


)
