# RETO MH – Historia del Software

## 📝 Descripción
Este proyecto forma parte del Reto de Historia del Software del equipo 4. Consiste en el desarrollo y despliegue de una solución técnica que integra una infraestructura segura en la nube (AWS) y una base de datos gestionada, sirviendo como soporte para la aplicación del reto. El enfoque principal es la seguridad perimetral y el trabajo colaborativo bajo estándares de industria.

---

## 👥 Miembros del Equipo
* **David Alonso Casaiz** 
* **Adei Fernández Velar** 
* **Sergio Camacho Viera** 
* **Alberto González Ceballos** 
* **Adrián González Gil** 

---

## 📂 Estructura del Proyecto
```text
.
├── Documentaciones/                 # Los Documentos
├── ProyectoRetoFinalEquipo4/                 # Código fuente de la aplicación              
├── .gitignore           # Archivos excluidos (secretos, venv, etc.)
└── README.md            # Este archivo

```


## 🌐 Sitio Web
# Gestión de Taller - Equipo 4

Este proyecto consiste en una aplicación web para la gestión y localización de material del taller del IES Miguel Herrero Pereda.

La aplicación permite visualizar la distribución física del taller mediante armarios, cajoneras y mesas, además de consultar el inventario almacenado en cada zona. También incluye una visualización adicional en 3D para facilitar la organización y búsqueda de materiales.

---
<details>
<summary>🧱 Estructura HTML</summary>

La página está desarrollada utilizando HTML5 y etiquetas semánticas para organizar correctamente el contenido.

## Cabecera (`header`)
Contiene:
- El título principal del proyecto.
- Información del centro educativo.

## Navegación (`nav`)
Incluye un menú con enlaces a:
- Inicio
- Plano del Taller
- Inventario
- Inventario 3D

La navegación permite desplazarse entre las diferentes secciones mediante enlaces internos.

## Contenido principal (`main`)

### Sección Inicio
Presenta una breve descripción del sistema y su finalidad.

### Sección Plano del Taller
Muestra un plano visual del taller mediante:
- Armario A1
- Cajonera A2
- Mesas A3

Cada estructura está representada mediante tablas HTML que simulan baldas, cajones y compartimentos.// (En revision)

### Sección Inventario
Zona dinámica donde se muestran los objetos almacenados en cada balda seleccionada.

Elementos principales:
- `panel-objetos`
- `resumen-total`

### Footer (`footer`)
Incluye información del equipo y del ciclo formativo.

---
</details>
<details>
<summary>🎨 Explicación del CSS</summary>

Los estilos están definidos en el archivo `style.css`.

## Variables globales
Se utilizan variables CSS (`:root`) para mantener una apariencia uniforme:
- Colores principales
- Fondos
- Bordes
- Tipografía
- Radios de esquinas

## Diseño general
La web utiliza:
- `Flexbox` para la distribución de elementos.
- Diseño responsive adaptable a distintas pantallas.
- Tema oscuro moderno inspirado en interfaces profesionales.

## Tipografía
Se utiliza la fuente:
- `Inter` importada desde Google Fonts.

## Estilos principales

### Header y navegación
- Gradientes oscuros.
- Menú horizontal interactivo.
- Efectos hover en los enlaces.

### Plano visual y armarios
Los armarios incluyen:
- Sombras.
- Bordes personalizados.
- Animaciones al pasar el cursor.
- Cambio de color en las baldas seleccionadas.

### Inventario
El panel de inventario incorpora:
- Listas dinámicas.
- Paneles informativos.
- Colores diferenciados.
- Diseño visual organizado.

### Inventario 3D
La sección `inventario_3d.html` incluye:
- Toolbar interactiva.
- Panel lateral de estadísticas.
- Tooltips dinámicos.
- Área de renderizado 3D mediante canvas.

---
</details>
<details>
<summary>⚙️ Explicación del JavaScript</summary>

La lógica interactiva está implementada en `script.js`.

## Base de datos local
Se utiliza un objeto JavaScript llamado `dbTaller` que almacena:
- Armarios
- Baldas
- Materiales disponibles en cada ubicación

Ejemplo:
- Tornillos
- Procesadores
- Memoria RAM
- Herramientas de red
- Discos duros

## Interacción con el usuario
El script detecta los clics realizados sobre las baldas del plano visual.

Cuando el usuario selecciona una balda:
1. Se resalta visualmente la selección.
2. Se obtiene el armario y la balda seleccionada.
3. Se consulta la información almacenada en `dbTaller`.
4. Se muestra dinámicamente el contenido del inventario.

## Actualización dinámica
El inventario se actualiza automáticamente utilizando:
- `innerHTML`
- Eventos `click`
- Manipulación del DOM

También se muestra:
- Número total de componentes almacenados.
- Mensajes cuando una balda está vacía.

---
</details>
<summary>Contenido Incluido</summary>

La web contiene:
- Plano visual del taller.
- Organización de armarios y baldas.
- Sistema interactivo de inventario.
- Navegación entre secciones.
- Visualización 3D.
- Paneles informativos y estadísticas.

```

# Ruta o enlace de la web

## Archivo principal

---
