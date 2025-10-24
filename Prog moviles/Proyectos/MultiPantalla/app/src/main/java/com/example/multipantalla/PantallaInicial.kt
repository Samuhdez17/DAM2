package com.example.multipantalla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.multipantalla.ui.theme.MultipantallaTheme

class PantallaInicialActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultipantallaTheme {
                val navController = rememberNavController() // NavController
                NavHost( // NavHost
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") { PantallaInicial("Inicio" , onTerminado = {
                       navController.navigate("final")
                    }) } // NavGraph
                    composable("final") { PantallaFinal("Final") }
                }
            }
        }
    }
}

@Composable
fun PantallaInicial(
    name: String,
    modifier: Modifier = Modifier,
    onTerminado:() -> Unit = {} )
{
    Button(
        onClick = {
            onTerminado()
        //Función cambio de pantalla
        }
    ) {
        Text(
            text = "Cambio de Pantalla",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaInicialPreview() {
    MultipantallaTheme {
        PantallaInicial("Android")
    }
}