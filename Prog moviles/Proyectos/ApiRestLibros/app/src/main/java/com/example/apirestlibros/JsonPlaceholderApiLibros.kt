package com.example.webservice.libros

import retrofit2.http.GET
import retrofit2.http.Query

interface JsonPlaceholderApiLibros{
    @GET("volumes")
    suspend fun getLibros(
        @Query("q") busqueda: String,
        @Query("startIndex") startIndex: Int,
        @Query("maxResults") maxResults: Int = 10 //Limita los resultados que devuelve a 10
    ): Libros
}


