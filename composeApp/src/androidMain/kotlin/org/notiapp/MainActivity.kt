package org.notiapp

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import dbConnection.SupabaseClient.supabase
import io.github.jan.supabase.auth.auth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                var startDestination by remember { mutableStateOf("login") }
                var isLoading by remember { mutableStateOf(true) }

                LaunchedEffect(Unit) {
                    try {
                        supabase.auth.loadFromStorage()
                        val session = supabase.auth.currentSessionOrNull()
                        if (session != null) {
                            Log.d("MainActivity", "Sesión activa: ${session.user?.email}")
                            startDestination = "News"
                        }
                    } catch (e: Exception) {
                        Log.e("MainActivity", "Error al cargar sesión: ${e.message}")
                        startDestination = "login"
                    } finally {
                        isLoading = false
                    }
                }

                if (isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else {
                    App(defaultscreen = startDestination,null)
                }
            }
        }
    }
}