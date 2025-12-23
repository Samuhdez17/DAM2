package com.example.spotify.singup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spotify.initial.Menu
import com.example.spotify.R
import com.example.spotify.login.LogIn
import com.example.spotify.ui.theme.SpotifyTheme

class Registro : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpotifyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "inicio"
                ) {
                    composable("inicio") {
                        Menu(
                            modifier = Modifier,
                            { navController.navigate("logIn") },
                            { navController.navigate("singUp") }
                        )
                    }

                    composable("logIn") {
                        LogIn({ navController.navigate("inicio") })
                    }

                    composable("singUp") {
                        SingUp( { navController.navigate("inicio") } )
                    }
                }
            }
        }
    }
}

@Composable
fun SingUp( onBackClick: () -> Unit = {} ) {
    val circularFont = FontFamily(
        Font(R.font.circular_std_4)
    )

    var email by remember { mutableStateOf("") }

    val textFieldColors = TextFieldDefaults.colors(
        unfocusedContainerColor = Color(0xFF2A2A2A),
        focusedContainerColor = Color(0xFF3A3A3A),
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        cursorColor = Color.White,
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))

        Row(
            modifier = Modifier.align(Alignment.Start)
        ) {
            IconButton(
                onClick = { onBackClick() },
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.flecha_atras),
                    contentDescription = "flecha atras",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(100.dp))

            Text(
                text = "Create account",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = circularFont,
                    color = Color.White,
                    fontSize = 15.sp
                ),
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp).align(Alignment.CenterVertically),
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "What's your email?",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontFamily = circularFont,
                color = Color.White,
                fontSize = 28.sp
            ),
            modifier = Modifier.align(Alignment.Start).padding(start = 16.dp),
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 16.dp),
            colors = textFieldColors
        )

        Text(
            text = "You'll need to confirm this email later.",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontFamily = circularFont,
                color = Color.Gray,
                fontSize = 11.sp
            ),
            modifier = Modifier.align(Alignment.Start).padding(start = 16.dp),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /*TODO*/ },
            enabled = email.isNotBlank(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1DB954),
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            ),
        ) {
            Text(
                text = "Next",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = circularFont,
                    color = Color.Black,
                    fontSize = 15.sp
                ),
            )
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun SingUpPreview() {
    SpotifyTheme {
        SingUp()
    }
}
