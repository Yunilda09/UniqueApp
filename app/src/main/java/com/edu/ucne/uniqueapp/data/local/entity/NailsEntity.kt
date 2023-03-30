package com.edu.ucne.uniqueapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edu.ucne.uniqueapp.data.remote.dto.NailsDto

@Entity(tableName = "Nails")
data class NailsEntity(
    @PrimaryKey(autoGenerate = true)
    val nailsId: Int? = null,
    val nailsServicio: String,
    val precio: String,

    ) {

}

fun NailsEntity.toNailsDto(): NailsDto {
    return NailsDto(
        nailsId = this.nailsId ?:0,
        nailsServicio = this.nailsServicio,
        precio = this.precio,

    )
}
