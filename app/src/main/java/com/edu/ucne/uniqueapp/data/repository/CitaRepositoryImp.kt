package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.UniqueApi
import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CitaRepositoryImp @Inject constructor(
    private val api: UniqueApi
) : Cita1Repository {

    override fun getCitas(id: Int): Flow<Resource<List<CitaDto>>> = flow {
        try {
            emit(Resource.Loading())

            val cita = api.getCitas(id)

            emit(Resource.Success(cita))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
    override suspend fun putCita(id: Int, citaDto: CitaDto) {
        api.putCita(id, citaDto)
    }
    override fun getCitaById(id: Int): Flow<Resource<CitaDto>> = flow {
        try {
            emit(Resource.Loading())

            val cita = api.getCitaById(id)

            emit(Resource.Success(cita))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
    override fun getCitasProximas(id: Int): Flow<Resource<List<CitaDto>>> = flow {
        try {
            emit(Resource.Loading())

            val cita = api.getCitasProxima(id)

            emit(Resource.Success(cita))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
    override suspend fun postCita(citaDto: CitaDto): CitaDto = api.postCita(citaDto)
}