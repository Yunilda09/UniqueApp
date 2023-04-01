package com.edu.ucne.uniqueapp.data.remote.dto

data class ServiciosDto(
    val servicioId: Int?= null,
    val tipoId: Int,
    val descripcion: String,
    val costo: Double,
)
