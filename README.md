# Agenda Telefónica

Aplicación de escritorio para gestionar una agenda telefónica. Está desarrollada en Java Swing, usa Gradle como herramienta de automatización y sigue el patrón MVC (Modelo-Vista-Controlador).

## Requisitos

- JDK 17 o superior (el proyecto usa JDK 21)
- No es necesario instalar Gradle: el proyecto incluye el Gradle Wrapper (`gradlew`)

## Cómo ejecutar

En Linux o macOS:

```bash
./gradlew run
```

En Windows:

```bash
gradlew run
```

## Cómo ejecutar las pruebas

```bash
./gradlew test
```

## Estructura del proyecto

```
src/main/java/com/hackathon/agenda/
├── Main.java                   Punto de entrada: crea el modelo, la vista y el controlador
├── modelo/
│   ├── Contacto.java           Datos de un contacto y sus validaciones
│   └── Agenda.java             Lógica de la agenda: añadir, buscar, modificar, eliminar y ordenar
├── vista/
│   └── VentanaPrincipal.java   Ventana Swing: formulario, tabla y panel de estado
└── controlador/
    └── AgendaControlador.java  Conecta la vista con el modelo y maneja los eventos
```

## Decisiones de diseño

- **MVC:** el modelo no importa nada de Swing, la vista no decide qué ocurre al pulsar un botón, y el controlador coordina ambas partes.
- **Validación:** el teléfono se valida con expresiones regulares. Se aceptan formatos como `600000000` o `+34 600 000 000`.
- **Igualdad de contactos:** dos contactos son iguales si tienen el mismo nombre y apellido, sin distinguir mayúsculas ni minúsculas.
- **Errores:** las validaciones lanzan excepciones en el modelo, y el controlador las captura y las muestra en el panel de estado, en rojo. Las operaciones correctas se muestran en verde.
- **Capacidad:** la agenda tiene un máximo de 10 contactos por defecto, configurable al crearla.

## Gradle

El archivo `build.gradle` centraliza la configuración del proyecto, las dependencias y la tarea `run`. Usar el Gradle Wrapper garantiza que todos los integrantes compilen y ejecuten el programa de la misma forma, sin depender de la instalación local de cada computadora.

# AgendaTelefonica
