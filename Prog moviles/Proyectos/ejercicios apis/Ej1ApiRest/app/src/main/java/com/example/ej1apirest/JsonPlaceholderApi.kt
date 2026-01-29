package com.example.ej1apirest

import retrofit2.http.GET

interface JsonPlaceholderApi {
    @GET("persona")
    suspend fun getPersona(): Persona
}