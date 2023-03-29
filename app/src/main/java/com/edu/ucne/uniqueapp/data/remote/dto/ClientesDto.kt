package com.edu.ucne.uniqueapp.data.remote.dto

data class ClientesDto(
    val clienteId: Int,
    val nombre: String,
    val apellido: String,
    val telefono: String,
    val fechaCreacion: String,
    val fechaModificacion: String,
    val estatus: String

)
