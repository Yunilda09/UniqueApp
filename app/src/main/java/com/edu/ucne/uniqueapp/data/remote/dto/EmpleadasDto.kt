package com.edu.ucne.uniqueapp.data.remote.dto

data class EmpleadasDto(
    val empleadaId: Int? = null,
    val nombre: String,
    val apellido: String,
    val telefono: String,
    val fechaCreacion: String,
    val fechaModificacion: String,
    val estatus: String
)
