package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.dto.ServiciosDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface Servicio1Repository {
    fun getServicios(): Flow<Resource<List<ServiciosDto>>>
    suspend fun putServicios(id: Int, serviciosDto: ServiciosDto)
    fun getServicioById(id: Int): Flow<Resource<ServiciosDto>>
    suspend fun deleteServicios(id: Int)
}