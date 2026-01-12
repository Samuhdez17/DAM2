package com.example.spotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spotify.initial.Principal
import com.example.spotify.login.LogIn
import com.example.spotify.singup.SingUp
import com.example.spotify.app.HomePage
import com.example.spotify.ui.theme.SpotifyTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

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
                        Principal(
                            modifier = Modifier,
                            auth,
                            { navController.navigate("logIn") },
                            { navController.navigate("singUp") }
                        )
                    }

                    composable("logIn") {
                        LogIn(
                            auth,
                            { navController.navigate("inicio") },
                            { navController.navigate("homePage") }
                        )
                    }

                    composable("singUp") {
                        SingUp({ navController.navigate("inicio") }, auth)
                    }

                    composable("homePage") {
                        HomePage()
                    }
                }
            }
        }
    }

    override fun onStart() {
        auth = Firebase.auth
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser
        if (currentUser != null) {
//             navegar a otra pagina
        }
    }
}