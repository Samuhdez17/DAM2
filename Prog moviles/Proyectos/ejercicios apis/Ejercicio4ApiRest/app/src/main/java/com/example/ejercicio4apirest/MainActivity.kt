package com.example.ejercicio4apirest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.ejercicio4apirest.ui.theme.Ejercicio4ApiRestTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiCoordenadas: ApiCoordenadas =
    Retrofit.Builder()
    .baseUrl("https://geocode.googleapis.com/v4beta/geocode/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiCoordenadas::class.java)

val apiTiempo: ApiTiempo =
    Retrofit.Builder().baseUrl("https://api.open-meteo.com/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiTiempo::class.java)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio4ApiRestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusquedaTiempo(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusquedaTiempo(modifier: Modifier = Modifier) {
    var nombreCiudad by remember { mutableStateOf("") }
    val lanzadorApi = rememberCoroutineScope()

    var datosCiudad by remember { mutableStateOf(Coordenadas(0.0, 0.0)) }
    var datosTiempo by remember { mutableStateOf(CurrentWeather(0.0, 0.0, 0.0)) }


    Column() {
        Spacer(Modifier.height(30.dp))

        Text(
            text = "VER EL TIEMPO",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Ciudad: ",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
            )

            TextField(
                value = nombreCiudad,
                onValueChange = { nombreCiudad = it }
            )
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                lanzadorApi.launch {
                     try {
                         val ciudad = apiCoordenadas.getCoordenadas(nombreCiudad)
                         datosCiudad = ciudad.results[0].location
                    } catch (e: Exception) {
                        Log.e("Error en API", "Error al obtener coordenadas ${e.message}")
                    }

                    val tiempo = apiTiempo.getTiempo(datosCiudad.latitude, datosCiudad.longitude)
                    datosTiempo = tiempo.current
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Buscar")
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Altitud: " + datosCiudad.longitude.toString(),
        )

        Text(
            text = "Latitud: " + datosCiudad.latitude.toString(),
        )

        Text(
            text = "Temperatura: " + datosTiempo.temperature_2m.toString() + "ºC",
        )

        Text(
            text = "Humedad: " + datosTiempo.relative_humidity_2m.toString() + "%",
        )

        Text(
            text = "Presion: " + datosTiempo.temperature_2m.toString() + "hPa",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusquedaTiempoPreview() {
    Ejercicio4ApiRestTheme {
        BusquedaTiempo()
    }
}