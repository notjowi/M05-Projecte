# M05-Projecte

# NotiApp - Aplicación de Noticias en Android 📱

NotiApp es una aplicación móvil Android que permite a los usuarios explorar, personalizar y gestionar noticias en tiempo real, con un diseño intuitivo y funcionalidades avanzadas como recomendaciones personalizadas y modo offline.

## Características principales ✨

- 📰 Feed de noticias en tiempo real  
- 🔍 Búsqueda avanzada con filtros  
- ❤️ Sistema de favoritos y marcadores  
- 🧠 Recomendaciones personalizadas  
- 📂 Modo offline (almacenamiento local)  
- 🌗 Soporte para tema oscuro/claro  
- 🔔 Notificaciones push para noticias relevantes  

## Tecnologías utilizadas 🛠️

**Frontend:**  
- Kotlin  
- Jetpack Compose  
- Coroutines  
- ViewModel  

**Backend:**  
- Supabase (Auth, Database, Storage)  
- PostgreSQL  

**Otras herramientas:**  
- Room (para modo offline)  
- Coil (para carga de imágenes)  
- Ktor/Retrofit (para networking)  

## Documentación 📄

- [Diagrama de arquitectura Cliente-Servidor](https://github.com/notjowi/M05-Projecte/blob/ee0a92f3200900648cb920bc5d035269e689bfbc/Documents/Diagrama%20de%20arquitectura%20Cliente-Servidor.md)  
- [Diagrama de clases Backend](https://github.com/notjowi/M05-Projecte/blob/ee0a92f3200900648cb920bc5d035269e689bfbc/Documents/Diagrama%20de%20classes%20Backend.md)  
- [Modelo E-R de la base de datos](https://github.com/notjowi/M05-Projecte/blob/ee0a92f3200900648cb920bc5d035269e689bfbc/Documents/Model%20E-R)  
- [Mockup de la interfaz](https://github.com/notjowi/M05-Projecte/blob/main/Documents/Mokup.png)  
- [Diagrama de secuencia de la app](https://github.com/notjowi/M05-Projecte/blob/ee0a92f3200900648cb920bc5d035269e689bfbc/Documents/Seq%C3%BC%C3%A8ncia%20app.md)  
- [Casos de uso (Mermaid)](https://github.com/notjowi/M05-Projecte/blob/ee0a92f3200900648cb920bc5d035269e689bfbc/Documents/UseCase.mermaid)  
- [Script SQL de Supabase](https://github.com/notjowi/M05-Projecte/blob/main/Documents/dbAuthNotiApp(Supabase).sql)  
- [Requisitos técnicos](https://github.com/notjowi/M05-Projecte/blob/ee0a92f3200900648cb920bc5d035269e689bfbc/Documents/req-tecnic.md)  
- [Pruebas unitarias](https://github.com/notjowi/M05-Projecte/blob/ee0a92f3200900648cb920bc5d035269e689bfbc/Documents/testsunitaris.md)  
## Requisitos del sistema 📋

- Android Studio Giraffe+  
- Android SDK 34 (Android 14)  
- Dispositivo con Android 8.0+ (API 26+)  
- Conexión a Internet para sincronización inicial  

## Configuración inicial ⚙️

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/notjowi/M05-Projecte.git
   ```

2. Abrir el proyecto en Android Studio

3. Configurar variables de entorno:
   - Crear archivo `local.properties` en la raíz del proyecto
   - Añadir tus credenciales de Supabase:
     ```
     supabase.url=TU_URL_SUPABASE
     supabase.key=TU_KEY_SUPABASE
     ```

4. Sincronizar proyecto con Gradle

5. Ejecutar en emulador o dispositivo físico

## Guías de usuario 📱

- [Manual de usuario TODO](Documents/ManualUsuariM05.pdf)  
- [FAQ - Preguntas frecuentes TODO](Documents/ManualTecnicM05.pdf)  

## Capturas de pantalla 🖼️

[Mokups](https://github.com/notjowi/M05-Projecte/blob/ee0a92f3200900648cb920bc5d035269e689bfbc/Documents/Mokup.png)

## Roadmap 🗺️

- [ ] Implementar suscripciones a categorías  
- [ ] Añadir sistema de comentarios  
- [ ] Soporte para múltiples idiomas  
- [ ] Versión iOS con KMM  

## Cómo contribuir 🤝

1. Haz fork del proyecto  
2. Crea una rama con tu feature (`git checkout -b feature/awesome-feature`)  
3. Haz commit de tus cambios (`git commit -m 'Add awesome feature'`)  
4. Haz push a la rama (`git push origin feature/awesome-feature`)  
5. Abre un Pull Request  

## Licencia 📜

Este proyecto está bajo la licencia **MIT**. Ver [LICENSE](LICENSE) para más detalles.

---

**NotiApp Team** © 2025 | [Reportar un issue](https://github.com/notjowi/M05-Projecte/issues) | [Documentación técnica](Documents/)

