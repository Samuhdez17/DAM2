package com.example.appincidencias.ui.incidencias

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appincidencias.R
import com.example.appincidencias.data.model.Incidencia
import com.example.appincidencias.data.network.JsonIncidenciasApi
import kotlinx.coroutines.launch

@Composable
fun NuevaIncidencia(
    apiJson: JsonIncidenciasApi,
    onVolverClick: () -> Unit
) {
    val lanzadorApi = rememberCoroutineScope()

    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var menuExpandido by remember { mutableStateOf(false) }

    val opcionesEstado = listOf("Pendiente", "Bloqueada", "En curso", "Finalizada")

    val textFieldColors = TextFieldDefaults.colors(
        unfocusedContainerColor = Color(0xFF2A2A2A),
        focusedContainerColor = Color(0xFF3A3A3A),
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        cursorColor = Color.White,
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        unfocusedLabelColor = Color(0xFFAAAAAA),
        focusedLabelColor = Color(0xFF1DB954)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(48.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { onVolverClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.flecha_atras),
                    contentDescription = "Volver",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = "Nueva incidencia",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }

        Spacer(Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF1E1E1E))
                .padding(20.dp)
        ) {
            TextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                shape = RoundedCornerShape(12.dp),
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            TextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                shape = RoundedCornerShape(12.dp),
                colors = textFieldColors,
                minLines = 3,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Estado",
                color = Color(0xFFAAAAAA),
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Box {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF2A2A2A))
                        .clickable { menuExpandido = true }
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Text(
                        text = if (estado.isBlank()) "Seleccionar estado" else estado,
                        color = if (estado.isBlank()) Color(0xFFAAAAAA) else Color.White,
                        fontSize = 16.sp
                    )
                }

                DropdownMenu(
                    expanded = menuExpandido,
                    onDismissRequest = { menuExpandido = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF2A2A2A))
                ) {
                    opcionesEstado.forEach { opcion ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = opcion,
                                    color = if (estado == opcion) Color(0xFF1DB954) else Color.White
                                )
                            },
                            onClick = {
                                estado = opcion
                                menuExpandido = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            TextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha") },
                shape = RoundedCornerShape(12.dp),
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                lanzadorApi.launch {
                    apiJson.crearIncidencia(
                        Incidencia(
                            id = 0,
                            titulo = titulo,
                            descripcion = descripcion,
                            estado = estado,
                            fecha = fecha
                        )
                    )
                    onVolverClick()
                }
            },
            enabled = titulo.isNotBlank() && descripcion.isNotBlank() && estado.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1DB954),
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            )
        ) {
            Text("Crear incidencia", fontWeight = FontWeight.SemiBold)
        }
    }
}