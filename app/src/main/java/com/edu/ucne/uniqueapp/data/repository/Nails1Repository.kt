package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.remote.dto.NailsDto
import com.edu.ucne.uniqueapp.data.remote.dto.PersonaDto
import com.edu.ucne.uniqueapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface Nails1Repository {
        fun getNails(): Flow<Resource<List<NailsDto>>>
        suspend fun putNails(id: Int, nailsDto: NailsDto)
        fun getNailsbyId(id: Int): Flow<Resource<NailsDto>>
        suspend fun deleteNails(id: Int)

}