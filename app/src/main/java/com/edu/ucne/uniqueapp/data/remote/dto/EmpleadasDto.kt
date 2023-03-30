package com.edu.ucne.uniqueapp.data.remote.dto

data class EmpleadasDto(
    val empleadaId: Int? = null,
    val nombre: String,
    val apellido: String,
    val descripcion: String,
    val telefono: String,
    val estatus: String
)
