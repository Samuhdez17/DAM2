package com.example.appincidencias.data.network

import com.example.appincidencias.data.model.Incidencia
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonIncidenciasApi {
    @GET("incidencias")
    suspend fun getIncidencias(
        @Query("q") busqueda: String?
    ): List<Incidencia>

    @GET("incidencias/{id}")
    suspend fun getIncidencia(
        @Path("id") id: Int
    ): Incidencia

    @POST("incidencias")
    suspend fun crearIncidencia(@Body incidencia: Incidencia): Incidencia

    @DELETE("incidencias/{id}")
    suspend fun borrarIncidencia(
        @Path("id") id: Int
    ): Response<Unit>
}