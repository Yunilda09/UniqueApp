package com.edu.ucne.uniqueapp.data.remote.dto

import java.util.Date

data class CitaDto(
    val citaId: Int= 0,
    val servicioId: Int = 0,
    val clienteId: Int = 0,
    val estadoId: Int = 0,
    val fecha: String = "",
    val nombre: String = "",
    val apellido:String = "",
)


data class Cita(
    val id:Int,
    val estadoId: Int,
    val cliente: String,
    val fecha: String,
    val hora: String,
    val servicio: String,
    val estado: String
)

fun CitaDto.toCita(servicio:String, estado:String):Cita{
    return(Cita(
        id = this.citaId,
        estadoId = this.estadoId,
        cliente= this.nombre + " " + this.apellido,
        fecha = this.fecha.substring(0,10),
        hora = this.fecha.substring(11,16),
        servicio = servicio,
        estado = estado
    ))
}

