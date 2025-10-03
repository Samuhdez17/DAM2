package com.example.buscarprimos

import android.R
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
                if (CalcularPrimo())
                Text(
                    text = "$index",
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun CalcularPrimo(): Boolean {
    return true
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BuscarPrimosTheme {
        CalculoPrimos("Android")
    }
}