package com.example.buscaminas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
                        Inicio(irMapa = { casillas, minas -> navController.navigate("mapa/$casillas/$minas") })
                    }

                    composable("mapa/{numCasillas}/{numBombas}") { backStackEntry ->
                        val casillas = backStackEntry.arguments!!.getString("numCasillas")
                        val bombas = backStackEntry.arguments!!.getString("numBombas")

                        Mapa(
                            numCasillas = casillas!!.toInt(),
                            numBombas = bombas!!.toInt(),
                            { navController.navigate("inicio") },
                            { haGanado,casillas, minas -> navController.navigate("final/$haGanado/$casillas/$minas") }
                        )
                    }

                    composable("final/{haGanado}/{numCasillas}/{numBombas}") { backStackEntry ->
                        val haGanado = backStackEntry.arguments!!.getBoolean("haGanado")
                        val casillas = backStackEntry.arguments!!.getInt("numCasillas")
                        val bombas = backStackEntry.arguments!!.getInt("numBombas")

                        Final(
                            haGanado = haGanado,
                            numCasillas = casillas,
                            numBombas = bombas,
                            { navController.navigate("inicio") },
                            { casillas, minas -> navController.navigate("mapa/$casillas/$minas") }
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
    volverInicio: () -> Unit,
    volverMapa: (Int, Int) -> Unit
) {
    Column {
        Text(
            text = if (haGanado) "¡Enhorabuena, has ganado!" else "Has perdido!"
        )

        Button(
            onClick = { volverInicio() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(75, 75, 75))
        ) {
            Text("Volver al inicio", color = Color.White)
        }

        Button(
            onClick = { volverMapa(numCasillas, numBombas) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(175, 245, 160))
        ) {
            Text("Volver a jugar", color = Color.Gray)
        }
    }
}