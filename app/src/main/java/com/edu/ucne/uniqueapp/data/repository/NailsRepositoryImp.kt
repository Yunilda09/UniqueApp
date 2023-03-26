package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.UniqueApi
import com.edu.ucne.uniqueapp.data.remote.dto.NailsDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NailsRepositoryImp @Inject constructor(
    private val api: UniqueApi

): Nails1Repository {

    override fun getNails(): Flow<Resource<List<NailsDto>>> = flow {
        try {
            emit(Resource.Loading())

            val nails = api.getNails()

            emit (Resource.Success(nails))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
    override suspend fun putNails(id: Int, nailsDto: NailsDto){
        api.putNails(id,nailsDto)
    }
    override fun getNailsbyId(id: Int) : Flow<Resource<NailsDto>> = flow {
        try {
            emit(Resource.Loading())

            val nails = api.getNailsbyId(id)

            emit (Resource.Success(nails))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }


    override suspend fun deleteNails(id: Int) = api.deleteNails(id)
}