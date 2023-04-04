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

): Cita1Repository {

    override fun getCitas(): Flow<Resource<List<CitaDto>>> = flow {
        try {
            emit(Resource.Loading())

            val cita = api.getCita()

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

    override fun getCitabyId(id: Int): Flow<Resource<CitaDto>> = flow {
        try {
            emit(Resource.Loading())

            val cita = api.getCitabyId(id)

            emit(Resource.Success(cita))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }


    override suspend fun deleteCita(id: Int) = api.deleteCita(id)
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
}