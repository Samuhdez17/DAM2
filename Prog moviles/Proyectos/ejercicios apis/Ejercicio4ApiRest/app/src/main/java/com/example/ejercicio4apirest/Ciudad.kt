package com.example.ejercicio4apirest

data class Ciudad(
    val results: List<Result>
)

data class Result(
    val location: Coordenadas
)

data class Coordenadas(
    val latitude: Double,
    val longitude: Double,
)