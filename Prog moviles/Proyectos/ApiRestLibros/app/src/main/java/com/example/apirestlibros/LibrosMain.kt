package com.example.webservice.libros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val jsonPlaceholderApi: JsonPlaceholderApiLibros =
    Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/books/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JsonPlaceholderApiLibros::class.java)

class LibrosMain: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Screen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun Screen(modifier: Modifier = Modifier) {
    var busqueda by remember { mutableStateOf("") }
    var listaLibros by remember { mutableStateOf<List<BookItem>>(emptyList()) }
    val scope = rememberCoroutineScope()

    var inicio by remember { mutableStateOf(0) }
    val tamanioMax = 10

    Column(
        modifier.padding(20.dp)
    ) {
        Row() {
            Text("Buscar libros: ")
        }

        Row() {
            TextField(
                value = busqueda,
                onValueChange = { busqueda = it },
                placeholder = { Text("Busqueda") }
            )
        }

        Row() {
            Button(
                onClick = {
                    scope.launch {
                        try {
                            inicio = 0
                            val response = jsonPlaceholderApi.getLibros(
                                busqueda = busqueda,
                                startIndex = inicio,
                                maxResults = tamanioMax
                            )
                            listaLibros = response.items ?: emptyList()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            ) {
                Text("Buscar")
            }
        }

        LazyColumn {
            items(listaLibros) { libro ->
                val info = libro.volumeInfo
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "Título: ${info.title}", fontWeight = FontWeight.Bold)
                    Text(text = "Autor: ${info.authors?.joinToString(", ") ?: "Desconocido"}")
                    Text(text = "Año: ${info.publishedDate ?: "Sin año de publicacion"}")
                    Text(
                        text = "Descripción: ${info.description ?: "Sin descripción"}",
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}