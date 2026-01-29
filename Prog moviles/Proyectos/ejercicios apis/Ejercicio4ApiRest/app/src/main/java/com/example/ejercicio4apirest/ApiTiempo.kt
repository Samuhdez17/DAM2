package com.example.ejercicio4apirest

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiTiempo {
    @GET("forecast")
    suspend fun getTiempo(
        @Query("latitude") latitud: Double,
        @Query("longitude") altitud: Double,
        @Query("hourly") momento: String = "temperature_2m,relative_humidity_2m,surface_pressure"
    ): Tiempo
}