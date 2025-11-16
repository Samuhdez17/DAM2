package com.example.buscaminas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Inicio (
    irMapa: () -> Unit,
) {
    var numCasillas by remember { mutableStateOf("") }
    var numMinas by remember { mutableStateOf("") }

    Column {
        TextField(
            value = "",
            onValueChange = { numCasillas = it },
            placeholder = { Text("Indica numero casillas") },
            label = { },
            singleLine = true,
        )

        Spacer(modifier = Modifier.width(6.dp))

        TextField(
            value = "",
            onValueChange = { numMinas = it },
            placeholder = { Text("Indica numero minas") },
            label = { },
            singleLine = true,
        )

        Spacer(modifier = Modifier.width(6.dp))

        Row {
            Button(
                onClick = { irMapa() },
                enabled = numCasillas.isNotEmpty() && numMinas.isNotEmpty()
            ) {
                Text("Comenzar")
            }
        }

        Spacer(modifier = Modifier.width(6.dp))

        Button(
            onClick = { irMapa() },
            enabled = numCasillas.isNotEmpty() && numMinas.isNotEmpty()
        ) {
            Text("Comenzar")
        }
    }
}