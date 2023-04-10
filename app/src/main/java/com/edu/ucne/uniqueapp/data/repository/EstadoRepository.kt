package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.dto.EstadoDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface EstadoRepository {
    fun getEstados(): Flow<Resource<List<EstadoDto>>>

    fun getEstadoById(id: Int): Flow<Resource<EstadoDto>>

}