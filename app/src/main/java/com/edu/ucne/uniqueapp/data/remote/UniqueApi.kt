package com.edu.ucne.uniqueapp.data.remote
import com.edu.ucne.uniqueapp.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface UniqueApi {
    //Cliente
    @GET("/api/Clientes")
    suspend fun getClientes(): List<ClientesDto>
    @GET("/api/Clientes/{id}")
    suspend fun getClientesbyId(@Path("id") id: Int):ClientesDto
    @POST("/api/Clientes")
    suspend fun postClientes(clientesDto: ClientesDto)
    @PUT("/api/Clientes/{id}")
    suspend fun putClientes(@Path("id") id: Int, @Body clientesDto: ClientesDto):Response<Unit>
    @DELETE("/api/Clientes/{id}")
    suspend fun deleteClientes(@Path("id") id: Int)

    // Empleadas
    @GET("/api/Empleadas")
    suspend fun getEmpleadas(): List<EmpleadasDto>
    @GET("/api/Empleadas/{id}")
    suspend fun getEmpleadasbyId(@Path("id") id: Int):EmpleadasDto
    @POST("/api/Empleadas")
    suspend fun postEmpleadas(empleadasDto: EmpleadasDto)
    @PUT("/api/Empleadas/{id}")
    suspend fun putEmpleadas(@Path("id") id: Int, @Body empleadasDto: EmpleadasDto):Response<Unit>
    @DELETE("/api/Empleadas/{id}")
    suspend fun deleteEmpleadas(@Path("id") id: Int)

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
