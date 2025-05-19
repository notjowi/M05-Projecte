package org.notiapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import LoginAndRegister.ResetPasswordScreen

class ResetPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val deepLink: Uri? = intent?.data

        setContent {
            MaterialTheme {
                App(defaultscreen = "resetPassword", deepLink = deepLink)
            }
        }
    }
}