package com.edu.ucne.uniqueapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edu.ucne.uniqueapp.data.remote.dto.ClientesDto

@Entity(tableName = "Clientes")
data class ClienteEntity(
    @PrimaryKey(autoGenerate = true)
    val clienteId: Int? = null,
    val nombre: String,
    val apellido: String,
    val telefono: String,
    val fecha: String,
    val horario: String

) {

}

fun ClienteEntity.toClientesDto(): ClientesDto {
    return ClientesDto(
        clienteId = this.clienteId ?: 0,
        nombre = this.nombre,
        apellido = this.apellido,
        telefono = this.telefono,
        fecha = this.fecha,
        horario = this.horario

    )
}
