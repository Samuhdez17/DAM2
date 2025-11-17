package com.example.examen_samuel_hernandez

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.examen_samuel_hernandez.ui.theme.EXAMENSAMUEL_HERNANDEZTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EXAMENSAMUEL_HERNANDEZTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val text = remember { mutableStateListOf(0) }
    val listaSeleccionados = remember { (1..99).map { false }.toMutableStateList() }
    var pulsados by remember { mutableStateOf(0) }

    val primerValor = text.joinToString()
    val demasValores = text.joinToString("-")

    Column {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Números seleccionados = ",
                modifier = modifier.padding(16.dp, 8.dp)
            )

            text.sort()
            Text(
                text = if (text[0] == 0 && text.size > 1) {
                    text.remove(0)
                    primerValor
                } else demasValores,
                modifier = modifier.padding(16.dp, 8.dp)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(5)
        ) {
            items(99) { index ->
                if (listaSeleccionados[index]) {
                    Button(
                        onClick = {
                            listaSeleccionados[index] = !listaSeleccionados[index]
                            eliminarNumero(text, index)
                            pulsados--
                        },
                        colors = ButtonDefaults.buttonColors(
                            Color.Cyan,
                            Color.Black,
                        )
                    ) {
                        Text(text = "${ index + 1 }")
                    }

                } else {
                    Button(
                        onClick = {
                            if (pulsados < 10) {
                                listaSeleccionados[index] = !listaSeleccionados[index]
                                text.add((index + 1))
                                pulsados++
                            }
                        }
                    ) {
                        Text(text = "${ index + 1 }")
                    }
                }

            }
        }
    }
}

private fun eliminarNumero(
    text: SnapshotStateList<Int>,
    index: Int
) {
    for (i in 0 until text.size) {
        if (text[i] == index + 1) {
            text.removeAt(i)
            if (text.isEmpty()) text.add(0)
            break
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EXAMENSAMUEL_HERNANDEZTheme {
        Greeting()
    }
}