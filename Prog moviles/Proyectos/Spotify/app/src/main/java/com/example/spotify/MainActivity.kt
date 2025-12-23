package com.example.spotify

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity {
    private lateinit var auth: FirebaseAuth
    auth = Firebase.auth

    override fun onStart()
    {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser
        if (currentUser != null)
        {
            // navegar a otra pagina
        }
    }
}