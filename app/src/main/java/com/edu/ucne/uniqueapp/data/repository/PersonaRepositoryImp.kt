package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.UniqueApi
import com.edu.ucne.uniqueapp.data.remote.dto.PersonaDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PersonaRepositoryImp @Inject constructor(
    private val api: UniqueApi

): Persona1Repository {

    override fun getPersonas(): Flow<Resource<List<PersonaDto>>> = flow {
        try {
            emit(Resource.Loading())

            val persona = api.getPersonas()

            emit (Resource.Success(persona))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
    override suspend fun putPersona(id: Int, personaDto: PersonaDto){
        api.putPersonas(id,personaDto)
    }
    override fun getPersonabyId(id: Int) :Flow<Resource<PersonaDto>> = flow {
        try {
            emit(Resource.Loading())

            val persona = api.getPersonasbyId(id)

            emit (Resource.Success(persona))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }


    override suspend fun deletePersonas(id: Int) = api.deletePersonas(id)
}