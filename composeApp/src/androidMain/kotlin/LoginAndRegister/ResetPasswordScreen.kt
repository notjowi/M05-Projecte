package LoginAndRegister

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dbConnection.SupabaseClient.supabase
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.*
import org.notiapp.App
import org.notiapp.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(navController: NavController, deepLink: Uri?) {
    val responseMessage = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }

    // Extraer token del deepLink
    val fragment = deepLink?.fragment
    val token = fragment
        ?.split("&")
        ?.find { it.startsWith("access_token=") }
        ?.substringAfter("=")

    // Manejo de errores del deep link
    val error = fragment
        ?.split("&")
        ?.find { it.startsWith("error=") }
        ?.substringAfter("=")
    val errorDescription = fragment
        ?.split("&")
        ?.find { it.startsWith("error_description=") }
        ?.substringAfter("=")
        ?.replace("+", " ")

    if (error == "access_denied") {
        LaunchedEffect(Unit) {
            responseMessage.value = errorDescription ?: "El enlace ha caducado o es inválido."
            delay(2000)
            navController.navigate(AppScreens.Login.route) {
                popUpTo(AppScreens.Login.route) { inclusive = true }
            }
        }
    }

    Log.d("ResetPassword", "DeepLink: $deepLink")
    Log.d("ResetPassword", "Token: $token | Error: $error | Desc: $errorDescription")

    val newPassword = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    if (error == null && token != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF2E42B7), Color(0xFFEEEEEE))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "NOTIAPP",
                    fontSize = 40.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 48.dp)
                )

                Text(
                    "ACTUALIZA TU CONTRASEÑA",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                OutlinedTextField(
                    value = newPassword.value,
                    onValueChange = { newPassword.value = it },
                    label = { Text("Nueva Contraseña") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    enabled = !isLoading.value,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray
                    )
                )

                OutlinedTextField(
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    label = { Text("Confirmar Contraseña") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    enabled = !isLoading.value,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray
                    )
                )

                Button(
                    onClick = {
                        if (newPassword.value != confirmPassword.value) {
                            responseMessage.value = "Las contraseñas no coinciden"
                            return@Button
                        }
                        CoroutineScope(Dispatchers.IO).launch {
                            isLoading.value = true
                            try {
                                supabase.auth.importAuthToken(token)
                                supabase.auth.updateUser {
                                    password = newPassword.value
                                }
                                withContext(Dispatchers.Main) {
                                    responseMessage.value = "Contraseña actualizada exitosamente"
                                }
                            } catch (e: Exception) {
                                Log.e("ResetPassword", "Error al actualizar", e)
                                withContext(Dispatchers.Main) {
                                    responseMessage.value = "Ha ocurrido un error. Inténtalo de nuevo."
                                }
                            } finally {
                                withContext(Dispatchers.Main) {
                                    isLoading.value = false
                                }
                                delay(2000)
                                navController.navigate(AppScreens.Login.route) {
                                    popUpTo(AppScreens.Login.route) { inclusive = true }
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = !isLoading.value
                ) {
                    if (isLoading.value) {
                        CircularProgressIndicator(color = Color.White)
                    } else {
                        Text("ACTUALIZAR")
                    }
                }

                if (responseMessage.value.isNotBlank()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = responseMessage.value,
                        color = if (responseMessage.value.startsWith("Ha"))
                            Color.Red
                        else
                            Color.Green,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    } else if (error != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF2E42B7), Color(0xFFEEEEEE))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = responseMessage.value,
                color = Color.Red,
                fontSize = 18.sp
            )
        }
    }
}