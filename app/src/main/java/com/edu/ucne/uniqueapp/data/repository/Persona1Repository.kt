package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.dto.PersonaDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface Persona1Repository {
    fun getPersonas(): Flow<Resource<List<PersonaDto>>>
    suspend fun putPersona(id: Int, personaDto: PersonaDto)
    fun getPersonabyId(id: Int): Flow<Resource<PersonaDto>>
    suspend fun deletePersonas(id: Int)
}
