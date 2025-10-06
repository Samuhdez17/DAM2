package com.example.buscarprimos

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
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
import com.example.buscarprimos.ui.theme.BuscarPrimosTheme
import kotlin.collections.map
import kotlin.collections.toMutableList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuscarPrimosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculoPrimos(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CalculoPrimos(name: String, modifier: Modifier = Modifier) {
    var texto by remember { mutableStateOf("") }
    val num = texto.toIntOrNull() ?: 0

    var listaNumeros by remember(num) { mutableStateOf((0..num).map { true }.toMutableList()) }

    Column {
        TextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Cantidad") }
        )

        LazyVerticalGrid(
            GridCells.Fixed(3)
        ) {
            items(listaNumeros.size) { index ->
                var numero = (index + 1)
                if (listaNumeros[index]) {
                    Text(
                        text = "$numero",
                        modifier = modifier
                    )
                }
            }
        }

        Button(
            onClick = {
                val numeros2 = (0..listaNumeros.size).map { true }.toMutableList()
                for (i in 0..num) numeros2[i] = esPrimo(i)
                listaNumeros = numeros2
            }
        ) {
            Text("Calcular")
        }
    }
}

fun esPrimo(index: Int): Boolean {
    if (index < 2) return false

    var esPrimo = true

    for (divisor in 2..(index-1)) {
        if (index % divisor == 0) {
            esPrimo = false
            break
        }
    }

    return esPrimo
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BuscarPrimosTheme {
        CalculoPrimos("Android")
    }
}