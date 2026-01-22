package com.example.apirest

import retrofit2.http.GET
import retrofit2.http.Query

interface JsonPlaceholderApi {
    @GET("persona")
    suspend fun getPersona(@Query("nombre") nombre: String): Persona
}