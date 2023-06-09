package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.UniqueApi
import com.edu.ucne.uniqueapp.data.remote.dto.TipoServiciosDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TipoServicioRepositoryImp @Inject constructor(
    private val api: UniqueApi

): TipoServicio1Repository {

    override fun getTipoServicios(): Flow<Resource<List<TipoServiciosDto>>> = flow {
        try {
            emit(Resource.Loading())

            val tipoServicio = api.getTipoServicios()

            emit(Resource.Success(tipoServicio))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }

    override fun getTipoServicioById(id: Int): Flow<Resource<TipoServiciosDto>> = flow {
        try {
            emit(Resource.Loading())

            val tipoServicio = api.getTipoServicioById(id)

            emit(Resource.Success(tipoServicio))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
}
