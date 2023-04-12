package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Cita1Repository {
    fun getCitas(id: Int): Flow<Resource<List<CitaDto>>>
    fun getCitasProximas(id: Int): Flow<Resource<List<CitaDto>>>
    fun getCitaById(id: Int): Flow<Resource<CitaDto>>
    suspend fun postCita(citaDto: CitaDto): CitaDto
    suspend fun putCita(id: Int, citaDto: CitaDto)


}