package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface Cita1Repository {
    fun getCitas(): Flow<Resource<List<CitaDto>>>
    suspend fun putCita(id: Int, citaDto: CitaDto)
    fun getCitabyId(id: Int): Flow<Resource<CitaDto>>
    suspend fun deleteCita(id: Int)
    fun getCitasProximas(id: Int ): Flow<Resource<List<CitaDto>>>
}