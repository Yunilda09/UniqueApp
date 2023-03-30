package com.edu.ucne.uniqueapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edu.ucne.uniqueapp.data.remote.dto.EmpleadasDto


@Entity(tableName = "Empleadas")
data class EmpleadasEntity(
    @PrimaryKey(autoGenerate = true)
    val empleadaId: Int? = null,
    val nombre: String,
    val apellido: String,
    val telefono: String,
    val descripcion: String,
    val estatus: String

) {

}
fun EmpleadasEntity.toEmpleadasDto(): EmpleadasDto {
    return EmpleadasDto(
        empleadaId = this.empleadaId ?: 0,
        nombre = this.nombre,
        apellido = this.apellido,
        telefono = this.telefono,
        descripcion = this.telefono,
        estatus = this.estatus
    )
}