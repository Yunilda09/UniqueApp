package com.edu.ucne.uniqueapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edu.ucne.uniqueapp.data.remote.dto.SpaDto

@Entity(tableName = "Personas")
data class SpaEntity(
    @PrimaryKey(autoGenerate = true)
    val spaId: Int? = null,
    val spaServicio: String,
    val precio: String,
    val fecha: String,
    val horario: String,

    ) {

}

fun SpaEntity.toSpaDto(): SpaDto {
    return SpaDto(
        spaId = this.spaId ?:0,
        spaServicio = this.spaServicio,
        precio = this.precio,
        fecha = this.fecha,
        horario = this.horario
    )
}
