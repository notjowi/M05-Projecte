package LoginAndRegister

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import kotlinx.coroutines.launch

private fun mapSupabaseAuthError(e: Throwable): String {
    return when (e) {
        is RestException -> when (e.statusCode) {
            400 -> "Error en el registro. Verifique los datos."
            401 -> "No autorizado para realizar el registro."
            409 -> "El correo ya está registrado."
            422 -> "Datos inválidos. Verifique el formato."
            429 -> "Demasiadas solicitudes. Inténtelo más tarde."
            else -> "Error en el registro: ${e.error ?: "Error desconocido"}"
        }
        else -> "Error inesperado. Comprueba tu conexión."
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val confirmPasswordVisible = remember { mutableStateOf(false) }
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

            Text(
                text = "Registro",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
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
                    .padding(bottom = 16.dp),
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

            OutlinedTextField(
                value = confirmPassword.value,
                onValueChange = { confirmPassword.value = it },
                label = { Text("Confirmar Contraseña") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                enabled = !isLoading,
                visualTransformation = if (confirmPasswordVisible.value)
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
                        if (email.value.isBlank() || password.value.isBlank() || confirmPassword.value.isBlank()) {
                            responseMessage.value = "Error: Complete todos los campos"
                            return@launch
                        }

                        if (password.value != confirmPassword.value) {
                            responseMessage.value = "Error: Las contraseñas no coinciden"
                            return@launch
                        }

                        isLoading = true
                        try {
                            supabase.auth.signUpWith(Email) {
                                this.email = email.value
                                this.password = password.value
                            }
                            responseMessage.value = "Registro exitoso"
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
                    Text("Registrarse")
                }
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
                onClick = { navController.navigateUp() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    "¿Ya tienes cuenta? Inicia sesión",
                    color = Color.White
                )
            }
        }
    }
}