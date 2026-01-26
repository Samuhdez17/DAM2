package com.example.ejercicio4apirest

data class Ciudad(
    val nombre: String,
    val coordenadas: List<ListaCoordenadas>
)

data class ListaCoordenadas(
    val lista: Coordenadas
)

data class Coordenadas(
    val altitud: Double,
    val latitud: Double,
)