package com.edu.ucne.uniqueapp.data.repository

import com.edu.ucne.uniqueapp.data.local.entity.NailsEntity
import com.edu.ucne.uniqueapp.data.local.entity.toNailsDto
import com.edu.ucne.uniqueapp.data.remote.UniqueApi
import com.edu.ucne.uniqueapp.data.remote.dto.NailsDto
import javax.inject.Inject

class NailsRepository  @Inject constructor(
        private val uniqueApi: UniqueApi
    ) {
        suspend fun insert(nail: NailsEntity){

            uniqueApi.putNails(nail.nailsId!!, nail.toNailsDto())
        }

        suspend fun putNail(id: Int, nailsDto: NailsDto) = uniqueApi.putNails(id, nailsDto)

    }
