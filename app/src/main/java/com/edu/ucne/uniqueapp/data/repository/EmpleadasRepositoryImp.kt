package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.UniqueApi
import com.edu.ucne.uniqueapp.data.remote.dto.EmpleadasDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EmpleadasRepositoryImp @Inject constructor(
    private val api: UniqueApi

) : Empleadas1Repository {

    override fun getEmpleadas(): Flow<Resource<List<EmpleadasDto>>> = flow {
        try {
            emit(Resource.Loading())

            val empleada = api.getEmpleadas()

            emit(Resource.Success(empleada))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }

    override suspend fun putEmpleadas(id: Int, empleadasDto: EmpleadasDto) {
        api.putEmpleadas(id, empleadasDto)
    }

    override fun getEmpleadasbyId(id: Int): Flow<Resource<EmpleadasDto>> = flow {
        try {
            emit(Resource.Loading())

            val empleada = api.getEmpleadasbyId(id)

            emit(Resource.Success(empleada))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }

    override suspend fun deleteEmpleadas(id: Int) = api.deleteEmpleadas(id)
}