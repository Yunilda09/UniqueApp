package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.dto.ClientesDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface Clientes1Repository {
    fun getClientes(): Flow<Resource<List<ClientesDto>>>
    suspend fun putClientes(id: Int, clientesDto: ClientesDto)
    fun getClientesbyId(id: Int): Flow<Resource<ClientesDto>>
    suspend fun deleteClientes(id: Int)
}