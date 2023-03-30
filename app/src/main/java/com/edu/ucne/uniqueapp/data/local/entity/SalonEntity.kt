package com.edu.ucne.uniqueapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edu.ucne.uniqueapp.data.remote.dto.SalonDto

@Entity(tableName = "Salon")
data class SalonEntity(
@PrimaryKey(autoGenerate = true)
        val salonId: Int? = null,
        val salonServicio: String,
        val precio: String

) {

}

fun SalonEntity.toSalonDto(): SalonDto {
    return SalonDto(
        salonId = this.salonId ?:0,
        salonServicio = this.salonServicio,
        precio = this.precio,

    )
}

