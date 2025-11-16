package com.example.buscarprimos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
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

@Composable
fun CalculoPrimos(modifier: Modifier = Modifier) {
    var texto by remember { mutableStateOf("") }
    val num = texto.toIntOrNull() ?: 0

    var listaNumeros by remember(num) { mutableStateOf((1..num).map { true }.toMutableList()) }

    Column {
        TextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Cantidad") }
        )

        Button(
            onClick = {
                val numeros2 = (1..listaNumeros.size).map { false }.toMutableList()
                for (i in 1..num-1) numeros2[i-1] = esPrimo(i)
                listaNumeros = numeros2
            }
        ) {
            Text("Calcular")
        }

        LazyVerticalGrid(
            GridCells.Fixed(3)
        ) {
            items(listaNumeros.size) { index ->
                if (listaNumeros[index]) {
                    Text(
                        text = "${index+1}",
                        modifier = modifier
                    )
                }
            }
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
        CalculoPrimos()
    }
}