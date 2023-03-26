package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.dto.NailsDto
import com.edu.ucne.uniqueapp.data.remote.dto.SpaDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface Spa1Repository {
    fun getSpa(): Flow<Resource<List<SpaDto>>>
    suspend fun putSpa(id: Int, spaDto: SpaDto)
    fun getSpabyId(id: Int): Flow<Resource<SpaDto>>
    suspend fun deleteSpa(id: Int)
}