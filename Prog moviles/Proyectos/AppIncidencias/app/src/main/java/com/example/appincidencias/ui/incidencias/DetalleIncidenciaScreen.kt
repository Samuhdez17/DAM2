package com.example.appincidencias.ui.incidencias

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.appincidencias.data.model.Incidencia
import com.example.appincidencias.data.network.JsonIncidenciasApi
import kotlinx.coroutines.launch

class DetalleIncidenciaScreen

@Composable
fun DetalleIncidencia(
    apiIncidencia: JsonIncidenciasApi,
    idIncidencia: Int,
    onVolverClick: () -> Unit
) {
    var incidencia by remember { mutableStateOf<Incidencia?>(null) }
    val lanzadorApi = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        lanzadorApi.launch {
            incidencia = apiIncidencia.getIncidencia(idIncidencia)
        }
    }

    Column() {
        Text(
            text = """
                | ID: ${incidencia?.id}
                | Titulo: ${incidencia?.titulo}
                | Descripcion: ${incidencia?.descripcion}
                | Estado: ${incidencia?.estado}
                | Fecha: ${incidencia?.fecha}
            """.trimMargin()
        )
    }
}