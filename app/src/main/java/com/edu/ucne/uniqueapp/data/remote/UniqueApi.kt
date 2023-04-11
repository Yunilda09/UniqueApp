package com.edu.ucne.uniqueapp.data.remote

import com.edu.ucne.uniqueapp.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface UniqueApi {

    /* CLIENTE */
    @GET("/api/Clientes/{id}")
    suspend fun getClientesById(@Path("id") id: Int): ClientesDto

    @GET("/api/Clientes/{email},{clave}")
    suspend fun getClienteByLogin(
        @Path("email") email: String,
        @Path("clave") clave: String
    ): ClientesDto

    @POST("/api/Clientes")
    suspend fun postClientes(@Body clientesDto: ClientesDto)

    @PUT("/api/Clientes/{id}")
    suspend fun putClientes(@Path("id") id: Int, @Body clientesDto: ClientesDto): Response<Unit>


    /* TIPO DE SERVICIOS */
    @GET("/api/TipoServicios")
    suspend fun getTipoServicios(): List<TipoServiciosDto>

    @GET("/api/TipoServicios/{id}")
    suspend fun getTipoServicioById(@Path("id") id: Int): TipoServiciosDto


    /* SERVICIOS */
    @GET("/api/Servicios")
    suspend fun getServicio(): List<ServiciosDto>

    @GET("/api/Servicios/{id}")
    suspend fun getServiciosById(@Path("id") id: Int): ServiciosDto

    @POST("/api/Servicios")
    suspend fun postServicios(serviciosDto: ServiciosDto)

    @PUT("/api/Servicios/{id}")
    suspend fun putServicios(@Path("id") id: Int, @Body serviciosDto: ServiciosDto): Response<Unit>

    @DELETE("/api/Servicios/{id}")
    suspend fun deleteServicios(@Path("id") id: Int)

    /* CITAS */
    @GET("/lista/{id}")
    suspend fun getCitas(@Path("id") id: Int): List<CitaDto>

    @GET("/api/Citas/{id}")
    suspend fun getCitaById(@Path("id") id: Int): CitaDto

    @GET("/api/Citas/Top3CitasByClienteId/{ClienteId}")
    suspend fun getCitasProxima(@Path("ClienteId") ClienteId: Int): List<CitaDto>

    @POST("/api/Citas")
    suspend fun postCita(@Body citaDto: CitaDto): CitaDto

    @PUT("/api/Citas/{id}")
    suspend fun putCita(@Path("id") id: Int, @Body citaDto: CitaDto): Response<Unit>

    /* ESTADOS */
    @GET("/api/Estadoes")
    suspend fun getEstados() : List<EstadoDto>
    @GET("/api/Estadoes/{id}")
    suspend fun getEstadoById(@Path("id") id:Int) : EstadoDto

}
