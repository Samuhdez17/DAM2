package com.example.appincidencias.ui.signup

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.example.appincidencias.R

class SingUp

@Composable
fun SignUp(
    auth: FirebaseAuth,
    onBackClick: () -> Unit = {},
    onSingUpClick: () -> Unit = {}
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var msg by remember { mutableStateOf(" ") }

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
            .fillMaxSize(),
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
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(100.dp))

            Text(
                text = "REGISTRO",
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.Black,
                    fontSize = 18.sp
                ),
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .align(Alignment.CenterVertically),
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Correo o usuario",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Color.Black,
                fontSize = 28.sp
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp),
        )
        TextField(
            value = email,
            onValueChange = {
                email = it
                msg = " "
            },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 16.dp),
            colors = textFieldColors
        )

        Text(
            text = "Contraseña",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Color.Black
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp),
        )
        TextField(
            value = password,
            onValueChange = {
                password = it
                msg = " "
            },
            shape = MaterialTheme.shapes.medium,
            colors = textFieldColors,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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
                fontSize = 15.sp,
                color = Color.Red
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp),
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                if (esContraseniaValida(password))
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful)
                                onSingUpClick()
                            else
                                msg = task.exception?.message.toString()
                        }

                else
                    Toast.makeText(context, "Contraseña mal formada", Toast.LENGTH_SHORT).show()
            },
            enabled = email.isNotBlank() && password.isNotBlank(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1DB954),
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            ),
        ) {
            Text(
                text = "Registrarse",
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.Black,
                    fontSize = 15.sp
                ),
            )
        }
    }
}

fun esContraseniaValida(contrasenia: String): Boolean {
    if (contrasenia.length < 8)
        return false

    if (!Regex("[A-Z]+").containsMatchIn(contrasenia))
        return false

    if (!Regex("[$?.*]+").containsMatchIn(contrasenia))
        return false

    return true
}