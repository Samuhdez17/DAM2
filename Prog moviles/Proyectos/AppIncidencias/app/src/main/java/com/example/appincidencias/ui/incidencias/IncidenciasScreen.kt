package com.example.appincidencias.ui.incidencias

import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appincidencias.data.model.Incidencia
import com.example.appincidencias.data.network.JsonInterfacesApi
import kotlinx.coroutines.launch

class IncidenciasScreen

@Composable
fun PantallPrincipal(
    apiIncidencias: JsonInterfacesApi,
    onRegistrarIncidenciaClick: () -> Unit = {},
    onVerIncidenciaClick: () -> Unit = {}
) {
    var busqueda by remember { mutableStateOf("") }
    var incidencias by remember { mutableStateOf<List<Incidencia>>(emptyList()) }

    LaunchedEffect(Unit) {
        incidencias = apiIncidencias.getIncidencias(busqueda)
    }

    LazyVerticalGrid (GridCells.Fixed(1)) {
        items(incidencias) { incidencia ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
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