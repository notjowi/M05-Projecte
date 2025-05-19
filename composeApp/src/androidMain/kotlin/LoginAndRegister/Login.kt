package LoginAndRegister

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dbConnection.SupabaseClient.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.exceptions.RestException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.notiapp.AppScreens

private fun mapSupabaseAuthError(e: Throwable): String {
    return when (e) {
        is RestException -> when (e.statusCode) {
            400 -> "Correo o contraseña incorrectos."
            401 -> "No autorizado. Revise sus credenciales."
            403 -> "Acceso denegado."
            429 -> "Demasiadas solicitudes. Inténtelo más tarde."
            else -> "Error al iniciar sesión: ${e.error ?: "Error desconocido"}"
        }
        else -> "Error inesperado. Comprueba tu conexión."
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val responseMessage = remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "NOTIAPP",
                fontSize = 40.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                enabled = !isLoading,
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
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                enabled = !isLoading,
                visualTransformation = if (passwordVisible.value)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
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
                    scope.launch {
                        if (email.value.isBlank() || password.value.isBlank()) {
                            responseMessage.value = "Error: Complete todos los campos"
                            return@launch
                        }

                        isLoading = true
                        try {
                            supabase.auth.signInWith(Email) {
                                this.email = email.value
                                this.password = password.value
                            }
                            responseMessage.value = "Inicio de sesión exitoso"
                            delay(1500)
                            withContext(Dispatchers.Main) {
                                navController.navigate(AppScreens.News.route) {
                                    popUpTo(0) { inclusive = true }
                                }
                            }
                        } catch (e: Exception) {
                            responseMessage.value = mapSupabaseAuthError(e)
                        } finally {
                            isLoading = false
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White)
                } else {
                    Text("Iniciar Sesión")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { navController.navigate(AppScreens.Register.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = !isLoading,
                border = BorderStroke(1.dp, Color.White)
            ) {
                Text(
                    "Registrarse",
                    color = Color.White
                )
            }

            if (responseMessage.value.isNotBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = responseMessage.value,
                    color = if (responseMessage.value.startsWith("Error"))
                        Color.Red
                    else
                        Color.Green
                )
            }

            TextButton(
                onClick = { navController.navigate(AppScreens.VerifyEmail.route) },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    "¿Olvidaste tu contraseña?",
                    color = Color.White
                )
            }
        }
    }
}