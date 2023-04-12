package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.UniqueApi
import com.edu.ucne.uniqueapp.data.remote.dto.ClientesDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ClientesRepositoryImp @Inject constructor(
    private val api: UniqueApi
): Clientes1Repository {

    override suspend fun putClientes(id: Int, clientesDto: ClientesDto) {
        api.putClientes(id, clientesDto)
    }

    override fun getClienteById(id: Int) :Flow<Resource<ClientesDto>> = flow {
        try {
            emit(Resource.Loading())

            val persona = api.getClientesById(id)

            emit (Resource.Success(persona))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }

    override fun getClienteByLogin(email: String, clave: String): Flow<Resource<ClientesDto>> = flow{
        try {
            emit(Resource.Loading())

            val persona = api.getClienteByLogin(email, clave)

            emit (Resource.Success(persona))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
}