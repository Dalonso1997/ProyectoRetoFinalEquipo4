# RETO MH – Gestión y Localización del Material del Taller

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
# 🛠️ Tecnologías Utilizadas

---

## 💻 Programación

- Java
- Swing
- JDBC
- NetBeans

---

## 🗄️ Base de Datos

- MySQL
- MySQL Workbench

---

## 🌐 Web

- HTML5
- CSS3
- JavaScript

---

## ☁️ Infraestructura

- AWS Academy
- EC2
- VPC
- Elastic IP
- Security Groups
- Apache/Nginx
- OpenSSH

---

## 🤝 Herramientas colaborativas

- GitHub 
- GitHub Desktop
- GitHub Projects
- Teams
- Visual Studio Code
- Drawio.com

---

# 🗄️ Base de Datos

## 📖 Descripción

La base de datos permite almacenar y gestionar el inventario completo del taller de informática.

Se registran:

- Componentes hardware
- Material de red
- Herramientas
- Material fungible
- Equipos completos
- Usuarios
- Préstamos
- Ubicaciones físicas

---

## 📌 Diagrama Entidad/Relación

<details>

<summary>📌 Diagrama E/R</summary>

![Diagrama E/R](Documentaciones/Base%20Datos/Diagrama%20ER.jpg)
</details>
<details>

<summary>📌 Modelo Relacional</summary>

![Modelo Relacional](Documentaciones/Base%20Datos/BD-03%20-%20Convertir%20el%20E_R%20en%20modelo%20relacional.drawio.png)

</details>

---


📄 Script SQL

📄 [Ver Script SQL](Documentaciones/Base%20Datos/ScriptCreacionBaseDeDatosDefinitivo.sql)



---

## ⚡ Triggers Implementados

### 🔒 Trigger de control de préstamos

Este trigger evita que se puedan realizar operaciones de préstamo si no hay suficiente stock disponible en el inventario.  
En caso de intentar reducir la cantidad por debajo de 0, la base de datos lanza un error y bloquea la operación.

---

### 📦 Trigger de actualización de stock

Este trigger registra automáticamente todos los cambios de cantidad en el inventario.  
Cada vez que se modifica la cantidad de un material, se guarda un histórico con:

- Cantidad anterior  
- Cantidad nueva  
- Fecha del movimiento  
- Identificador del material afectado  

Esto permite llevar un control completo de los movimientos del almacén.

---
 
### ⚠️ Trigger para el control del estado en cantidad 0

Este trigger se utiliza para que cuando un material queda en cantidad 0, automaticamente su estado pasa a 'prestado' delimitando asi que se pueda solicitar un prestamo de dicho material.

---

### 📋 Trigger para el registro de alta de nuevo material

Trigger para registrar el alta de un material nuevo, registrando:

- Identificador del material
- Cantidad del material
- Estado del material
  
---

### Scripts triggers
📄 [Ver Triggers](Documentaciones/Base%20Datos/ScriptTriggers(DEFINITIVO).sql)

---

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
<summary>⚙️ Explicación del Script</summary>

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

## 🌐 Ruta o enlace de la web

La aplicación desplegada está disponible en:

🔗 http://3.224.141.230/

## 🖼️ Capturas

### Página principal

![Página Principal](Documentaciones/Lenguaje%20de%20Marcas/CapturaPaginaPrincipal.png)

---

### Inventario 3D

![Inventario](Documentaciones/Lenguaje%20de%20Marcas/CapturaInventario.png)

---


📄 Vista 3D

📄 [Abrir Codigo Inventario 3D](Web/inventario_3d.html)



# 💻 Aplicación Java

## 📖 Descripción

Aplicación de escritorio desarrollada en Java Swing para la gestión del inventario del taller de informática.

La aplicación permite administrar materiales, localizar componentes y gestionar préstamos mediante distintos perfiles de usuario.

---

## ✅ Funcionalidades

- Login con roles
- CRUD de inventario
- Gestión de préstamos
- Localización de material
- Generación de informes
- Importación/exportación CSV Y TXT

---

## 🏗️ Decisiones de Diseño

### Patrón Singleton

Utilizado para mantener una única conexión con la base de datos.

---

### Patrón DAO

Separación entre acceso a datos y lógica de negocio.

---

### Gestión por Roles

Diferenciación entre:

- Administrador
- Profesor

---

## 📂 Estructura del Proyecto Java

```text
/src
├── dao
├── model
├── view
├── controller
└── utils
```

---

## 📐 Diagrama de clases

📄 [Ver diagrama de clases](Documentaciones/Entornos%20de%20Desarrollo/Diagrama%20de%20clases.png)

---

## 🔄 Casos de uso

📄 [Ver casos de uso](Documentaciones/Entornos%20de%20Desarrollo/Diagrama%20de%20casos%20de%20uso.jpg)

---

## 📝 Documentación Técnica (JavaDoc)

🔗 [Explorar JavaDoc Interactivo del Equipo 4](http://3.224.141.230/apidocs/index.html)

---

## 📸 Capturas de la Aplicación

### Pantalla de Login

![Captura guía usuario](Documentaciones/Im%C3%A1genes%20para%20la%20guia%20de%20usuario/Captura%20de%20pantalla%202026-05-18%20151332.png)

---

### Gestión de Inventario

![Captura](Documentaciones/Imágenes%20para%20la%20guia%20de%20usuario/Captura%20de%20pantalla%202026-05-21%20150846.png)

---

### Gestión de Préstamos

![Captura](Documentaciones/Im%C3%A1genes%20para%20la%20guia%20de%20usuario/Captura%20de%20pantalla%202026-05-18%20152050.png)

---

# ☁️ Infraestructura y Despliegue

## 🏗️ Arquitectura AWS

La infraestructura se desplegó utilizando AWS Academy con dos instancias EC2 separadas para servicios y base de datos.

---

## ⚙️ EC2-1 — Servidor de Datos

- Ubuntu Server
- MySQL
- Elastic IP
- Puerto 3306 restringido

---

## 🌐 EC2-2 — Servidor Web

- Apache/Nginx
- SFTP
- Puerto 80 abierto

---

## 🔐 Seguridad

- Security Groups configurados
- Restricción SSH
- Control de acceso por IP

---



📘 Guía de Despliegue

La guía completa se encuentra en:


📄 [Ver Guía de Despliegue](Documentaciones/Sistemas/Guia-despliegue-Equipo4%20(Finalizado).pdf)


---


📗 Manual de Usuario

El manual de usuario se encuentra en:

📄 [Abrir Manual de Usuario](Documentaciones/Manual-usuario-Equipo4.pdf)

---


# 🚀 Resultados Obtenidos

- Aplicación Java funcional
- Base de datos conectada en AWS
- Sitio web operativo
- Gestión de inventario implementada
- Infraestructura desplegada correctamente

---


# 📜 Licencia

Consultar el archivo:

```text
LICENSE
```

---

## ✅ Valoración de lo realizado

El equipo ha cumplido satisfactoriamente todos los objetivos principales del reto. Las partes que mejor resultado han dado han sido la aplicación Java, la base de datos con sus triggers y la infraestructura desplegada en AWS. El trabajo en equipo ha fluido de forma coordinada desde el principio, lo que ha permitido avanzar con ritmo constante a lo largo de las dos semanas.

Lo más desafiante a nivel técnico ha sido trabajar con conceptos de programación y bases de datos que aún no habíamos visto en clase, como los triggers o ciertos patrones de diseño, lo que nos ha obligado a investigar y aprender de forma autónoma durante el desarrollo del proyecto.

## 🔧 Mejoras propuestas

El proyecto cubre todas las funcionalidades planteadas. De cara a una versión futura, se podrían explorar mejoras como la exportación de datos en formatos adicionales, la implementación de alta disponibilidad en AWS o una interfaz web más dinámica con conexión directa a la base de datos en tiempo real.


---
# 🌍 Webgrafía

- [AWS Documentation](https://docs.aws.amazon.com/)
- [Amazon EC2 Documentation](https://docs.aws.amazon.com/ec2/)
- [Oracle Java Documentation](https://docs.oracle.com/en/java/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [MDN Web Docs](https://developer.mozilla.org/)
- [GitHub Docs](https://docs.github.com/)
- [Apache HTTP Server Documentation](https://httpd.apache.org/docs/)
- [Draw.io](https://www.drawio.com/)
