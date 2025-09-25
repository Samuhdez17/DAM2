package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helloworld.ui.theme.HelloWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloWorldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "kk",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(
        color = Color.DarkGray,
        contentColor = Color.White,
        modifier = modifier

    ) {
        Text(
            text = "Hello $name!",
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun Calculadora(modifier: Modifier = Modifier) {
    var texto1 by remember { mutableStateOf("") }
    var texto2 by remember { mutableStateOf("") }

    Row() {
        TextField(
            value = texto1,
            onValueChange = { texto1 = it },
            label = { Text("Primer numero") },
            placeholder = { Text("Escribe el primer numero") },
            modifier = Modifier
                .weight(0.5f)
                .padding(10.dp)
        )

        Text(
            "X",modifier = Modifier.padding(horizontal = 8.dp).align(Alignment.CenterVertically)
        )

        TextField(
            value = texto2,
            onValueChange = { texto2 = it },
            label = { Text("Segundo numero") },
            placeholder = { Text("Escribe el segundo numero") },
            modifier = Modifier
                .weight(0.5f)
                .padding(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloWorldTheme {
        Greeting("Doble'S", modifier = Modifier.border(4.dp, Color.Gray))
        Calculadora()
    }
}