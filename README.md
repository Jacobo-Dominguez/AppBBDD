# ProyectoFinal – SteamDB  (Java + MySQL)

Este proyecto es una simulación sencilla de una base de datos tipo Steam, desarrollada en Java con Swing para la interfaz gráfica y MySQL como base de datos. Los usuarios pueden ver juegos, escribir reseñas, realizar compras y ser administrados por un usuario administrador.

## 🗂️ Estructura del Proyecto

```plaintext
ProyectoFinal/
│
├── doc/                                    # Documentación del proyecto
│ ├── DAX_PROG_Act9- POO y BBDD.docx.pdf    # Enunciado del proyecto
│ └── BBDD_ProyectoFinal.sql                # Script SQL de creación de la base de datos
│
├── src/
│ ├── gestores/                             # Clases gestoras para interactuar con la base de datos
│ │ ├── DBConnection.java
│ │ ├── GestorCompras.java
│ │ ├── GestorJuegos.java
│ │ ├── GestorResenas.java
│ │ └── GestorUsuarios.java
│ │
│ ├── resources/                            # Recursos como imágenes e iconos
│ │ ├── icono.png
│ │ └── [otras imágenes no utilizadas]
│ │
│ ├── tablas/                               # Representación de las tablas de la BD como clases Java
│ │ ├── Compra.java
│ │ ├── Juego.java
│ │ ├── Resena.java
│ │ └── Usuario.java
│ │
│ ├── vistas/                               # Interfaces gráficas en Swing
│ │ ├── VentanaCompras.java                 # Ventana que contiene todas las compras realizadas por el usuario logeado
│ │ ├── VentanaCrearUsuario.java            # Ventana de registro de usuarios a la BBDD
│ │ ├── VentanaAdministrarUsuarios.java     # Ventana exclusiva para los admin que les permite eliminar usuarios de la BBDD
│ │ ├── VentanaJuegos.java                  # Ventana que contiene todos los juegos disponibles en SteamDB
│ │ ├── VentanaLogin.java                   # Ventana donde se introduce el email para iniciar sesion
│ │ ├── VentanaMenu.java                    # Ventana "principal" donde estan todos los botones que a su vez te lleva por el resto de ventanas 
│ │ ├── VentanaMisResenas.java              # Ventana con las reseñas realizadas por el usuario logeado
│ │ └── VentanaResenas.java                 # Ventana con todas las reseñas realizadas por los usuarios sobre un juego escpecifico
│ │
│ ├── Main.java                             # Clase principal que lanza la aplicación
│ ├── PrincipalCompra.java                  # Pruebas individual de compra
│ ├── PrincipalJuego.java                   # Pruebas individual de juego
│ ├── PrincipalResena.java                  # Pruebas individual de reseña
│ └── PrincipalUsuario.java                 # Pruebas individual de usuario
```


---

## 🚀 Ejecución

1. **Base de Datos**: Importar el archivo `BBDD_ProyectoFinal.sql` desde la carpeta `doc` en tu gestor de base de datos (ej. MySQL Workbench).
2. **Configuración de conexión**: Editar los parámetros de conexión en `DBConnection.java` (usuario, contraseña, URL).
3. **Compilar y ejecutar**: Ejecutar `Main.java` desde tu entorno de desarrollo (NetBeans, IntelliJ, Eclipse).

---

## 🎮 Funcionalidades Principales

- **Login y Registro de Usuario**
    - Login con correo electrónico
    - Crear nuevo usuario

- **Gestión de Juegos**
    - Ver lista de juegos
    - Comprar juegos
    - Ver reseñas de cada juego

- **Gestión de Reseñas**
    - Crear reseñas de juegos comprados
    - Ver reseñas propias y de otros

- **Gestión de Usuarios (Administrador)**
    - Ver lista de todos los usuarios
    - Eliminar usuarios

---

## 🛠 Tecnologías Utilizadas

- **Lenguaje**: Java
- **Interfaz Gráfica**: Swing
- **Base de Datos**: MySQL
- **Conexión BD**: JDBC

---
## 👤 Autor

- *Jacobo Luis Domínguez Morales* – Proyecto académico de programación orientada a objetos.


