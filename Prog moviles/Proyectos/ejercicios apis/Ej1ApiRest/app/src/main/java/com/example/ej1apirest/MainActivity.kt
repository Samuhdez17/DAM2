package com.example.ej1apirest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ej1apirest.ui.theme.Ej1ApiRestTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Ejercicio1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ej1ApiRestTheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AdivinarEdad(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

private val jsonPlaceholderApi: JsonPlaceholderApi =
    Retrofit.Builder()
        .baseUrl("https://api.agify.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JsonPlaceholderApi::class.java)

@Composable
fun AdivinarEdad(modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf("") }
    val edad by remember { mutableStateOf(0) }

    Column() {
        Row() {
            Text(text = "Nombre: ")
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ej1ApiRestTheme {
        AdivinarEdad()
    }
}