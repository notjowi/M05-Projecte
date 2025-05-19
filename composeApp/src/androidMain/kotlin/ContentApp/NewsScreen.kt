package ContentApp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dbConnection.SupabaseClient.supabase
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.notiapp.AppScreens

// Modelo de datos para las noticias
data class News(
    val title: String,
    val description: String,
    val date: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(navController: NavController) {
    // Lista de noticias precargadas
    val newsList = remember {
        listOf(
            News(
                "Nueva versión de Android Studio",
                "Google lanza la última versión de Android Studio con importantes mejoras de rendimiento",
                "2024-03-20"
            ),
            News(
                "Kotlin 2.0 en camino",
                "JetBrains anuncia las nuevas características de Kotlin 2.0",
                "2024-03-19"
            ),
            News(
                "Flutter vs React Native",
                "Comparativa actualizada de los frameworks más populares para desarrollo móvil",
                "2024-03-18"
            ),
            News(
                "Compose Multiplatform 1.5",
                "Nueva actualización trae mejoras significativas en el rendimiento",
                "2024-03-17"
            ),
            News(
                "Tendencias en desarrollo móvil",
                "Las tecnologías más demandadas en 2024 para el desarrollo de apps",
                "2024-03-16"
            ),
            News(
                "Jetpack Compose 2.0",
                "Nueva versión incluye mejoras en el rendimiento y nuevos componentes personalizables",
                "2024-03-15"
            ),
            News(
                "Android 15 Beta",
                "Google libera la primera beta de Android 15 con nuevas APIs y características de privacidad",
                "2024-03-14"
            ),
            News(
                "Kotlin Coroutines Flow",
                "Guía completa sobre el manejo de flujos de datos asíncronos en aplicaciones Android",
                "2024-03-13"
            ),
            News(
                "Material Design 3",
                "Nuevas pautas y componentes para crear interfaces más modernas y accesibles",
                "2024-03-12"
            ),
            News(
                "Firebase Analytics Pro",
                "Nueva versión de Firebase añade características avanzadas de análisis y seguimiento",
                "2024-03-11"
            ),
            News(
                "SQLite vs Room",
                "Análisis comparativo de las soluciones de base de datos más populares en Android",
                "2024-03-10"
            ),
            News(
                "Gradle 9.0",
                "Nueva versión del sistema de construcción trae mejoras significativas en velocidad",
                "2024-03-09"
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF2E42B7), Color(0xFFEEEEEE))
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Barra superior
            TopAppBar(
                title = {
                    Text(
                        "NOTIAPP",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            // Cerrar sesión
                            CoroutineScope(Dispatchers.IO).launch {
                                try {
                                    supabase.auth.signOut()
                                    withContext(Dispatchers.Main) {
                                        navController.navigate(AppScreens.Login.route) {
                                            popUpTo(AppScreens.Login.route) { inclusive = true }
                                        }
                                    }
                                } catch (e: Exception) {
                                    Log.e("NewsScreen", "Error al cerrar sesión", e)
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Cerrar sesión",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )

            // Lista de noticias
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(newsList) { news ->
                    NewsCard(news = news)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsCard(news: News) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.9f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = news.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E42B7)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = news.description,
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = news.date,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}