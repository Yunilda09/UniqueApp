package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.UniqueApi
import com.edu.ucne.uniqueapp.data.remote.dto.PersonaDto
import com.edu.ucne.uniqueapp.data.remote.dto.SpaDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SpaRepositoryImp @Inject constructor(
    private val api: UniqueApi

): Spa1Repository {

    override fun getSpa(): Flow<Resource<List<SpaDto>>> = flow {
        try {
            emit(Resource.Loading())

            val spa = api.getSpa()

            emit (Resource.Success(spa))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
    override suspend fun putSpa(id: Int, spaDto: SpaDto){
        api.putSpa(id,spaDto)
    }
    override fun getSpabyId(id: Int) : Flow<Resource<SpaDto>> = flow {
        try {
            emit(Resource.Loading())

            val spa = api.getSpabyId(id)

            emit (Resource.Success(spa))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }


    override suspend fun deleteSpa(id: Int) = api.deleteSpa(id)
}