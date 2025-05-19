##
# Diagrama de clases Backend(App)
```mermaid
classDiagram
    class MainActivity {
        -onCreate(savedInstanceState: Bundle)
    }

    class App {
        +App(defaultscreen: String, deepLink: Uri?)
    }

    class AppScreens {
        <<sealed>>
        +route: String
        +Login
        +Register
        +News
        +VerifyEmail
        +ResetPassword
    }

    class LoginScreen {
        -email: String
        -password: String
        -isLoading: Boolean
        -responseMessage: String
        +mapSupabaseAuthError(e: Throwable): String
    }

    class RegisterScreen {
        -email: String
        -password: String
        -confirmPassword: String
        -isLoading: Boolean
        -responseMessage: String
        +mapSupabaseAuthError(e: Throwable): String
    }

    class ResetPasswordScreen {
        -newPassword: String
        -confirmPassword: String
        -isLoading: Boolean
        -responseMessage: String
        +handleDeepLink(token: String)
    }

    class SendVerificationEmail {
        -email: String
        -isLoading: Boolean
        -responseMessage: String
    }

    class NewsScreen {
        +News: data class
        -newsList: List<News>
        +signOut()
    }

    MainActivity --> App
    App --> AppScreens
    App --> LoginScreen
    App --> RegisterScreen
    App --> ResetPasswordScreen
    App --> SendVerificationEmail
    App --> NewsScreen
```

---

# Diagrama de clases Backend(Supabase)
##Es un diagrama de clases basico que representa la estructura de la base de datos de Supabase, incluyendo las tablas y sus relaciones.
```mermaid
classDiagram
    class Users {
        +uuid id
        +text email
        +text phone
        +text password
        +text username
        +timestamp created_at
        +timestamp updated_at
        +boolean is_active
    }

    class UserRoles {
        +uuid id
        +uuid user_id
        +text role
        +timestamp created_at
        +timestamp updated_at
    }

    class UserSessions {
        +uuid id
        +uuid user_id
        +text session_token
        +timestamp created_at
        +timestamp updated_at
        +timestamp expires_at
    }

    class RefreshTokens {
        +uuid id
        +uuid user_id
        +text refresh_token
        +timestamp created_at
        +timestamp updated_at
        +timestamp expires_at
    }

    Users "1" -- "0..*" UserRoles : has
    Users "1" -- "0..*" UserSessions : has
    Users "1" -- "0..*" RefreshTokens : has
```