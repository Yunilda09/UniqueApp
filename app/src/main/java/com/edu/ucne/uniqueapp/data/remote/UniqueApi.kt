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
    @GET("/api/Clientes/{email},{clave}")
    suspend fun getClienteByLogin(@Path("email") email: String, @Path("clave") clave: String): ClientesDto
    @POST("/api/Clientes")
    suspend fun postClientes(@Body clientesDto: ClientesDto)
    @PUT("/api/Clientes/{id}")
    suspend fun putClientes(@Path("id") id: Int, @Body clientesDto: ClientesDto):Response<Unit>
    @DELETE("/api/Clientes/{id}")
    suspend fun deleteClientes(@Path("id") id: Int)

    // TipoServicio
    @GET("/api/TipoServicios")
    suspend fun getTipoServicios(): List<TipoServiciosDto>
    @GET("/api/TipoServicios/{id}")
    suspend fun getTipoServiciobyId(@Path("id") id: Int):TipoServiciosDto
    @POST("/api/TipoServicios")
    suspend fun postTipoServicios(tipoServiciosDto: TipoServiciosDto)
    @PUT("/api/TipoServicios/{id}")
    suspend fun putTipoServicios(@Path("id") id: Int, @Body tipoServiciosDto: TipoServiciosDto):Response<Unit>
    @DELETE("/api/TipoServicios/{id}")
    suspend fun deleteTipoServicios(@Path("id") id: Int)

    //Servicios
   @GET ("/api/Servicios")
   suspend fun getServicio(): List<ServiciosDto>
   @GET("/api/Servicios/{id}")
   suspend fun getServiciosbyId(@Path("id") id: Int):ServiciosDto
   @POST("/api/Servicios")
   suspend fun postServicios( serviciosDto: ServiciosDto)
   @PUT("/api/Servicios/{id}")
   suspend fun putServicios(@Path("id") id:Int, @Body serviciosDto: ServiciosDto): Response<Unit>
   @DELETE("/api/Servicios/{id}")
   suspend fun deleteServicios(@Path("id") id:Int)

   //Cita
   @GET("/api/Cita/lista/{id}")
   suspend fun getCita(@Path ("id") id: Int): List<CitaDto>
   @GET("/api/Cita/{id}")
   suspend fun getCitabyId(@Path("id") id: Int): CitaDto
   @GET("/api/Citas/Top3CitasByClienteId/{ClienteId}")
   suspend fun getCitasProxima(@Path("ClienteId") clienteId: Int): List<CitaDto>
   @POST("/api/Cita")
   suspend fun postCita(@Body citaDto: CitaDto): Response<CitaDto>
   @PUT("/api/Cita/{id}")
   suspend fun putCita(@Path("id") id: Int, @Body citaDto: CitaDto): Response<CitaDto>
   @DELETE("/api/Cita/{id}")
   suspend fun deleteCita(@Path("id") id: Int)


}
