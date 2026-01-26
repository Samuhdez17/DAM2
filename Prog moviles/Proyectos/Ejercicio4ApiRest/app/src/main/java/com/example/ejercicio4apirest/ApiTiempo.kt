package com.example.ejercicio4apirest

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiTiempo {
    https://open-meteo.com/en/

    @GET("docs")
    suspend fun getTiempo(
        @Query("hourly") periodo: String = " ",
        @Query("current") momento: String = "temperature_2m,relative_humidity_2m,surface_pressure",
        @Query("longitude") longitud: Double,
        @Query("latitude") latitud: Double
    ): Tiempo
}