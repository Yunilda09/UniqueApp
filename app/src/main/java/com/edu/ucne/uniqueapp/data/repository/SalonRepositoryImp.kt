package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.UniqueApi
import com.edu.ucne.uniqueapp.data.remote.dto.SalonDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SalonRepositoryImp @Inject constructor(
    private val api: UniqueApi

): Salon1Repository {

    override fun getSalon(): Flow<Resource<List<SalonDto>>> = flow {
        try {
            emit(Resource.Loading())

            val salon = api.getSalon()

            emit (Resource.Success(salon))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
    override suspend fun putSalon(id: Int, salonDto: SalonDto){
        api.putSalon(id,salonDto)
    }
    override fun getSalonbyId(id: Int) : Flow<Resource<SalonDto>> = flow {
        try {
            emit(Resource.Loading())

            val salon = api.getSalonbyId(id)

            emit (Resource.Success(salon))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }


    override suspend fun deleteSalon(id: Int) = api.deleteSalon(id)
}