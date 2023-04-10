package com.edu.ucne.uniqueapp.data.remote.dto

data class ClientesDto(
    val clienteId: Int?= null,
    val nombres: String,
    val apellidos: String,
    val telefono: Double,
    val email: String,
    val clave: String
)
