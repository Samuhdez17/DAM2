package com.example.ejercicio4apirest

data class Tiempo(
    val current: CurrentWeather
)

data class CurrentWeather(
    val temperature_2m: Double,
    val relative_humidity_2m: Double,
    val surface_pressure: Double
)

