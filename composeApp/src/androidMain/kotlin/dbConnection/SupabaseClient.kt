package dbConnection

import io.github.jan.supabase.auth.*
import io.github.jan.supabase.auth.status.SessionSource.Storage
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest


object SupabaseClient {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://acdnagrrkhecwehuguxw.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFjZG5hZ3Jya2hlY3dlaHVndXh3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDc1ODI2NDYsImV4cCI6MjA2MzE1ODY0Nn0.2kB0cICP1cNz0QlufcGYma9DSUlT4_5mfeZegyYgtus"
    ) {
        install(Postgrest)
        install(Auth){
            alwaysAutoRefresh = true
            autoLoadFromStorage = true
        }
    }
}