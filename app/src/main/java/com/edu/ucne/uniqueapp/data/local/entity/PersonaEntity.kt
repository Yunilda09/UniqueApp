package com.edu.ucne.uniqueapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edu.ucne.uniqueapp.data.remote.dto.PersonaDto

@Entity(tableName = "Personas")
data class PersonaEntity(
    @PrimaryKey(autoGenerate = true)
    val personaId: Int? = null,
    val nombre: String,
    val apellido: String,
    val telefono: String,

) {

}

fun PersonaEntity.toPersonaDto(): PersonaDto {
    return PersonaDto(
        personaId = this.personaId ?:0,
        nombre = this.nombre,
        apellido = this.apellido,
        telefono = this.telefono
        )
}
