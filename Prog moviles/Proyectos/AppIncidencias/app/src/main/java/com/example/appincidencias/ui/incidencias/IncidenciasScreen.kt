package com.example.appincidencias.ui.incidencias

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.appincidencias.data.model.Incidencia
import com.example.appincidencias.data.network.JsonIncidenciasApi
import kotlinx.coroutines.launch

class IncidenciasScreen

@Composable
fun PantallPrincipal(
    apiIncidencias: JsonIncidenciasApi,
    onRegistrarIncidenciaClick: () -> Unit = {},
    onVerIncidenciaClick: (incidenciaId: Int) -> Unit = {},
    onLogOut: () -> Unit = {}
) {
    var busqueda by remember { mutableStateOf("") }
    var incidencias by remember { mutableStateOf<List<Incidencia>>(emptyList()) }

    val lanzadorApi = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        lanzadorApi.launch {
            incidencias = apiIncidencias.getIncidencias(busqueda)
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(0.dp, 16.dp)
        ) {
            TextField(
                value = busqueda,
                onValueChange = {
                    busqueda = it
                },
                label = { Text("Buscar incidencia")},
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        lanzadorApi.launch {
                            incidencias = apiIncidencias.getIncidencias(busqueda)
                        }
                    }
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    lanzadorApi.launch {
                        incidencias = apiIncidencias.getIncidencias(busqueda)
                    }
                }
            ) {
                Text(text = "Buscar")
            }

        }

        LazyVerticalGrid(GridCells.Fixed(1)) {
            items(incidencias) { incidencia ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp).clickable {
                            onVerIncidenciaClick(incidencia.id)
                        }
                    ) {
                        Text(
                            text = incidencia.titulo,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
                            Text(
                                text = incidencia.descripcion,
                                style = MaterialTheme.typography.bodyMedium,
                            )

//                        Button(
//                            onClick = {
//                                lanzadorApi.launch {
//                                    val response = jsonPlaceholderApi.deletePost(post.id)
//
//                                    if (response.isSuccessful) {
//                                        Toast.makeText(
//                                            context,
//                                            "Post borrado correctamente",
//                                            Toast.LENGTH_SHORT
//                                        ).show()
//
//                                        posts = jsonPlaceholderApi.getPosts()
//                                    }
//                                }
//                            }
//                        ) {
//                            Text(
//                                text = "Borrar",
//                                modifier = Modifier.width(50.dp)
//                            )
//                        }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = incidencia.estado,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}