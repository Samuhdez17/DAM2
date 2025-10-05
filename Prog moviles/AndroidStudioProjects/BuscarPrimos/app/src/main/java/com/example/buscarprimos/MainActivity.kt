package com.example.buscarprimos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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

    Column {
        TextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Cantidad") }
        )

        LazyVerticalGrid(
            GridCells.Fixed(3)
        ) {
            items(num) { index ->
                if (esPrimo(index + 1)) {
                    Text(
                        text = "${ index + 1 }",
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Composable
fun esPrimo(index: Int): Boolean {
    if (index <= 1) return false

    var esPrimo = false
    var divisor = 2
    val dividendo = index
    var resultado: Int

    while (divisor < dividendo) {
        resultado = dividendo % divisor

        esPrimo = resultado != 0

        if (!esPrimo) break
        divisor++
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