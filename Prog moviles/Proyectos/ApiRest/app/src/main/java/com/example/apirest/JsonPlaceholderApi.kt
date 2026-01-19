package com.example.apirest

import retrofit2.http.GET

interface JsonPlaceholderApi {
    @GET("persona")
    suspend fun getPersona(): List<Persona>
}