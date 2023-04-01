package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.UniqueApi
import com.edu.ucne.uniqueapp.data.remote.dto.ServiciosDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ServicioRepositoryImp @Inject constructor(
    private val api: UniqueApi

): Servicio1Repository {

    override fun getServicios(): Flow<Resource<List<ServiciosDto>>> = flow {
        try {
            emit(Resource.Loading())

            val servicio = api.getServicio()

            emit (Resource.Success(servicio))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
    override suspend fun putServicios(id: Int, serviciosDto: ServiciosDto){
        api.putServicios(id, serviciosDto)
    }
    override fun getServiciobyId(id: Int) : Flow<Resource<ServiciosDto>> = flow {
        try {
            emit(Resource.Loading())

            val servicio = api.getServiciosbyId(id)

            emit (Resource.Success(servicio))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }


    override suspend fun deleteServicios(id: Int) = api.deleteServicios(id)
}
