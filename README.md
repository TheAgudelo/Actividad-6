# Actividad 6 - punto 1: Gestor de contacto

Este proyecto pertenece a los ejercicios de la materia programacion orientada a objetos y es una aplicación de escritorio desarrollada en Java con Swing que permite a los usuarios gestionar una lista de contactos. La aplicación ofrece funcionalidades para crear, leer, editar y eliminar contactos a través de una interfaz gráfica intuitiva.

## Características principales

- **Interfaz gráfica amigable** para la gestión de contactos.
- **Persistencia de datos** mediante archivos de texto planos.
- Posibilidad de **crear**, **leer**, **editar** y **eliminar** contactos existentes.

## Estructura del Proyecto

El sistema se compone de varias interfaces que extienden la clase `JFrame` y se comunican mediante eventos. A continuación, se detallan las clases principales:

### 1. `Principal.java`

- Clase principal del programa.
- Inicia la interfaz principal `InterfazInicial`.

### 2. `InterfazInicial.java`

- Ventana inicial del programa.
- Contiene botones para acceder a las funciones: Crear, Leer, Editar y Eliminar.
- Cada botón abre una nueva interfaz especializada.

### 3. `InterfazCrear.java`

- Permite al usuario crear un nuevo contacto ingresando nombre y número.
- Guarda los datos en el archivo `Archivos/Contactos.txt`.
- Verifica que no exista un contacto duplicado.

### 4. `InterfazLeer.java`

- Permite buscar un contacto por nombre.
- Si el contacto existe, se muestra su número asociado.

### 5. `InterfazEditar.java`

- Permite modificar el número asociado a un contacto existente.
- Lee el archivo y reemplaza la línea correspondiente si se encuentra el contacto.

### 6. `InterfazEliminar.java`

- Permite eliminar un contacto existente.
- Lee el archivo y sobrescribe el contenido sin la línea correspondiente al contacto a eliminar.

## Archivos

- `Archivos/Contactos.txt`: Archivo donde se almacenan los contactos en el formato `Nombre&Numero`.

## Tecnologías Utilizadas

- Java SE
- Swing para interfaces gráficas
- Manejo de archivos con `FileReader`, `FileWriter`, `BufferedReader`, `BufferedWriter`

## Requisitos para Ejecutar

1. Tener instalado JDK 8 o superior.
2. Crear una carpeta llamada `Archivos` en el mismo directorio donde se ejecuta el proyecto.
3. Compilar y ejecutar la clase `Principal`.

```bash
javac Principal.java
java Principal
```

## Autor

Desarrollado como parte de un ejercicio académico para la práctica de interfaces gráficas en Java y manejo de archivos.

## UML
![Diagrama de clases UML](/UML.png)
