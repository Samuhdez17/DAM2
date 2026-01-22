package com.example.apirest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apirest.ui.theme.ApiRestTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiRestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NombreEdadScreen(
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
fun NombreEdadScreen(modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf<Int?>(0) }
    val coroutineScope = rememberCoroutineScope()

    fun obtenerEdad() {
        coroutineScope.launch {
            try {
                val persona = jsonPlaceholderApi.getPersona(nombre)
                edad = persona.edad
            } catch (e: Exception) {
                edad = null
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Introduce un nombre") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { obtenerEdad() }) {
            Text("Obtener Edad")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("La edad es: $edad")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApiRestTheme {
        NombreEdadScreen(modifier = Modifier.fillMaxSize())
    }
}
