package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.dto.SalonDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface Salon1Repository {
    fun getSalon(): Flow<Resource<List<SalonDto>>>
    suspend fun putSalon(id: Int, salonDto: SalonDto)
    fun getSalonbyId(id: Int): Flow<Resource<SalonDto>>
    suspend fun deleteSalon(id: Int)
}