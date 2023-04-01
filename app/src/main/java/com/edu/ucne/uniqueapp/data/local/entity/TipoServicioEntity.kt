package com.edu.ucne.uniqueapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edu.ucne.uniqueapp.data.remote.dto.TipoServiciosDto

@Entity(tableName = "Tipo Servicio")
data class TipoServicioEntity(
@PrimaryKey(autoGenerate = true)
        val tipoId: Int? = null,
        val tipo: String

) {

}

fun TipoServiciosDto.toTipoServicioDto(): TipoServiciosDto {
    return TipoServiciosDto(
        tipoId = this.tipoId ?:0,
        tipo = this.tipo

    )
}

