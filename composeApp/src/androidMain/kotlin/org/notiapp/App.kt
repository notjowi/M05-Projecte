package org.notiapp

        import ContentApp.NewsScreen
        import androidx.compose.runtime.Composable
        import androidx.compose.material3.MaterialTheme
        import androidx.navigation.compose.NavHost
        import androidx.navigation.compose.composable
        import androidx.navigation.compose.rememberNavController
        import LoginAndRegister.LoginScreen
        import LoginAndRegister.RegisterScreen
        import LoginAndRegister.ResetPasswordScreen
        import LoginAndRegister.SendVerificationEmail
        import android.net.Uri
        import org.jetbrains.compose.ui.tooling.preview.Preview

        @Composable
        @Preview
        fun App(defaultscreen: String = "login", deepLink: Uri? = null) {
            val navController = rememberNavController()

            MaterialTheme {
                NavHost(
                    navController = navController,
                    startDestination = defaultscreen
                ) {
                    composable(AppScreens.Login.route) {
                        LoginScreen(navController = navController)
                    }
                    composable(AppScreens.Register.route) {
                        RegisterScreen(navController = navController)
                    }
                    composable(AppScreens.VerifyEmail.route) {
                        SendVerificationEmail(navController = navController)
                    }
                    composable(AppScreens.ResetPassword.route){
                        ResetPasswordScreen(navController = navController, deepLink = deepLink)
                    }
                    composable(AppScreens.News.route) {
                        NewsScreen(navController = navController)
                    }
                    // Aquí agregarás más pantallas según las vayas implementando
                }
            }
        }

        sealed class AppScreens(val route: String) {
            object Login : AppScreens("login")
            object Register : AppScreens("register")
            object News : AppScreens("News")
            object VerifyEmail : AppScreens("VerifyEmail")
            object ResetPassword : AppScreens("resetPassword")
            // Agrega más rutas según necesites
        }