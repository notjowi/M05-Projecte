## Pruebas Unitarias para Login y Registro

A continuación, se presentan pruebas unitarias para las funcionalidades de inicio de sesión y registro utilizando Kotlin.

### Prueba Unitaria para Login

```kotlin
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginViewModelTest {

    @Test
    fun testLoginConCredencialesValidas() {
        val viewModel = LoginViewModel()
        val resultado = viewModel.login("usuario@example.com", "contraseñaSegura")
        assertEquals(true, resultado)
    }

    @Test
    fun testLoginConCredencialesInvalidas() {
        val viewModel = LoginViewModel()
        val resultado = viewModel.login("usuario@example.com", "contraseñaIncorrecta")
        assertEquals(false, resultado)
    }
}
```

### Prueba Unitaria para Registro

```kotlin
import org.junit.Assert.assertEquals
import org.junit.Test

class RegisterViewModelTest {

    @Test
    fun testRegistroConDatosValidos() {
        val viewModel = RegisterViewModel()
        val resultado = viewModel.register("nuevoUsuario@example.com", "contraseñaSegura")
        assertEquals(true, resultado)
    }

    @Test
    fun testRegistroConEmailInvalido() {
        val viewModel = RegisterViewModel()
        val resultado = viewModel.register("emailInvalido", "contraseñaSegura")
        assertEquals(false, resultado)
    }
}
```


