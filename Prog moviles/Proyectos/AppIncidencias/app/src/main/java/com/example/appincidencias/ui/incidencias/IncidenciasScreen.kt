package com.example.appincidencias.ui.incidencias

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appincidencias.R
import com.example.appincidencias.data.model.Incidencia
import com.example.appincidencias.data.network.JsonIncidenciasApi
import kotlinx.coroutines.launch

class IncidenciasScreen

@Composable
fun PantallPrincipal(
    apiJson: JsonIncidenciasApi,
    onRegistrarIncidenciaClick: () -> Unit = {},
    onVerIncidenciaClick: (incidenciaId: Int) -> Unit = {},
    onLogOut: () -> Unit = {}
) {
    var busqueda by remember { mutableStateOf("") }
    var incidencias by remember { mutableStateOf<List<Incidencia>>(emptyList()) }

    val lanzadorApi = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        lanzadorApi.launch {
            incidencias = apiJson.getIncidencias(busqueda)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Incidencias",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Cerrar sesión",
                color = Color(0xFFAAAAAA),
                fontSize = 12.sp,
                modifier = Modifier.clickable { onLogOut() }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Barra de búsqueda + botón
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { onRegistrarIncidenciaClick() },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.nueva),
                    contentDescription = "Añadir incidencia",
                )
            }

            TextField(
                value = busqueda,
                onValueChange = { busqueda = it },
                placeholder = { Text("Buscar incidencia", color = Color(0xFFAAAAAA)) },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFF2A2A2A),
                    focusedContainerColor = Color(0xFF3A3A3A),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(
                    onSend = {
                        lanzadorApi.launch {
                            incidencias = apiJson.getIncidencias(busqueda)
                        }
                    }
                ),
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    lanzadorApi.launch {
                        incidencias = apiJson.getIncidencias(busqueda)
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1DB954),
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Buscar", fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de incidencias
        LazyVerticalGrid(GridCells.Fixed(1)) {
            items(incidencias) { incidencia ->
                var imagen by remember { mutableStateOf(R.drawable.pendiente) }
                var color by remember { mutableStateOf(Color(0xFFFFC107)) }

                when(incidencia.estado.lowercase()) {
                    "pendiente" -> {
                        imagen = R.drawable.pendiente
                        color = Color(0xFFFFC107)
                    }
                    "en curso" -> {
                        imagen = R.drawable.curso
                        color = Color(0xFFFFFF00)
                    }

                    "bloqueada" -> {
                        imagen = R.drawable.bloqueada
                        color = Color(0xFFFF5252)
                    }

                    "finalizada" -> {
                        imagen = R.drawable.finalizada
                        color = Color(0xFF1DB954)
                    }

                    else -> {
                        imagen = R.drawable.pendiente
                        color = Color(0xFFFFC107)
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF1E1E1E))
                        .clickable { onVerIncidenciaClick(incidencia.id) }
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = imagen),
                            contentDescription = incidencia.estado,
                            modifier = Modifier.padding(end = 8.dp)
                        )

                        Column {
                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = incidencia.titulo,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )

                            Text(
                                text = incidencia.descripcion,
                                color = Color(0xFFAAAAAA),
                                fontSize = 13.sp,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(modifier = Modifier.height(10.dp))
                            HorizontalDivider(color = Color(0xFF333333))
                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = incidencia.estado,
                                color = color,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}