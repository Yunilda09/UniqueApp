package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.dto.ClientesDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface Clientes1Repository {
    suspend fun putClientes(id: Int, clientesDto: ClientesDto)
    fun getClienteById(id: Int): Flow<Resource<ClientesDto>>

}