package com.edu.ucne.uniqueapp.data.remote.dto

data class ClientesDto(
    val clienteId: Int?= null,
    val nombre: String,
    val apellido: String,
    val telefono: Double,
    val email: String,
    val clave: String
)
