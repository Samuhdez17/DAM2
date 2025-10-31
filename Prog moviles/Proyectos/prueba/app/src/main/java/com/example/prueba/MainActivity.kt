package com.example.prueba

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prueba.ui.theme.PruebaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val array = (0 until 10).map { false }.toMutableStateList()

    Column() {
        LazyVerticalGrid(
            GridCells.Fixed(2),
            modifier = Modifier.padding(5.dp)
        ) {
            items(array.size) { index ->

                if (array[index]) {
                    Text(
                        text = "T",
                        modifier = modifier
                    )
                } else {
                    Text(
                        text = "F",
                        modifier = modifier
                    )
                }
            }
        }

        Button(
            onClick = {
                for (i in 0 until array.size) {
                    array[i] = !array[i]
                }
            }
        ) {
            Text(text = "Cambiar valores")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PruebaTheme {
        Greeting("Android")
    }
}