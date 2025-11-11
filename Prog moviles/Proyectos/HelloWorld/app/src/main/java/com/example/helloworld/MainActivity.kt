package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helloworld.ui.theme.HelloWorldTheme
@Composable
fun Calculadora(modifier: Modifier = Modifier) {
    var texto1 by remember { mutableStateOf("") }
    var texto2 by remember { mutableStateOf("") }
    var operacionCompleta by remember { mutableStateOf("") }

    val num1 = texto1.toDoubleOrNull() ?: 0.0
    val num2 = texto2.toDoubleOrNull() ?: 0.0

    Column {
        Row {
            TextField(
                value = texto1,
                onValueChange = { texto1 = it },
                label = { Text("Primer numero") },
                placeholder = { Text("Escribe el numero") },
                modifier = Modifier
                    .weight(0.5f)
                    .padding(10.dp)
            )

            TextField(
                value = texto2,
                onValueChange = { texto2 = it },
                label = { Text("Segundo numero") },
                placeholder = { Text("Escribe el numero") },
                modifier = Modifier
                    .weight(0.5f)
                    .padding(10.dp)
            )
        }

        Row {
            Button(modifier = Modifier.padding(10.dp), onClick = {
                val resultado = num1 + num2
                operacionCompleta = "$texto1 + $texto2 = $resultado"
            }) {
                Text("Sumar")
            }

            Button(modifier = Modifier.padding(10.dp), onClick = {
                val resultado = num1 - num2
                operacionCompleta = "$texto1 - $texto2 = $resultado"
            }) {
                Text("Restar")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Button(modifier = Modifier.padding(8.dp), onClick = {
                val resultado = num1 * num2
                operacionCompleta = "$texto1 x $texto2 = $resultado"
            }) {
                Text("Multiplicar")
            }

            Button(modifier = Modifier.padding(8.dp), onClick = {
                val resultado = num1 / num2
                operacionCompleta = "$texto1 / $texto2 = $resultado"
                if (num2 == 0.0) operacionCompleta = "No se puede dividir entre 0"
            }) {
                Text("Dividir")
            }

            Button(modifier = Modifier.padding(10.dp), onClick = {
                operacionCompleta = ""
            }) {
                Text("Borrar")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(operacionCompleta)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloWorldTheme {
        Calculadora()
    }
}
