package com.edu.ucne.uniqueapp.data.remote
import com.edu.ucne.uniqueapp.data.remote.dto.NailsDto
import com.edu.ucne.uniqueapp.data.remote.dto.PersonaDto
import com.edu.ucne.uniqueapp.data.remote.dto.SalonDto
import com.edu.ucne.uniqueapp.data.remote.dto.SpaDto
import retrofit2.Response
import retrofit2.http.*

interface UniqueApi {
    //Persona
    @GET("/api/Personas")
    suspend fun getPersonas(): List<PersonaDto>
    @GET("/api/Personas/{id}")
    suspend fun getPersonasbyId(@Path("id") id: Int):PersonaDto
    @POST("/api/Personas")
    suspend fun postpersonas(personaDto: PersonaDto)
    @PUT("/api/Personas/{id}")
    suspend fun putPersonas(@Path("id") id: Int, @Body personaDto: PersonaDto):Response<Unit>
    @DELETE("/api/Personas/{id}")
    suspend fun deletePersonas(@Path("id") id: Int)

    //NailsServicios
   @GET ("/api/NailsServicios")
   suspend fun getNails(): List<NailsDto>
   @GET("/api/NailsServicios/{id}")
   suspend fun getNailsbyId(@Path("id") id: Int):NailsDto
   @POST("/api/NailsServicios")
   suspend fun postNails(nailsDto: NailsDto)
   @PUT("/api/NailsServicios/{id}")
   suspend fun putNails(@Path("id") id:Int, @Body nailsDto: NailsDto): Response<Unit>
   @DELETE("/api/NailsServicios/{id}")
   suspend fun deleteNails(@Path("id") id:Int)

   //SpaServicios
   @GET("/api/SpaServicios")
   suspend fun getSpa(): List<SpaDto>
   @GET("/api/SpaServicios/{id}")
   suspend fun getSpabyId(@Path("id") id: Int): SpaDto
   @POST("/api/SpaServicios")
   suspend fun postSpa(nailsDto: NailsDto)
   @PUT("/api/SpaServicios/{id}")
   suspend fun putSpa(@Path("id") id: Int, @Body spaDto: SpaDto): Response<Unit>
   @DELETE("/api/SpaServicios/{id}")
   suspend fun deleteSpa(@Path("id") id: Int)

   //SalonServicios
   @GET("/api/SalonServicios")
   suspend fun getSalon(): List<SalonDto>
   @GET("/api/SalonServicios/{id}")
   suspend fun getSalonbyId(@Path("id") id: Int): SalonDto
   @POST("/api/SalonServicios")
   suspend fun postSalon(salonDto: SalonDto)
   @PUT("/api/SalonServicios")
   suspend fun putSalon(@Path("id") id: Int, @Body salonDto: SalonDto): Response<Unit>
   @DELETE("/api/SalonServicios")
   suspend fun deleteSalon(@Path("id") id: Int)

}
