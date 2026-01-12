package com.example.spotify.app

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.spotify.ui.theme.SpotifyTheme

class Home

@Composable
fun HomePage() {
    Column {
        Text("Cancion 1")
        Text("Cancion 2")
        Text("Cancion 3")
        Text("Cancion 4")
        Text("Cancion 5")
        Text("Cancion 6")
        Text("Cancion 7")

    }
}

@Preview(showBackground = true)
@Composable
fun LogInPreview() {
    SpotifyTheme {
        HomePage()
    }
}