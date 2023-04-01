package com.edu.ucne.uniqueapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edu.ucne.uniqueapp.data.remote.dto.ServiciosDto
import com.edu.ucne.uniqueapp.data.remote.dto.TipoServiciosDto


@Entity(tableName = "Servicios")
data class ServicioEntity(
    @PrimaryKey(autoGenerate = true)
    val servicioId: Int? = null,
    val tipoId: Int,
    val descripcion: String,
    val costo: Double

) {

}
fun ServicioEntity.toServiciosDto(): ServiciosDto {
    return ServiciosDto(
        servicioId = this.servicioId ?: 0,
        tipoId = this.tipoId,
        descripcion = this.descripcion,
        costo = this.costo
    )
}