# Issues simplificadas - Reto Gestión Taller

Versión sin IPE y sin cuadernos diarios. Cada issue está pensada para que un alumno pueda asignársela y saber qué hacer sin pedir contexto extra. Duración máxima: 5 horas.

Etiquetas base recomendadas:

- `BD`
- `PROG`
- `SI`
- `LMSGI`
- `ED`
- `DOC`
- `ENTREGA`
- `OPCIONAL`

## Fechas de entrega obligatoria

Estas son las fechas que deben marcarse en GitHub Projects con un campo tipo `Fecha entrega`:

- 2026-05-04: Wiki con Contrato de equipo y Roles, repositorio creado y responsable AWS designado.
- 2026-05-05: Project configurado, etiquetas creadas y plantilla de cuaderno preparada.
- 2026-05-06: Diagrama de clases, plantilla de Guía de despliegue y diagrama E/R.
- 2026-05-07: VPC, Internet Gateway, subredes, EC2-1, Security Group, MySQL y carga inicial de BD.
- 2026-05-08: EC2-2 inicial, Security Group, diagrama AWS, pantalla de inicio y conexión a BD. XSLT CSV es opcional.
- 2026-05-11: Modelo relacional y script de creación de BD.
- 2026-05-13: EC2-2 finalizado, HTTP/SSH/SFTP verificados, diagrama de casos de uso y README base con diagramas.
- 2026-05-15: Plantilla de Manual de usuario y requisitos HW/SW documentados.
- 2026-05-18: Licencia elegida, comparada y archivo LICENSE añadido.
- 2026-05-19: Guía de despliegue definitiva, README actualizado y desarrollo básico Java cerrado.
- 2026-05-20: Trigger, informes Java, web HTML/CSS documentada, Manual de usuario final y pruebas finales de servidores.
- 2026-05-21: Arquitectura AWS final con IDs/IPs/SG/VPC, pruebas finales y depuración.
- 2026-05-22: Presentación, demostración, coevaluaciones y cierre.

## Bases de Datos

### BD-01 - Analizar datos y ubicaciones del inventario
Etiquetas: `BD`  
Estimación: 2 h

Descripción:
Revisar la guía y definir qué información debe guardar la base de datos del taller. Deben aparecer, como mínimo, categorías de material, estado, ubicación física y cantidad.

Para cerrar:
- Lista de categorías: red, hardware, herramientas, fungible y equipos completos.
- Lista de estados: disponible, prestado, en reparación y baja.
- Ubicaciones definidas con armario, balda y cajón.
- Campos mínimos del material escritos en un documento o README.

### BD-02 - Diseñar el modelo E/R
Etiquetas: `BD`, `ENTREGA`  
Estimación: 4 h
Fecha límite: 2026-05-06

Descripción:
Crear el diagrama entidad/relación de la base de datos a partir de los requisitos del inventario. Debe permitir gestionar materiales, categorías, ubicaciones, usuarios, préstamos/devoluciones y movimientos si se usan para el trigger.

Para cerrar:
- Diagrama E/R creado en Diagrams.net o herramienta similar.
- Entidades, relaciones y cardinalidades visibles.
- El modelo permite localizar material por armario, balda y cajón.
- Imagen exportada al repositorio.

### BD-03 - Convertir el E/R en modelo relacional
Etiquetas: `BD`, `ENTREGA`  
Estimación: 3 h
Fecha límite: 2026-05-11

Descripción:
Transformar el diagrama E/R en tablas relacionales. Definir claves primarias, claves foráneas, tipos de datos y restricciones necesarias.

Para cerrar:
- Modelo relacional documentado.
- Tablas, PK y FK definidas.
- Restricciones principales indicadas.
- Imagen o documento subido al repositorio.

### BD-04 - Crear script SQL de tablas
Etiquetas: `BD`, `ENTREGA`  
Estimación: 4 h
Fecha límite: 2026-05-11

Descripción:
Crear el script SQL que genere la base de datos MySQL desde cero. Debe incluir todas las tablas necesarias para que la aplicación Java funcione.

Para cerrar:
- Archivo `.sql` subido en una carpeta tipo `database`.
- El script crea la base de datos y todas las tablas.
- Incluye PK, FK y restricciones básicas.
- Se ejecuta sin errores en MySQL.

### BD-05 - Añadir datos de prueba
Etiquetas: `BD`  
Estimación: 3 h

Descripción:
Completar el script SQL con datos ficticios para probar la aplicación. Los datos deben cubrir todas las categorías, estados y ubicaciones principales.

Para cerrar:
- Hay materiales de todas las categorías.
- Hay materiales en distintos estados.
- Hay ubicaciones suficientes para probar filtros y localización.
- Hay usuarios de prueba para Administrador y Profesor.

### BD-06 - Preparar consultas SQL para la aplicación
Etiquetas: `BD`, `PROG`  
Estimación: 3 h

Descripción:
Escribir y probar las consultas que necesitará Programación: listado completo, filtros por categoría/estado/ubicación, búsqueda por nombre, préstamos e informes.

Para cerrar:
- Consultas probadas en MySQL.
- Consultas compartidas con el equipo de Programación.
- No hay consultas que dependan de datos inventados fuera del modelo.
- Las consultas usan nombres de campos definitivos.

### BD-07 - Crear trigger de la base de datos
Etiquetas: `BD`, `ENTREGA`  
Estimación: 3 h
Fecha límite: 2026-05-20

Descripción:
Implementar al menos un trigger relevante. Por ejemplo: registrar en una tabla histórica los cambios de estado del material o controlar movimientos de stock.

Para cerrar:
- Script de trigger separado o claramente identificado.
- Tabla auxiliar creada si hace falta.
- Caso de prueba documentado.
- El trigger se ejecuta correctamente.

### BD-08 - Probar la base de datos desde cero
Etiquetas: `BD`  
Estimación: 2 h

Descripción:
Comprobar que una persona del equipo puede crear la base de datos en limpio usando solo los scripts del repositorio.

Para cerrar:
- Scripts ejecutados en orden correcto.
- Datos de prueba cargados.
- Trigger probado.
- Errores corregidos o documentados.

## Programación

### PROG-01 - Crear proyecto Java Swing en NetBeans
Etiquetas: `PROG`  
Estimación: 2 h

Descripción:
Crear el proyecto base en NetBeans con Swing. Preparar la estructura de paquetes para modelo, DAO, vistas, controladores y utilidades.

Para cerrar:
- Proyecto compila.
- Estructura de paquetes creada.
- Conector MySQL añadido o documentado.
- Proyecto subido al repositorio.

### PROG-02 - Crear clases modelo
Etiquetas: `PROG`  
Estimación: 3 h

Descripción:
Crear las clases Java que representan los datos principales: Material, Categoría, Ubicación, Usuario, Préstamo y cualquier clase adicional que indique el modelo.

Para cerrar:
- Atributos coherentes con la base de datos.
- Constructores y getters/setters implementados.
- Código compila.
- Clases preparadas para usarse desde los DAO.

### PROG-03 - Implementar conexión Singleton a MySQL
Etiquetas: `PROG`, `BD`  
Estimación: 3 h

Descripción:
Crear una clase de conexión a la base de datos usando el patrón Singleton. La conexión debe poder apuntar a MySQL en AWS o a una BD local de pruebas.

Para cerrar:
- Singleton implementado.
- No se suben contraseñas reales al repositorio.
- Conexión probada.
- Los errores de conexión se muestran de forma clara.

### PROG-04 - Crear DAO de usuarios y login
Etiquetas: `PROG`  
Estimación: 3 h

Descripción:
Implementar el acceso a datos para usuarios. Debe validar usuario, contraseña y rol para diferenciar Administrador y Profesor.

Para cerrar:
- DAO de usuarios creado.
- Login consulta la base de datos.
- Se obtiene correctamente el rol.
- Consultas parametrizadas, sin concatenar contraseñas en SQL.

### PROG-05 - Crear pantalla de login
Etiquetas: `PROG`, `ENTREGA`  
Estimación: 4 h
Fecha límite: 2026-05-08

Descripción:
Crear la ventana Swing de inicio de sesión. Debe pedir usuario y contraseña, validar campos vacíos y abrir la aplicación según el rol.

Para cerrar:
- Pantalla funcional.
- Mensajes de error si faltan datos o credenciales son incorrectas.
- Entrada correcta como Administrador.
- Entrada correcta como Profesor.

### PROG-06 - Crear menú principal por rol
Etiquetas: `PROG`  
Estimación: 3 h

Descripción:
Crear la ventana principal de la aplicación. Las opciones deben cambiar según el perfil: Administrador tiene acceso completo y Profesor solo consulta, localización, préstamos y devoluciones.

Para cerrar:
- Menú principal creado.
- Opciones separadas por perfil.
- Profesor no puede acceder a CRUD completo.
- Navegación básica entre módulos.

### PROG-07 - Mostrar listado completo de inventario
Etiquetas: `PROG`  
Estimación: 4 h

Descripción:
Crear el DAO y la pantalla que muestra todos los materiales del inventario en una tabla.

Para cerrar:
- Tabla Swing con materiales.
- Se muestran nombre, descripción, categoría, estado, ubicación y cantidad.
- Datos cargados desde MySQL.
- Mensaje claro si no hay datos o falla la consulta.

### PROG-08 - Añadir filtros y búsqueda de inventario
Etiquetas: `PROG`  
Estimación: 3 h

Descripción:
Permitir buscar material por texto y filtrar por categoría, estado y ubicación.

Para cerrar:
- Búsqueda por nombre o texto.
- Filtro por categoría.
- Filtro por estado.
- Filtro por ubicación.

### PROG-09 - Alta de material para Administrador
Etiquetas: `PROG`  
Estimación: 3 h

Descripción:
Crear formulario para que el Administrador pueda añadir material nuevo.

Para cerrar:
- Formulario de alta creado.
- Validación de campos obligatorios.
- Inserta en base de datos.
- Refresca el listado tras guardar.

### PROG-10 - Modificación de material para Administrador
Etiquetas: `PROG`  
Estimación: 3 h

Descripción:
Permitir que el Administrador seleccione un material existente y modifique sus datos.

Para cerrar:
- Carga datos actuales del material.
- Permite editar campos necesarios.
- Guarda cambios en MySQL.
- Muestra confirmación o error.

### PROG-11 - Baja o eliminación controlada de material
Etiquetas: `PROG`  
Estimación: 2 h

Descripción:
Permitir que el Administrador dé de baja un material o lo elimine, según lo decidido por el equipo.

Para cerrar:
- Acción disponible solo para Administrador.
- Pide confirmación antes de ejecutar.
- Actualiza el listado.
- No rompe préstamos o históricos existentes.

### PROG-12 - Gestión de ubicaciones
Etiquetas: `PROG`  
Estimación: 3 h

Descripción:
Crear o ajustar la gestión de ubicaciones físicas para armario, balda y cajón. Debe servir para asignar materiales a lugares concretos.

Para cerrar:
- Ubicaciones visibles desde formularios.
- Se pueden crear o seleccionar ubicaciones.
- No se duplican ubicaciones innecesariamente.
- Funciona con filtros y localización.

### PROG-13 - Localización de material y apertura de web
Etiquetas: `PROG`, `LMSGI`  
Estimación: 3 h

Descripción:
Crear el módulo que, al buscar un material, muestra su ubicación exacta y abre el sitio web del taller en el navegador.

Para cerrar:
- Muestra armario, balda y cajón.
- Abre la web de visualización.
- Funciona para Administrador y Profesor.
- Informa si no encuentra el material.

### PROG-14 - Registro de préstamos
Etiquetas: `PROG`  
Estimación: 4 h

Descripción:
Permitir registrar préstamos de material. Debe quedar guardado quién presta, qué material, cantidad si aplica y fecha.

Para cerrar:
- Formulario de préstamo creado.
- Valida que el material se pueda prestar.
- Guarda el préstamo.
- Actualiza estado o stock según el modelo.

### PROG-15 - Registro de devoluciones
Etiquetas: `PROG`  
Estimación: 3 h

Descripción:
Permitir devolver material prestado. La aplicación debe mostrar préstamos abiertos y registrar la devolución.

Para cerrar:
- Lista préstamos pendientes.
- Permite marcar devolución.
- Actualiza estado o stock.
- Evita devoluciones duplicadas.

### PROG-16 - Exportar inventario a CSV
Etiquetas: `PROG`  
Estimación: 3 h

Descripción:
Añadir una opción para exportar el inventario a un archivo CSV.

Para cerrar:
- Exporta datos con cabecera.
- El CSV se abre correctamente en Excel o similar.
- Informa dónde se ha guardado.
- Controla errores de escritura.

### PROG-17 - Importar inventario desde CSV
Etiquetas: `PROG`  
Estimación: 4 h

Descripción:
Añadir una opción para cargar materiales desde un CSV con formato conocido.

Para cerrar:
- Formato del CSV documentado.
- Valida campos obligatorios.
- Inserta filas correctas.
- Informa filas con error.

### PROG-18 - Informe de inventario completo
Etiquetas: `PROG`, `ENTREGA`  
Estimación: 2 h
Fecha límite: 2026-05-20

Descripción:
Generar un informe en formato texto con el listado completo del inventario.

Para cerrar:
- Informe generado en `.txt`.
- Incluye campos principales.
- Se puede guardar desde la aplicación.
- Mensaje de éxito o error.

### PROG-19 - Informes por categoría/estado y localización
Etiquetas: `PROG`, `ENTREGA`  
Estimación: 3 h
Fecha límite: 2026-05-20

Descripción:
Generar informes de inventario filtrados por categoría/estado y por armario/balda.

Para cerrar:
- Informe por categoría y estado.
- Informe por ubicación.
- Salida en texto legible.
- Datos coinciden con la base de datos.

### PROG-20 - Validaciones y mensajes de la aplicación
Etiquetas: `PROG`  
Estimación: 3 h

Descripción:
Revisar la aplicación para que todas las operaciones importantes muestren mensajes claros y validen campos antes de guardar o consultar.

Para cerrar:
- Mensajes de éxito.
- Mensajes de error.
- Validación de campos vacíos.
- La aplicación no se cierra ante errores normales.

### PROG-21 - Crear ejecutable o JAR final
Etiquetas: `PROG`, `ENTREGA`  
Estimación: 2 h
Fecha límite: 2026-05-22

Descripción:
Preparar una versión ejecutable de la aplicación Java.

Para cerrar:
- JAR o ejecutable generado.
- Dependencias explicadas.
- Instrucciones básicas de ejecución.
- Probado en otro equipo si es posible.

### PROG-22 - Prueba completa de la aplicación
Etiquetas: `PROG`  
Estimación: 4 h

Descripción:
Probar la aplicación completa con los dos perfiles de usuario.

Para cerrar:
- Prueba con Administrador.
- Prueba con Profesor.
- Prueba con base de datos en AWS.
- Errores críticos corregidos o anotados.

### PROG-23 - Cerrar desarrollo básico de la aplicación Java
Etiquetas: `PROG`, `ENTREGA`  
Estimación: 2 h
Fecha límite: 2026-05-19

Descripción:
Revisar que la aplicación ya tiene todos los módulos obligatorios funcionando de forma básica antes de dedicar tiempo a correcciones, documentación final o mejoras.

Para cerrar:
- Login y roles funcionan.
- Inventario, filtros, CRUD, localización, préstamos, devoluciones, importación/exportación e informes existen.
- La aplicación conecta con MySQL.
- Los fallos pendientes son correcciones, no módulos completos sin empezar.

## Lenguajes de Marcas

### LMSGI-01 - Crear estructura HTML del sitio web
Etiquetas: `LMSGI`  
Estimación: 2 h

Descripción:
Crear la estructura básica del sitio web con HTML5. Debe incluir header, nav, main y footer.

Para cerrar:
- Archivos HTML creados.
- Header, nav, main y footer presentes.
- Navegación básica funcionando.
- Contenido inicial del taller añadido.

### LMSGI-02 - Crear hoja CSS común
Etiquetas: `LMSGI`  
Estimación: 3 h

Descripción:
Crear una hoja CSS3 común para todo el sitio web, con diseño limpio y funcional.

Para cerrar:
- CSS enlazado desde HTML.
- Uso de Flexbox o Grid.
- Colores y fuentes coherentes.
- Elementos interactivos con estilo visible.

### LMSGI-03 - Diseñar plano visual del taller
Etiquetas: `LMSGI`, `ENTREGA`  
Estimación: 4 h
Fecha límite: 2026-05-20

Descripción:
Representar visualmente el taller con armarios, baldas y zonas principales.

Para cerrar:
- Plano visible en la web.
- Armarios y baldas identificados.
- Diseño legible.
- Ubicaciones coinciden con la base de datos.

### LMSGI-04 - Crear listado de material por ubicación
Etiquetas: `LMSGI`  
Estimación: 3 h

Descripción:
Añadir a la web un listado de materiales agrupados por armario, balda o zona.

Para cerrar:
- Material agrupado por ubicación.
- Categorías principales visibles.
- Información coherente con datos de prueba.
- Fácil de consultar.

### LMSGI-05 - Crear anclas para abrir ubicaciones desde Java
Etiquetas: `LMSGI`, `PROG`  
Estimación: 2 h

Descripción:
Preparar IDs o enlaces internos en la web para que la aplicación Java pueda abrir una ubicación concreta.

Para cerrar:
- Cada zona importante tiene `id`.
- Enlaces internos probados.
- Programación conoce el formato de URL.
- Funciona al desplegar la web.

### LMSGI-06 - Validar y probar la web
Etiquetas: `LMSGI`  
Estimación: 3 h

Descripción:
Comprobar que el HTML y CSS son válidos y que la web se ve bien en más de un navegador o tamaño de pantalla.

Para cerrar:
- HTML validado.
- CSS validado.
- Probado en dos navegadores o tamaños.
- Sin textos solapados ni partes ilegibles.

### LMSGI-07 - Documentar la web en README
Etiquetas: `LMSGI`, `DOC`  
Estimación: 2 h

Descripción:
Explicar en el README la estructura del sitio web, estilos utilizados y contenido incluido.

Para cerrar:
- Sección web en README.
- Explicación de estructura HTML.
- Explicación de CSS.
- Enlace o ruta a la web.

### LMSGI-08 - Opcional: XSLT para CSV
Etiquetas: `LMSGI`, `OPCIONAL`  
Estimación: 3 h
Fecha límite: 2026-05-08, solo si se hace

Descripción:
Solo hacer si sobra tiempo. Crear una plantilla XSLT que transforme un XML de inventario en CSV.

Para cerrar:
- XML de prueba creado.
- XSLT genera CSV.
- Resultado probado.
- README indica que es ampliación opcional.

### LMSGI-09 - Opcional: XSLT para HTML
Etiquetas: `LMSGI`, `OPCIONAL`  
Estimación: 3 h
Fecha límite: 2026-05-20, solo si se hace

Descripción:
Solo hacer si sobra tiempo. Crear una plantilla XSLT que genere parte del HTML del inventario desde XML.

Para cerrar:
- XSLT genera HTML.
- Salida revisada.
- Archivos subidos.
- README lo marca como opcional.

## Sistemas Informáticos

### SI-01 - Crear plantilla de la Guía de despliegue
Etiquetas: `SI`, `DOC`, `ENTREGA`  
Estimación: 2 h
Fecha límite: 2026-05-06

Descripción:
Crear el documento base de la Guía de despliegue en Word/Teams.

Para cerrar:
- Documento creado con nombre correcto.
- Portada preparada.
- Índice preparado.
- Apartados mínimos creados.

### SI-02 - Diseñar arquitectura AWS y diagrama de red
Etiquetas: `SI`, `ENTREGA`  
Estimación: 4 h
Fecha límite: 2026-05-08

Descripción:
Diseñar la arquitectura AWS del reto: VPC, Internet Gateway, dos subredes públicas, EC2-1, EC2-2, Elastic IPs y Security Groups.

Para cerrar:
- Arquitectura decidida.
- Diagrama de red creado.
- Reglas de puertos indicadas.
- Imagen lista para guía y README.

### SI-03 - Crear VPC, Internet Gateway y subredes
Etiquetas: `SI`, `ENTREGA`  
Estimación: 4 h
Fecha límite: 2026-05-07

Descripción:
Crear en AWS Academy la VPC del reto, el Internet Gateway y dos subredes públicas en zonas distintas.

Para cerrar:
- VPC creada.
- Internet Gateway asociado.
- Subred pública AZ-A.
- Subred pública AZ-B.
- Rutas públicas configuradas.

### SI-04 - Crear EC2-1 con Elastic IP y Security Group
Etiquetas: `SI`, `ENTREGA`  
Estimación: 3 h
Fecha límite: 2026-05-07

Descripción:
Lanzar la instancia EC2-1 para el servidor de datos MySQL.

Para cerrar:
- EC2-1 en AZ-A.
- Elastic IP asociada.
- Puerto 3306 restringido a IP del IES.
- Puerto 22 restringido a IP del IES.

### SI-05 - Instalar y configurar MySQL en EC2-1
Etiquetas: `SI`, `BD`  
Estimación: 4 h

Descripción:
Instalar MySQL en EC2-1 y dejarlo preparado para recibir la base de datos del proyecto.

Para cerrar:
- MySQL instalado y activo.
- Usuario de aplicación creado.
- Acceso configurado según la guía.
- No se publican contraseñas reales.

### SI-06 - Cargar base de datos en EC2-1
Etiquetas: `SI`, `BD`  
Estimación: 3 h

Descripción:
Ejecutar los scripts SQL del proyecto en MySQL dentro de EC2-1 y probar que la aplicación podrá conectarse.

Para cerrar:
- BD creada en EC2-1.
- Datos de prueba cargados.
- Conexión remota probada.
- Datos de conexión comunicados al equipo sin exponer secretos.

### SI-07 - Crear EC2-2 con Elastic IP y Security Group
Etiquetas: `SI`, `ENTREGA`  
Estimación: 3 h
Fecha límite: 2026-05-08

Descripción:
Lanzar la instancia EC2-2 para servidor web y SFTP.

Para cerrar:
- EC2-2 en AZ-B.
- Elastic IP asociada.
- Puerto 80 abierto.
- Puerto 22 restringido a IP del IES.

### SI-08 - Instalar Apache o Nginx en EC2-2
Etiquetas: `SI`  
Estimación: 3 h

Descripción:
Instalar y configurar el servidor web elegido en EC2-2.

Para cerrar:
- Apache o Nginx instalado.
- Servicio activo.
- Página de prueba accesible por HTTP.
- Elección documentada.

### SI-09 - Configurar SSH/SFTP en EC2-2
Etiquetas: `SI`  
Estimación: 3 h

Descripción:
Configurar y probar acceso SSH y transferencia SFTP en EC2-2 usando la clave `.pem`.

Para cerrar:
- SSH funciona.
- SFTP permite subir archivos.
- SFTP permite bajar archivos.
- Procedimiento documentado.

### SI-10 - Subir la web al servidor
Etiquetas: `SI`, `LMSGI`  
Estimación: 2 h

Descripción:
Subir los archivos HTML/CSS al servidor EC2-2 para que la web esté disponible desde navegador.

Para cerrar:
- Archivos subidos.
- Web accesible por Elastic IP.
- Permisos correctos.
- URL compartida con Programación.

### SI-11 - Probar HTTP, SSH y SFTP
Etiquetas: `SI`  
Estimación: 2 h

Descripción:
Hacer pruebas finales de acceso a EC2-2.

Para cerrar:
- HTTP por puerto 80 probado.
- SSH probado.
- SFTP probado.
- Evidencias guardadas para la guía.

### SI-12 - Documentar EC2-1 en la Guía de despliegue
Etiquetas: `SI`, `DOC`  
Estimación: 3 h

Descripción:
Redactar en la guía el proceso de creación, configuración y uso de EC2-1.

Para cerrar:
- Lanzamiento de instancia explicado.
- MySQL explicado.
- Security Group justificado.
- Carga de BD y conexión Java explicadas.

### SI-13 - Documentar EC2-2 en la Guía de despliegue
Etiquetas: `SI`, `DOC`  
Estimación: 3 h

Descripción:
Redactar en la guía el proceso de creación, configuración y uso de EC2-2.

Para cerrar:
- Lanzamiento de instancia explicado.
- Apache/Nginx explicado.
- SSH/SFTP explicado.
- Security Group justificado.

### SI-14 - Comparar subred pública y privada
Etiquetas: `SI`, `DOC`  
Estimación: 2 h

Descripción:
Añadir a la guía una explicación breve de la diferencia entre subred pública y privada, y justificar por qué en el reto se usa subred pública.

Para cerrar:
- Diferencia explicada.
- Limitación de AWS Academy mencionada.
- Security Groups justificados.
- Texto añadido a la guía.

### SI-15 - Comparar licencias y añadir LICENSE
Etiquetas: `SI`, `DOC`, `ENTREGA`  
Estimación: 3 h
Fecha límite: 2026-05-18

Descripción:
Comparar MIT, GPL v3 y Apache 2.0, elegir una licencia para el proyecto y añadir el archivo LICENSE al repositorio.

Para cerrar:
- Comparativa redactada.
- Licencia elegida y justificada.
- Archivo LICENSE añadido.
- Apartado incluido en el manual.

### SI-16 - Documentar requisitos HW/SW de la aplicación
Etiquetas: `SI`, `DOC`, `ENTREGA`  
Estimación: 2 h
Fecha límite: 2026-05-15

Descripción:
Redactar en el Manual de usuario los requisitos para ejecutar la aplicación Java.

Para cerrar:
- Versión mínima de JRE.
- Sistema operativo compatible.
- RAM, procesador, disco y resolución.
- Conectividad necesaria con AWS.

### SI-17 - Generar PDF final de Guía de despliegue
Etiquetas: `SI`, `DOC`, `ENTREGA`  
Estimación: 4 h
Fecha límite: 2026-05-19

Descripción:
Revisar la Guía de despliegue, convertirla a PDF, subirla al repositorio y enlazarla en el README.

Para cerrar:
- PDF generado.
- Subido al repositorio.
- Enlazado en README.
- Revisión visual y ortográfica hecha.

### SI-18 - Actualizar arquitectura AWS final con IDs e IPs
Etiquetas: `SI`, `DOC`, `ENTREGA`  
Estimación: 2 h
Fecha límite: 2026-05-21

Descripción:
Actualizar la documentación final de AWS con los identificadores reales de la infraestructura desplegada.

Para cerrar:
- IDs de EC2 incluidos.
- Elastic IPs incluidas.
- IDs o nombres de Security Groups incluidos.
- ID de VPC y subredes incluido.
- No se publican claves privadas ni contraseñas.

### SI-19 - Pruebas finales de servidores y clientes
Etiquetas: `SI`, `ENTREGA`  
Estimación: 3 h
Fecha límite: 2026-05-20

Descripción:
Comprobar que la infraestructura completa funciona antes de la entrega final.

Para cerrar:
- Java conecta con MySQL en EC2-1.
- Web accesible por HTTP en EC2-2.
- SSH probado en ambas instancias.
- SFTP probado en EC2-2.
- Evidencias añadidas a la Guía de despliegue.

## Entornos de Desarrollo y documentación

### ED-01 - Configurar GitHub Project y etiquetas
Etiquetas: `ED`, `ENTREGA`  
Estimación: 2 h
Fecha límite: 2026-05-05

Descripción:
Crear el tablero GitHub Project y las etiquetas necesarias para organizar las issues por asignatura.

Para cerrar:
- Estados básicos creados: Backlog, Ready, In progress, Review, Done.
- Etiquetas BD, PROG, SI, LMSGI, ED, DOC, ENTREGA y OPCIONAL.
- Issues añadidas al Project.
- El equipo entiende cómo mover tareas.

### ED-02 - Crear plantilla de issue de cuaderno de trabajo
Etiquetas: `ED`, `DOC`, `ENTREGA`  
Estimación: 2 h
Fecha límite: 2026-05-05

Descripción:
Crear la plantilla de issue para el cuaderno diario. No hace falta crear una issue diaria en el backlog; el secretario la rellenará cada día al terminar.

Para cerrar:
- Archivo `.github/ISSUE_TEMPLATE/cuaderno_trabajo.md`.
- Incluye asistentes, tareas, acuerdos, bloqueos y evidencias.
- Plantilla probada creando un cuaderno.
- Etiqueta de cuaderno preparada.

### ED-03 - Publicar contrato y roles en Wiki
Etiquetas: `ED`, `DOC`, `ENTREGA`  
Estimación: 2 h
Fecha límite: 2026-05-04

Descripción:
Publicar en GitHub Wiki las páginas obligatorias de Contrato de equipo y Roles.

Para cerrar:
- Contrato de equipo publicado.
- Roles publicados.
- Responsable AWS indicado.
- Política de rotación incluida.

### ED-04 - Definir flujo Git del equipo
Etiquetas: `ED`  
Estimación: 2 h

Descripción:
Definir cómo trabajará el equipo con ramas, commits y revisiones.

Para cerrar:
- Convención de ramas.
- Convención de mensajes de commit.
- Norma para no subir secretos.
- Proceso de revisión antes de cerrar issues.

### ED-05 - Crear diagrama de clases completo
Etiquetas: `ED`, `ENTREGA`  
Estimación: 4 h
Fecha límite: 2026-05-06

Descripción:
Crear el diagrama de clases de la aplicación Java.

Para cerrar:
- Atributos con tipo.
- Métodos con parámetros.
- Relaciones y cardinalidades.
- Imagen subida al repositorio.

### ED-06 - Crear diagrama de casos de uso
Etiquetas: `ED`, `ENTREGA`  
Estimación: 3 h
Fecha límite: 2026-05-13

Descripción:
Crear el diagrama de casos de uso cubriendo Administrador y Profesor.

Para cerrar:
- Actor Administrador.
- Actor Profesor.
- Casos de uso principales incluidos.
- Imagen subida al repositorio.

### ED-07 - Crear README base
Etiquetas: `ED`, `DOC`  
Estimación: 2 h
Fecha límite: 2026-05-13

Descripción:
Crear o completar el README inicial del repositorio.

Para cerrar:
- Nombre del reto.
- Descripción corta.
- Miembros del equipo.
- Índice y estructura básica.

### ED-08 - Documentar BD, web, Java y despliegue en README
Etiquetas: `ED`, `DOC`  
Estimación: 4 h
Fecha límite: 2026-05-19

Descripción:
Completar el README con las secciones técnicas mínimas indicadas en la guía.

Para cerrar:
- Sección de base de datos con diagramas y script.
- Sección de web.
- Sección de Java con decisiones de diseño.
- Sección de despliegue con enlaces a guía y manual.

### ED-09 - Revisar JavaDoc
Etiquetas: `ED`, `PROG`  
Estimación: 3 h

Descripción:
Comprobar que las clases Java tienen documentación JavaDoc útil.

Para cerrar:
- Clases principales documentadas.
- Métodos importantes documentados.
- JavaDoc generado sin errores graves.
- Documentación subida o enlazada.

### ED-10 - Preparar Manual de usuario final
Etiquetas: `ED`, `DOC`, `ENTREGA`  
Estimación: 5 h
Fecha límite: 2026-05-20

Descripción:
Montar el Manual de usuario final en PDF usando la información de Programación y Sistemas.

Para cerrar:
- Portada e índice.
- Requisitos HW/SW.
- Licencia.
- Guía por perfil con capturas.
- Informes explicados.
- PDF subido y enlazado.

### ED-11 - Preparar presentación final
Etiquetas: `ED`, `DOC`, `ENTREGA`  
Estimación: 4 h
Fecha límite: 2026-05-22

Descripción:
Crear la presentación final del equipo para exponer el proyecto.

Para cerrar:
- Organización del equipo.
- Tecnologías utilizadas.
- Desarrollo realizado.
- Valoración y mejoras.
- Enlace en README.

### ED-12 - Preparar demo y checklist final
Etiquetas: `ED`, `ENTREGA`  
Estimación: 3 h
Fecha límite: 2026-05-22

Descripción:
Preparar el orden de la demostración final y comprobar que todo lo entregable está enlazado.

Para cerrar:
- Demo de aplicación.
- Demo de web.
- Revisión de AWS.
- README con enlaces finales.
- Sin claves ni contraseñas en el repositorio.

### ED-13 - Cerrar README final con todos los resultados
Etiquetas: `ED`, `DOC`, `ENTREGA`  
Estimación: 3 h
Fecha límite: 2026-05-22

Descripción:
Revisar y cerrar el README final del repositorio para que sirva como memoria del reto y enlace todos los entregables.

Para cerrar:
- Nombre del reto y descripción corta.
- Miembros del equipo.
- Resultados obtenidos por módulo.
- Tecnologías utilizadas.
- Valoración y mejoras propuestas.
- Bibliografía/webgrafía.
- Enlaces a guía, manual, presentación, web, scripts, diagramas, ejecutable y código.
