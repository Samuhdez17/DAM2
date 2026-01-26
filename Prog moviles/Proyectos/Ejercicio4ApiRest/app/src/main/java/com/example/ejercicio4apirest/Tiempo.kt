package com.example.ejercicio4apirest

data class Tiempo(
    val info: List<ListaDatos>
)

data class ListaDatos(
    val lista: Datos
)

data class Datos(
    val temperatura: Double,
    val humedad: Double,
    val presion: Double
)

