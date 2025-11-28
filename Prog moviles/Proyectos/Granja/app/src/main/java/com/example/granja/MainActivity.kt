package com.example.granja

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.granja.ui.theme.GranjaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GranjaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Granja(
                        modifier = Modifier.padding(innerPadding),
                        this
                    )
                }
            }
        }
    }
}

@Composable
fun Granja(modifier: Modifier = Modifier, context: Context) {
    val oveja: MediaPlayer = MediaPlayer.create(context, R.raw.oveja)
    Column() {
        Text(
            text = "Granja",
            modifier = modifier
        )

        Row() {
            IconButton(
                onClick = {
                    oveja.start()
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.oveja),
                    contentDescription = ""
                )

            }
        }
    }
}