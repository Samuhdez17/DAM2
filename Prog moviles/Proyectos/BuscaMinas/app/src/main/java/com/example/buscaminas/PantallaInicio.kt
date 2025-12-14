package com.example.buscaminas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buscaminas.ui.theme.BuscaMinasTheme

class PantallaInicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuscaMinasTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "inicio"
                ) {
                    composable("inicio") {
                        Inicio(irMapa = { casillas, minas, nombre -> navController.navigate("mapa/$casillas/$minas/$nombre") })
                    }

                    composable("mapa/{numCasillas}/{numBombas}/{nombre}") { backStackEntry ->
                        val casillas = backStackEntry.arguments!!.getString("numCasillas")!!.toInt()
                        val bombas = backStackEntry.arguments!!.getString("numBombas")!!.toInt()
                        val nombre = backStackEntry.arguments!!.getString("nombre").toString()

                        Mapa(
                            numCasillas = casillas,
                            numBombas = bombas,
                            nombreUsuario = nombre,
                            { navController.navigate("inicio") },
                            { haGanado,casillas, minas, nombre -> navController.navigate("final/$haGanado/$casillas/$minas/$nombre") }
                        )
                    }

                    composable("final/{haGanado}/{numCasillas}/{numBombas}/{nombre}") { backStackEntry ->
                        val haGanado = backStackEntry.arguments?.getString("haGanado")!!.toBoolean()
                        val casillas = backStackEntry.arguments?.getString("numCasillas")!!.toInt()
                        val bombas = backStackEntry.arguments?.getString("numBombas")!!.toInt()
                        val nombre = backStackEntry.arguments?.getString("nombre").toString()

                        Final(
                            haGanado = haGanado,
                            numCasillas = casillas,
                            numBombas = bombas,
                            nombre = nombre,
                            volverInicio = { navController.navigate("inicio") },
                            volverMapa = { casillas, minas, nombre ->
                                navController.navigate("mapa/$casillas/$minas/$nombre")
                            }
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun Inicio (
    irMapa: ((String, String, String) -> Unit),
) {
    var nombre by remember { mutableStateOf("") }
    var numCasillas by remember { mutableStateOf("") }
    var numMinas by remember { mutableStateOf("") }

    Column {
        Spacer(modifier = Modifier.height(40.dp))

        Card {
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Nombre",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Bottom)
                )
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    placeholder = { Text("Indica tu nombre") },
                    modifier = Modifier.align(Alignment.CenterVertically),
                    label = { },
                    singleLine = true,
                )
            }

            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Número de casillas",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Bottom)
                )
                TextField(
                    value = numCasillas,
                    onValueChange = { numCasillas = it },
                    placeholder = { Text("Indica numero casillas") },
                    modifier = Modifier.align(Alignment.CenterVertically),
                    label = { },
                    singleLine = true,
                )
            }

            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Número de minas",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Bottom)
                )
                TextField(
                    value = numMinas,
                    onValueChange = { numMinas = it },
                    placeholder = { Text("Indica numero minas") },
                    modifier = Modifier.align(Alignment.CenterVertically),
                    label = { },
                    singleLine = true,
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row {
            Text(
                text = "Dificultades: ",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Button(
                onClick = {
                    numCasillas = "9"
                    numMinas = "3"
                },
            ) {
                Text("Pequeño")
            }

            Spacer(modifier = Modifier.width(6.dp))

            Button(
                onClick = {
                    numCasillas = "25"
                    numMinas = "5"
                },
            ) {
                Text("Medio")
            }

            Spacer(modifier = Modifier.width(6.dp))

            Button(
                onClick = {
                    numCasillas = "100"
                    numMinas = "10"
                },
            ) {
                Text("Grande")
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Button(
            onClick = { irMapa(numCasillas, numMinas, nombre) },
            enabled = nombre.isNotEmpty() && numCasillas.isNotEmpty() && numMinas.isNotEmpty()
        ) {
            Text("Comenzar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaInicioPreview() {
    BuscaMinasTheme() {
        Inicio { casillas, minas, nombre -> {} }
    }
}
