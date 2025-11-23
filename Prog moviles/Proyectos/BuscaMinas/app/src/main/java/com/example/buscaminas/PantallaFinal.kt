package com.example.buscaminas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buscaminas.ui.theme.BuscaMinasTheme

class PantallaFinal : ComponentActivity() {
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
fun Final(
    haGanado: Boolean,
    numCasillas: Int,
    numBombas: Int,
    nombre: String,
    volverInicio: () -> Unit,
    volverMapa: (Int, Int, String) -> Unit
) {
    val context = LocalContext.current
    val dbHandler = remember { DBHandler(context) }

    if (haGanado) dbHandler.aniadirPersona(nombre)

    val ganadores = dbHandler.leerPersonas()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(24.dp)
    ) {
        Text(
            text = if (haGanado) "¡Enhorabuena, $nombre, has ganado!" else "Lo siento, $nombre, has perdido",
            color = if (haGanado) Color.Green else Color.Red,
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text("Lista de ganadores:", fontSize = 18.sp, color = Color.Blue)

        Spacer(modifier = Modifier.height(8.dp))

        Column {
            ganadores.forEach { ganador ->
                Text("• $ganador", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = { volverInicio() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(75, 75, 75))
            ) {
                Text("Volver al inicio", color = Color.White)
            }

            Button(
                onClick = { volverMapa(numCasillas, numBombas, nombre) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784))
            ) {
                Text("Volver a jugar", color = Color.Black)
            }
        }
    }
}
