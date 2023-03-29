package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.dto.EmpleadasDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface Empleadas1Repository {
    fun getEmpleadas(): Flow<Resource<List<EmpleadasDto>>>
    suspend fun putEmpleadas(id: Int, empleadasDto: EmpleadasDto)
    fun getEmpleadasbyId(id: Int): Flow<Resource<EmpleadasDto>>
    suspend fun deleteEmpleadas(id: Int)
}