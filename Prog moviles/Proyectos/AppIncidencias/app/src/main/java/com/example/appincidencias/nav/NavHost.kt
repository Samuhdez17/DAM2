package com.example.appincidencias.nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appincidencias.data.network.JsonIncidenciasApi
import com.example.appincidencias.ui.incidencias.DetalleIncidencia
import com.example.appincidencias.ui.incidencias.NuevaIncidencia
import com.example.appincidencias.ui.incidencias.PantallPrincipal
import com.example.appincidencias.ui.theme.AppIncidenciasTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.example.appincidencias.ui.login.LogIn
import com.example.appincidencias.ui.signup.SignUp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NavHost : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        val lanzadorApi: JsonIncidenciasApi =
            Retrofit.Builder()
                .baseUrl("https://incidencias-api-veppzdntwa-ew.a.run.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JsonIncidenciasApi::class.java)


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppIncidenciasTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "principal"
                ) {
                    composable("logIn") {
                        LogIn(
                            auth,
                            { navController.navigate("principal") },
                            { navController.navigate("signUp") },
                        )
                    }

                    composable("signUp") {
                        SignUp(
                            auth,
                            { navController.navigate("logIn") },
                            { navController.navigate("principal") }
                        )
                    }

                    composable("principal") {
                        PantallPrincipal(
                            lanzadorApi,
                            { navController.navigate("registrarIncidencia") },
                            { idIncidencia -> navController.navigate("verIncidencia/$idIncidencia") },
                            { navController.navigate("logIn") }
                        )
                    }

                    composable("registrarIncidencia") {
                        NuevaIncidencia(
                            lanzadorApi,
                            { navController.navigate("principal") },
                        )
                    }

                    composable("verIncidencia/{idIncidencia}") { backStackEntry ->
                        val idIncidencia = backStackEntry.arguments!!.getString("idIncidencia")!!.toInt()

                        DetalleIncidencia(
                            lanzadorApi,
                            idIncidencia,
                            { navController.navigate("principal") },
                        )
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