package com.example.webservice.libros


data class Libros( //Lo que devuelve entero.
    val items: List<BookItem>?
)

data class BookItem( //Cada item dentro del array items
    val volumeInfo: VolumeInfo
)

data class VolumeInfo( //Informacion de cad elemento dentro del array items (cada info del libro)
    val title: String,
    val authors: List<String>?,
    val publishedDate: String?,
    val description: String?
)