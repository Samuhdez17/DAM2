package com.example.buscaminas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val numBombas = 10
    val casillasRestantes = casillas - numBombas

    var juegoTerminado by remember { mutableStateOf(false) }
    val botonesPulsados = remember { (0 until casillas).map { false }.toMutableStateList() }
    val posBombas = remember { (0 until casillas).map { false }.toMutableStateList() }

    Column {
        Text(
            "BUSCAMINAS",
            modifier = Modifier
                .padding(3.dp)
                .align(Alignment.CenterHorizontally),
            fontSize = (24.sp),
        )

        Button(
            onClick = {
                juegoTerminado = false
                for (i in 0 until casillas) {
                    botonesPulsados[i] = false
                    posBombas[i] = false
                }
            },
            modifier = Modifier
                .padding(6.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color(100, 100, 100))
        ) {
            Text("Reiniciar", color = Color.White)
        }

        var casillasPulsadas = 0
        for (pos in 0 until botonesPulsados.size) {
            if (botonesPulsados[pos]) casillasPulsadas++
        }
        val seHaGanado = casillasPulsadas == casillasRestantes
        if (seHaGanado) juegoTerminado = true

        Text(
            text = when {
                seHaGanado -> "¡Has ganado!"
                juegoTerminado -> "Has perdido"
                casillasPulsadas == 0 -> "Pulsa una casilla para empezar"
                else -> "Casillas restantes: ${casillasRestantes - casillasPulsadas}        Bombas: $numBombas"
            },
            modifier = Modifier
                .padding(3.dp)
                .align(Alignment.CenterHorizontally),
            fontSize = (12.sp),
        )


        LazyVerticalGrid(
            GridCells.Fixed(columnas),
            modifier = Modifier
                .padding(3.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            items(casillas) { index ->
                if (botonesPulsados[index]) {
                    if (posBombas[index]) {
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .aspectRatio(1f)
                                .fillMaxWidth(),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(255, 0, 0)
                            )
                        ) { }

                        juegoTerminado = true
                        for (pos in 0 until posBombas.size) {
                            if (posBombas[pos]) botonesPulsados[pos] = true
                        }


                    } else {
                        val bombasCerca = mirarAlrededor(posBombas, index, columnas)

                        Button(
                            onClick = {},
                            modifier = Modifier
                                .aspectRatio(1f)
                                .fillMaxWidth(),
                            contentPadding = PaddingValues(0.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(255, 255, 255)
                            )
                        ) {
                            when (bombasCerca) {
                                1 -> Text(
                                    text = bombasCerca.toString(),
                                    color = Color(0, 0, 0)
                                )

                                2 -> Text(
                                    text = bombasCerca.toString(),
                                    color = Color(20, 200, 30)
                                )

                                3 -> Text(
                                    text = bombasCerca.toString(),
                                    color = Color(250, 0, 0)
                                )

                                4 -> Text(
                                    text = bombasCerca.toString(),
                                    color = Color(50, 0, 140)
                                )
                            }
                        }
                    }
                } else {
                    Button(
                        onClick = {
                            if (!juegoTerminado) {
                                if (partidaSinEmpezar(botonesPulsados)) {
                                    colocarBombas(
                                        posBombas,
                                        numBombas,
                                        index
                                    )

                                }
                                botonesPulsados[index] = true
                                casillasPulsadas++
                            }

                            val bombasCerca = mirarAlrededor(posBombas, index, columnas)
                            if (bombasCerca == 0 && !posBombas[index]) {
                                desbloquearCeros(index, columnas, posBombas, botonesPulsados)
                            }
                        },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxWidth(),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(53, 104, 45)
                        )
                    ) { }
                }
            }
        }
    }
}

private fun partidaSinEmpezar(
    botonesPulsados: SnapshotStateList<Boolean>,
): Boolean {
    for (pos in 0 until botonesPulsados.size) {
        if (botonesPulsados[pos]) return false
    }

    return true
}

private fun colocarBombas(
    posBombas: SnapshotStateList<Boolean>,
    numBombas: Int,
    index: Int
) {
    var numBombasV = numBombas
    while (numBombasV != 0) {
        var posRandom: Int
        do {
            posRandom = Random.nextInt(posBombas.size)
        } while (posBombas[posRandom] || posRandom == index)
        posBombas[posRandom] = true
        numBombasV--
    }
}

private fun desbloquearCeros(
    index: Int,
    columnas: Int,
    posBombas: SnapshotStateList<Boolean>,
    botonesPulsados: SnapshotStateList<Boolean>,
) {
    val numFilas = posBombas.size / columnas
    val fila = index / columnas
    val col = index % columnas

    for (f in (fila - 1)..(fila + 1)) {
        for (c in (col - 1)..(col + 1)) {
            if (f in 0 until numFilas && c in 0 until columnas) {
                val vecino = f * columnas + c
                if (!posBombas[vecino] && !botonesPulsados[vecino]) {
                    botonesPulsados[vecino] = true
                    val bombasCerca = mirarAlrededor(posBombas, vecino, columnas)
                    if (bombasCerca == 0) {
                        desbloquearCeros(vecino, columnas, posBombas, botonesPulsados)
                    }
                }
            }
        }
    }
}

private fun mirarAlrededor(
    posBombas: SnapshotStateList<Boolean>,
    index: Int,
    columnas: Int
): Int {
    var numMinas = 0

    if (mirarArriba    (posBombas, index, columnas)) numMinas++
    if (mirarAbajo     (posBombas, index, columnas)) numMinas++
    if (mirarIzquierda (posBombas, index, columnas)) numMinas++
    if (mirarDerecha   (posBombas, index, columnas)) numMinas++
    if (mirarArribaIzq (posBombas, index, columnas)) numMinas++
    if (mirarArribaDer (posBombas, index, columnas)) numMinas++
    if (mirarAbajoIzq  (posBombas, index, columnas)) numMinas++
    if (mirarAbajoDer  (posBombas, index, columnas)) numMinas++

    return numMinas
}

private fun mirarAbajoDer(
    posBombas: SnapshotStateList<Boolean>,
    index: Int,
    columnas: Int): Boolean {

    val fila = index / columnas
    val numFilas = posBombas.size / columnas
    val col = index % columnas

    if (fila < numFilas - 1 && col < columnas - 1) {
        val pos = index + columnas + 1
        return posBombas[pos]
    }

    return false
}

private fun mirarAbajoIzq(
    posBombas: SnapshotStateList<Boolean>,
    index: Int,
    columnas: Int): Boolean {

    val fila = index / columnas
    val numFilas = posBombas.size / columnas
    val col = index % columnas

    if (fila < numFilas - 1 && col > 0) {
        val pos = index + columnas - 1
        return posBombas[pos]
    }

    return false
}

private fun mirarArribaDer(
    posBombas: SnapshotStateList<Boolean>,
    index: Int,
    columnas: Int): Boolean {

    val fila = index / columnas
    val col = index % columnas

    if (fila > 0 && col < columnas - 1) {
        val pos = index - columnas + 1
        return posBombas[pos]
    }

    return false
}

private fun mirarArribaIzq(
    posBombas: SnapshotStateList<Boolean>,
    index: Int,
    columnas: Int): Boolean {

    val fila = index / columnas
    val col = index % columnas

    if (fila > 0 && col > 0) {
        val pos = index - columnas - 1
        return posBombas[pos]
    }

    return false
}

private fun mirarDerecha(
    posBombas: SnapshotStateList<Boolean>,
    index: Int,
    columnas: Int
    ): Boolean {

    if ((index + 1) % columnas == 0) return false
    val derecha = index + 1
    return derecha < posBombas.size && posBombas[derecha]
}

private fun mirarIzquierda(
    posBombas: SnapshotStateList<Boolean>,
    index: Int,
    columnas: Int
    ): Boolean {

    if (index % columnas == 0) return false
    val izquierda = index - 1
    return izquierda >= 0 && posBombas[izquierda]
}

private fun mirarAbajo(
    posBombas: SnapshotStateList<Boolean>,
    index: Int,
    columnas: Int): Boolean {

    val abajo = index + columnas
    return abajo < posBombas.size && posBombas[abajo]
}

private fun mirarArriba(
    posBombas: SnapshotStateList<Boolean>,
    index: Int,
    columnas: Int): Boolean {

    val arriba = index - columnas
    return arriba >= 0 && posBombas[arriba]
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mapa()
}
