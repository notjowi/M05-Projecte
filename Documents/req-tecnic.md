# 📋 **Requisitos Técnicos de NotiApp**  

## **1. Backend (Servidor y Gestión de Datos)**  
### **a. Requisitos del Servidor**  
- **Alojamiento**: Supabase (Backend-as-a-Service)  
- **Base de datos**: Supabase postgresql  
- **APIs**: RESTful con Retrofit + NewsAPI (fuente de noticias)  

### **b. Lenguajes de Programación**  
- Kotlin (principal)  

### **c. Seguridad**  
- **Autenticación**: Supabase Auth (correo/Google)  
- **Cifrado**: HTTPS + AES-256 para datos sensibles en local  
- **Copias de seguridad**: Automáticas (supabase)  

---

## **2. Frontend (Aplicación Android)**  
### **a. Tecnologías Principales**  
- **Lenguaje**: Kotlin (100% nativo)  
- **Arquitectura**: MVVM + Clean Architecture  
- **UI**: Jetpack Compose (o XML para layouts legacy)  

### **b. Compatibilidad**  
- **Versión mínima**: Android 8.0 (API 26)  
- **Dispositivos**: Móviles y tablets Android  

---

## **3. Requisitos Generales**  
### **a. Gestión de Usuarios**  
- **Roles**: Usuario estándar, Administrador (vía Supabase postgres)  
- **Autenticación**: Email/contraseña o Google Sign-In (Token JWT)  

### **b. Almacenamiento Local**  
- **Datos guardados**: Preferencias de usuario, noticias en caché  
- **Seguridad**: Room con cifrado SQLCipher (opcional)  

### **c. Accesibilidad**  
- **Niveles WCAG**: AA (contraste adecuado, tamaños de texto ajustables)  

---

## **4. Infraestructura**  
- **Conectividad**: Internet obligatorio para actualizaciones  
- **APIs de terceros**:  
  - NewsAPI (noticias)  
  - Supabase (auth, base de datos, notificaciones)  

---

## **5. Proceso de Desarrollo**  
### **a. Herramientas**  
- **IDE**: Android Studio Flamingo+  
- **Control de versiones**: Git + GitHub  
- **Metodología**: Scrum (sprints de 2 semanas)  

### **b. Testing**  
- **Unitarios**: JUnit + kotlin  
- **UI**: Espresso  
- **QA**: Pruebas manuales en dispositivos reales (Android 8+)  

---

## **6. Documentación Técnica Adicional**  
- **Diagrama de arquitectura**: Disponible en `/Documents/architecture.md`  
- **API Reference**: Swagger 

--- 
