package com.example.appincidencias.ui.incidencias

import androidx.compose.foundation.background
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appincidencias.R
import com.example.appincidencias.data.model.Incidencia
import com.example.appincidencias.data.network.JsonIncidenciasApi
import kotlinx.coroutines.launch

class DetalleIncidenciaScreen

@Composable
fun DetalleIncidencia(
    apiIncidencia: JsonIncidenciasApi,
    idIncidencia: Int,
    borrarIncidencia: JsonIncidenciasApi,
    onVolverClick: () -> Unit
) {
    var incidencia by remember { mutableStateOf<Incidencia?>(null) }
    val lanzadorApi = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        lanzadorApi.launch {
            incidencia = apiIncidencia.getIncidencia(idIncidencia)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))

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
                text = "Detalle de incidencia",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF1E1E1E))
                .padding(20.dp)
        ) {
            Text(
                text = "# ${incidencia?.id}",
                color = Color(0xFF1DB954),
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = incidencia?.titulo ?: "Cargando...",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Color(0xFF333333))
            Spacer(modifier = Modifier.height(16.dp))

            CampoInfo(label = "Descripción", valor = incidencia?.descripcion ?: "—")
            Spacer(modifier = Modifier.height(12.dp))
            CampoInfo(label = "Estado", valor = incidencia?.estado ?: "—")
            Spacer(modifier = Modifier.height(12.dp))
            CampoInfo(label = "Fecha", valor = incidencia?.fecha ?: "—")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón borrar
        Button(
            onClick = {
                lanzadorApi.launch {
                    borrarIncidencia.borrarIncidencia(idIncidencia)
                    onVolverClick()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE5534B).copy(alpha = 0.2f),
                contentColor = Color(0xFFE5534B)
            )
        ) {
            Text(text = "Borrar incidencia", fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun CampoInfo(label: String, valor: String) {
    Text(text = label, color = Color(0xFFAAAAAA), fontSize = 12.sp)
    Text(text = valor, color = Color.White, fontSize = 15.sp)
}