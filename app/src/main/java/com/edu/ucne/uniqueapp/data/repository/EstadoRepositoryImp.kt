package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.UniqueApi
import com.edu.ucne.uniqueapp.data.remote.dto.EstadoDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EstadoRepositoryImp@Inject constructor(
    private val api: UniqueApi
): EstadoRepository {
    override fun getEstadoById(id: Int): Flow<Resource<EstadoDto>> = flow {
        try {
            emit(Resource.Loading())

            val estado = api.getEstadoById(id)

            emit(Resource.Success(estado))

        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }

    override fun getEstados(): Flow<Resource<List<EstadoDto>>> = flow {
        try {
            emit(Resource.Loading())

            val estados = api.getEstados()

            emit(Resource.Success(estados))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
}