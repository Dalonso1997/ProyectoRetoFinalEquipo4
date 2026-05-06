# Plan de GitHub Projects y backlog del reto

Reto: Gestion y localizacion del material del taller de Informatica.

Duracion oficial: del lunes 4 de mayo de 2026 al viernes 22 de mayo de 2026, 15 dias lectivos.

## Modulos y etiquetas obligatorias

Cada issue del backlog debe llevar una etiqueta del modulo principal al que pertenece:

| Etiqueta | Modulo |
| --- | --- |
| `mod:BD` | Bases de Datos |
| `mod:PROG` | Programacion |
| `mod:SI` | Sistemas Informaticos |
| `mod:LMSGI` | Lenguajes de Marcas y Sistemas de Gestion de Informacion |
| `mod:ED` | Entornos de Desarrollo |
| `mod:IPEI` | Itinerario Personal para la Empleabilidad I |

Etiquetas auxiliares recomendadas:

| Etiqueta | Uso |
| --- | --- |
| `tipo:entrega` | Tarea con entrega marcada en el calendario oficial |
| `tipo:documentacion` | README, guia, manual, wiki, diagramas o evidencias |
| `tipo:infraestructura` | AWS, EC2, MySQL, servidor web, SSH, SFTP |
| `tipo:java` | Aplicacion Java Swing |
| `tipo:web` | Sitio HTML/CSS/XSLT |
| `tipo:bd` | Modelado, SQL, datos, triggers |
| `cuaderno-trabajo` | Issue diario obligatorio del secretario |
| `prio:alta` | Bloquea tareas posteriores o tiene fecha cercana |
| `bloqueante` | Otra tarea depende directamente de esta |

## Campos del tablero

Columnas recomendadas:

1. `Backlog`
2. `Ready`
3. `In progress`
4. `Review`
5. `Done`

Campos personalizados recomendados:

| Campo | Valores |
| --- | --- |
| `Modulo` | BD, PROG, SI, LMSGI, ED, IPEI |
| `Estimacion` | 5h por defecto |
| `Fecha objetivo` | Fecha de entrega o fecha sugerida |
| `Alumno slot` | A, B, C, D, E |
| `Tipo` | entrega, desarrollo, documentacion, infraestructura, prueba |

## Regla de rotacion diaria

Cada alumno debe tomar una tarea de unas 5 horas al dia. Para asegurar que todos trabajan todas las asignaturas:

1. Asignad a cada alumno un slot fijo: A, B, C, D y, si sois cinco, E.
2. Cada dia, el alumno escoge la tarea que tenga su slot sugerido.
3. Nadie repite modulo hasta haber pasado por BD, PROG, SI, LMSGI, ED e IPEI, salvo urgencia por entrega.
4. Las tareas con `tipo:entrega`, `prio:alta` o `bloqueante` tienen prioridad.
5. Al cerrar una tarea, debe haber evidencia: commit, captura, enlace en README, archivo generado o comentario en la issue.
6. Al final de cada jornada se crea el issue de cuaderno de trabajo con etiqueta `cuaderno-trabajo`, asignado a todo el equipo.

Para equipos de 4 alumnos: usad los slots A-D y convertid las tareas de slot E en tareas de pareja, revision o reserva para quien termine antes.

## Fechas de entrega obligatoria

| Fecha | Entregas principales |
| --- | --- |
| 2026-05-06 | Diagrama de clases, plantilla de Guia de despliegue, diseno E/R, inicio de infraestructura, web y Java |
| 2026-05-07 | VPC, Internet Gateway, subredes, EC2-1 con MySQL, Elastic IP, Security Group y carga inicial de BD |
| 2026-05-08 | EC2-2, Apache/Nginx y SFTP iniciados, diagrama AWS, pantalla inicial Java, conexion BD, tarea IPEI mapa profesional |
| 2026-05-11 | Modelo relacional y script de creacion de BD |
| 2026-05-12 | Desarrollo Java y perfil profesional inicial IPEI |
| 2026-05-13 | EC2-2 finalizado, accesos HTTP/SSH/SFTP verificados, Guia con diagrama AWS, casos de uso, README inicial |
| 2026-05-15 | Plantilla manual de usuario, requisitos HW/SW, informes Java, pruebas servidor web/SFTP/SSH, competencias IPEI |
| 2026-05-18 | Licencia elegida, LICENSE, pruebas finales de servidor, inicio manual usuario |
| 2026-05-19 | Guia de despliegue definitiva, README actualizado, Java basico finalizado |
| 2026-05-20 | Triggers BD, informes Java, web documentada, manual de usuario final, entorno personal de aprendizaje IPEI |
| 2026-05-21 | Pruebas finales, documentacion AWS completa, manual usuario, matriz de riesgos laborales IPEI |
| 2026-05-22 | Presentacion final, coevaluaciones y cierre profesional del reto |

## Definition of Done comun

Una issue se considera cerrada solo si cumple:

- Tiene etiqueta de modulo.
- Tiene estimacion aproximada de 5h.
- Tiene enlace o referencia a la evidencia generada.
- Si genera codigo, hay commit y prueba basica.
- Si genera documentacion, esta enlazada desde README o Wiki cuando proceda.
- Si tiene fecha oficial, queda marcada como `tipo:entrega`.

## Issues del backlog

El archivo [`issues-backlog.csv`](issues-backlog.csv) contiene una propuesta de issues con modulo, fecha objetivo, slot de alumno, etiquetas y criterios de aceptacion resumidos.
