package LoginAndRegister

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dbConnection.SupabaseClient.supabase
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendVerificationEmail(navController: NavController) {
    val scope = rememberCoroutineScope()
    val email = remember { mutableStateOf("") }
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
                text = "Restablecer Contraseña",
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
                    .padding(bottom = 24.dp),
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

            Button(
                onClick = {
                    scope.launch {
                        if (email.value.isBlank()) {
                            responseMessage.value = "Error: Ingrese un email"
                            return@launch
                        }

                        isLoading = true
                        try {
                            supabase.auth.resetPasswordForEmail(
                                email.value,
                                redirectUrl = "notiapp://reset-password"
                            )
                            responseMessage.value = "Se ha enviado un enlace a tu correo"
                        } catch (e: Exception) {
                            responseMessage.value = "Error: No se pudo enviar el correo"
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
                    Text("Enviar correo de recuperación")
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
                    "Volver al login",
                    color = Color.White
                )
            }
        }
    }
}