package com.example.ejercicio4apirest

data class Tiempo(
    val info: Datos
)

data class Datos(
    val temperatura: Double,
    val humedad: Double,
    val presion: Double
)

