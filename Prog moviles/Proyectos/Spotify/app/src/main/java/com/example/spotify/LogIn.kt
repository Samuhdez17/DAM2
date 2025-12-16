package com.example.spotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spotify.ui.theme.SpotifyTheme

class InicioSesion : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpotifyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Menu(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LogIn() {
    var emailOrUsername by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val circularFont = FontFamily(
        Font(R.font.circular_std_4)
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding( bottom = 10.dp).align(Alignment.Start)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.flecha_atras),
                contentDescription = "flecha atras",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = "Email or username",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontFamily = circularFont,
                color = Color.White
            ),
            modifier = Modifier.align(Alignment.Start).padding(start = 16.dp),
        )
        TextField(
            value = "",
            onValueChange = { /*TODO*/ },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        )

        Spacer(modifier = Modifier.height(30.dp))


        Text(
            text = "Password",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontFamily = circularFont,
                color = Color.White
            ),
            modifier = Modifier.align(Alignment.Start).padding(start = 16.dp),
        )
        TextField(
            value = "",
            onValueChange = { /*TODO*/ },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        )

        Button(
            onClick = { /*TODO*/ },
            enabled = emailOrUsername.isNotBlank() && password.isNotBlank(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1DB954),
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            ),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text( text = "Log in" )
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White,
            ),
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Text(text = "Log in without password")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogInPreview() {
    SpotifyTheme {
        LogIn()
    }
}