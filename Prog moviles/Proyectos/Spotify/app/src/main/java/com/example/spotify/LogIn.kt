package com.example.spotify

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.spotify.ui.theme.SpotifyTheme

@Composable
fun LogIn() {
    var emailOrUsername by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    IconButton(
        onClick = { /*TODO*/ },
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_spoti),
            contentDescription = "Spotify logo"
        )
    }

    Column {
        Text(text = "Email or username")
        TextField(
            value = "",
            onValueChange = { /*TODO*/ },
        )
        Text(text = "Password")
        TextField(
            value = "",
            onValueChange = { /*TODO*/ },
        )

        Button(
            onClick = { /*TODO*/ },
            enabled = emailOrUsername.isNotBlank() && password.isNotBlank(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1DB954),
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            )
        ) {
            Text( text = "Log in" )
        }

        Button(
            onClick = { /*TODO*/ },
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