package com.edu.ucne.uniqueapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import java.util.*

@Entity(tableName = "Citas")
data class CitaEntity(
    @PrimaryKey(autoGenerate = true)
    val citaId: Int? = null,
    val servicioId: Int,
    val clienteId: Int,
    val estadoId: Int,
    val nombre: String,
    val apellido: String,
    val fecha: String,
    val hora: String

    ) {

}

fun CitaEntity.toCitaDto(): CitaDto {
    return CitaDto(
        citaId = this.citaId ?:0,
        servicioId = this.servicioId,
        clienteId = this.clienteId,
        estadoId = this.estadoId,
        nombre = this.nombre,
        apellido = this.apellido,
        fecha = this.fecha,
        hora = this.hora
    )
}
