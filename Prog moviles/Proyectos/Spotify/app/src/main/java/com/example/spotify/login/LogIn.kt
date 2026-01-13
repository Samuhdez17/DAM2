package com.example.spotify.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spotify.R
import com.google.firebase.auth.FirebaseAuth

class LogIn

@Composable
fun LogIn(
    auth: FirebaseAuth,
    onBackClick: () -> Unit = {},
    onLogInClick: () -> Unit = {}
) {
    var emailOrUsername by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var msg by remember { mutableStateOf(" ") }
    val circularFont = FontFamily(
        Font(R.font.circular_std_4)
    )

    val textFieldColors = TextFieldDefaults.colors(
        unfocusedContainerColor = Color(0xFF2A2A2A),
        focusedContainerColor = Color(0xFF3A3A3A),
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        cursorColor = Color.White,
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))

        IconButton(
            onClick = { onBackClick() },
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
            value = emailOrUsername,
            onValueChange = {
                emailOrUsername = it
                msg = " "
                            },
            shape = MaterialTheme.shapes.medium,
            colors = textFieldColors,
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
            value = password,
            onValueChange = {
                password = it
                msg = " "
                            },
            shape = MaterialTheme.shapes.medium,
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(
                            id = if (passwordVisible)
                                R.drawable.mostar_psswrd
                            else
                                R.drawable.ocultar_psswrd
                        ),
                        modifier = Modifier.size(25.dp),
                        contentDescription = "Mostrar contraseña",
                        tint = Color.White
                    )
                }
            },
            visualTransformation =
                if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),

        )

        Text(
            text = msg,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 17.sp,
                fontFamily = circularFont,
                color = Color.Red
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 10.dp),
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                auth.signInWithEmailAndPassword(emailOrUsername, password).addOnCompleteListener { task->
                    if (task.isSuccessful) {
                        onLogInClick()
                    }
                    else {
                        msg = "Username or password incorrect"
                    }
                }
            },
            enabled = emailOrUsername.isNotBlank() && password.isNotBlank(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1DB954),
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            ),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = "Log in",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = circularFont,
                    color = Color.DarkGray,
                    fontSize = 16.sp
                )
            )
        }

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(top = 12.dp)
                .height(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White,
            ),
            border = BorderStroke(1.dp, Color.Gray),
            contentPadding = PaddingValues(
                horizontal = 12.dp,
            )
        ) {
            Text(
                text = "Log in without password",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = circularFont,
                    fontSize = 12.sp
                )
            )
        }
    }
}