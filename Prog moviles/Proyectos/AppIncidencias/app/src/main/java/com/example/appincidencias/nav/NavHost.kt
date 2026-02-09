package com.example.appincidencias.nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appincidencias.ui.theme.AppIncidenciasTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.example.appincidencias.ui.login.LogIn
import com.example.appincidencias.ui.signup.SignUp

class NavHost : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppIncidenciasTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "inicio"
                ) {
                    composable("inicio") {
                        LogIn(
                            auth,
                            { navController.navigate("homePage") },
                            { navController.navigate("signUp") }
                        )
                    }

                    composable("signUp") {
                        SignUp(
                            auth,
                            { navController.navigate("inicio") },
                            { navController.navigate("homePage") }
                        )
                    }

                    composable("logIn") {
                    }

                    composable("homePage") {
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