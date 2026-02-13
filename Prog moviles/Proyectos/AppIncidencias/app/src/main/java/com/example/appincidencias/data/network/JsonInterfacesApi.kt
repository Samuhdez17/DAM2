package com.example.appincidencias.data.network

import com.example.appincidencias.data.model.Incidencia
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonInterfacesApi {
    @GET("incidencias")
    suspend fun getIncidencias(
        @Query("q") busqueda: String?
    ): List<Incidencia>
}