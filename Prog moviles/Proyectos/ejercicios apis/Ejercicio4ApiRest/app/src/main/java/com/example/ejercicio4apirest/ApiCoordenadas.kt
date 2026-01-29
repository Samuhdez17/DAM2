package com.example.ejercicio4apirest

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCoordenadas {
    @GET("address")
    suspend fun getCoordenadas(
        @Query("address") address: String,
        @Query("key") key: String = "AIzaSyBVDdDLQ8hSoKaw0_zP4mBUg7j5lGCrPLw"
    ): Ciudad
}