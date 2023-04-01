package com.edu.ucne.uniqueapp.data.repository


import com.edu.ucne.uniqueapp.data.remote.dto.TipoServiciosDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface TipoServicio1Repository {
    fun getTipoServicios(): Flow<Resource<List<TipoServiciosDto>>>
    suspend fun putTipoServicios(id: Int, tipoServiciosDto: TipoServiciosDto)
    fun getTipoServiciobyId(id: Int): Flow<Resource<TipoServiciosDto>>
    suspend fun deleteTipoServicios(id: Int)
}