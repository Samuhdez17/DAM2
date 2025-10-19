package com.example.buscaminas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buscaminas.ui.theme.BuscaMinasTheme
import kotlin.math.sqrt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuscaMinasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Mapa(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Mapa(name: String, modifier: Modifier = Modifier) {
    var casillas = 100.0
    val columnas:Int = sqrt(casillas).toInt()
    if (casillas.toInt() % columnas != 0) {
        while (casillas.toInt() % columnas != 0) {
            casillas += 1
        }
    }
    var numBombas = 5

    var posBombasFinal = colocarBombas(casillas, numBombas)

    LazyVerticalGrid(
        GridCells.Fixed(columnas),
        modifier = Modifier.padding(3.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
        items(casillas.toInt()) { index ->
            if (posBombasFinal[index]) {
                Button(
                    modifier = Modifier.aspectRatio(1f).fillMaxWidth(),
                    shape = RectangleShape,
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(53, 0, 0)
                    )
                ) { }

            } else {
                Button(
                    modifier = Modifier.aspectRatio(1f).fillMaxWidth(),
                    shape = RectangleShape,
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(53, 104, 45)
                    )
                ) { }
            }
        }
    }
}

@Composable
private fun colocarBombas(
    casillas: Double,
    numBombas: Int
): MutableList<Boolean> {
    var numBombas1 = numBombas
    val posBombas = (0..casillas.toInt()).map { false }.toMutableList()

    while (numBombas1 != 0) {
        val posRandom = Random.nextInt()
        if (!posBombas[posRandom]) {
            posBombas[posRandom] = !posBombas[posRandom]
            numBombas1 -= 1
        }
    }

    return posBombas
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BuscaMinasTheme {
        Mapa("Android")
    }
}