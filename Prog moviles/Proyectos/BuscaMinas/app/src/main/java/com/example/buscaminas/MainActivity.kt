package com.example.buscaminas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt
import kotlin.random.Random


@Composable
fun Mapa() {
    // Se indican las casillas que se quieren y se hace una una figura cuadrada mediante la raíz
    // cuadrada del número de casillas, si no cuadra se añaden casillas hasta que cuadre
    var casillas = 100
    val columnas:Int = sqrt(casillas.toDouble()).toInt()
    if (casillas % columnas != 0) {
        while (casillas % columnas != 0) {
        casillas += 1
        }
    }

    var botonesPulsados = (0..casillas).map { false }.toMutableStateList()
    var posBombas = (0..casillas).map { false }.toMutableStateList()

    LazyVerticalGrid(
    GridCells.Fixed(columnas),
        modifier = Modifier.padding(3.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(casillas) { index ->
            if (botonesPulsados[index]) {
                Button(
                    onClick = {},
                    modifier = Modifier.aspectRatio(1f).fillMaxWidth(),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(255, 255, 255)
                    )
                ) { }
            } else {
                Button(
                    onClick = {
                        if (partidaSinEmpezar()) colocarBombas()
                        botonesPulsados[index] = true
                        },
                    modifier = Modifier.aspectRatio(1f).fillMaxWidth(),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(53, 104, 45)
                    )
                ) { }
            }
//            if (posBombas[index]) {
//                Button(
//                    modifier = Modifier.aspectRatio(1f).fillMaxWidth(),
//                    shape = RectangleShape,
//                    onClick = {},
//                    colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(53, 0, 0)
//                    )
//                ) { }
//
//            }
            //else {
//                Button(
//                     modifier = Modifier.aspectRatio(1f).fillMaxWidth(),
//                     shape = RectangleShape,
//                     onClick = {},
//                     colors = ButtonDefaults.buttonColors(
//                     containerColor = Color(53, 104, 45)
//                     )
//                ) { }
//            }
        }
    }
}

private fun partidaSinEmpezar(): Boolean {
    for (pos in 0 until botonesPulsados.size) {
        if (botonesPulsados[pos]) return false
    }

    return true
}

private fun colocarBombas() {
    var numBombas = 10

    while (numBombas != 0) {
        var posRandom: Int
        do {
            posRandom = Random.nextInt(posBombas.size - 1)
        } while (posBombas[posRandom])
        posBombas[posRandom] = true
        numBombas--
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mapa()
}