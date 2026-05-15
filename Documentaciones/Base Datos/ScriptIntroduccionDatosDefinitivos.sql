-- reinicio de las tablas de las bases de datos incluyendo sus ids

-- Linea para deshabilitar las foreign keys y asi permitir el truncate
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE registro;
TRUNCATE TABLE prestamos;
TRUNCATE TABLE materiales;
TRUNCATE TABLE ubicacion;
TRUNCATE TABLE estado;
TRUNCATE TABLE categorias;
TRUNCATE TABLE usuarios;

-- Volvemos a activar las foreign keys
SET FOREIGN_KEY_CHECKS = 1;


-- Comenzamos a llenar la base de datos con los datos reales a utilizar


-- -----------------------------------------
		-- TABLA USUARIOS --
-- -----------------------------------------

INSERT INTO usuarios (nombre, apellidos,email,password,rol,activo,fecha_alta)
VALUES (
		'David','Alonso','alonso@david.es','1234','administrador',true,now()
        );

INSERT INTO usuarios (nombre, apellidos,email,password,rol,activo,fecha_alta)
VALUES (
		'Aday','Fernandez','aday@fernandez.es','1234','administrador',true,now()
        );

UPDATE usuarios SET email = 'fernandez@aday.es' where nombre = 'Aday' and apellidos = 'Fernandez';
        
INSERT INTO usuarios (nombre, apellidos,email,password,rol,activo,fecha_alta)
VALUES (
		'Sergio','Camacho','camacho@sergio.es','1234','administrador',true,now()
        );

INSERT INTO usuarios (nombre, apellidos,email,password,rol,activo,fecha_alta)
VALUES (
		'Alberto','Gonzalez','gonzalez@adrian.es','1234','administrador',true,now()
        );

-- update al usuario de alberto porque me equivoco y pongo el email de adrian
UPDATE usuarios SET email = 'gonzalez@alberto.es' where nombre = 'Alberto' and apellidos = 'Gonzalez';  

INSERT INTO usuarios (nombre, apellidos,email,password,rol,activo,fecha_alta)
VALUES (
		'Adrian','Gonzalez','gonzalez@adrian.es','1234','administrador',true,now()
        );

UPDATE usuarios SET rol = 'profesor' where nombre = 'Alberto';
UPDATE usuarios SET rol = 'profesor' where nombre = 'Adrian';

-- Compruebo los cambios
SELECT * FROM usuarios;

-- ---------------------------------------
		-- TABLA CATEGORIAS --
-- ---------------------------------------

INSERT INTO categorias (nombre,descripcion)
VALUES (
	'Componentes de red','Utiles para la conexion de red'
    );

INSERT INTO categorias (nombre,descripcion)
VALUES (
	'Componentes hardware','placas base, procesadores, módulos de RAM, discos duros, SSD, fuentes de 
alimentación, tarjetas gráficas, etc.'
    );

INSERT INTO categorias (nombre,descripcion)
VALUES (
	'Herramientas','destornilladores, pulseras antiestáticas, polímetros, etc.'
    );

INSERT INTO categorias (nombre,descripcion)
VALUES (
	'Material fungible','tornillos, bridas, pasta térmica, etc.'
    );
    
INSERT INTO categorias (nombre,descripcion)
VALUES (
	'Equipos completos','PCs, portátiles, Raspberry Pi, etc.'
    );

-- compruebo que se han anadido correctamente las categorias
SELECT * FROM categorias;


-- -------------------------------------------
		-- TABLA ESTADO --
-- -------------------------------------------

INSERT INTO estado (nombre)
VALUES ('disponible');

INSERT INTO estado (nombre)
VALUES ('prestado');

INSERT INTO estado (nombre)
VALUES ('en reparacion');

INSERT INTO estado (nombre)
VALUES ('baja');


-- --------------------------------------------
		-- TABLA UBICACION --
-- --------------------------------------------
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE ubicacion;
SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 2', '1','Material Soldadura Regletas');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 2', '2','Cableado (SATA, VGA, Alimentacion)');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 2', '3','Material Soldadura: Alfombrillas, soportes, soldadores');
    
INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 2', '4','Memorias RAM');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 2', '5','Discos Duros');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 3', '1','Tarjetas Graficas/Tarjetas de red');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 3', '2','Pasta termica');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 3', '3','Fuentes de alimentacion');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 3', '4','Herramientas: destornilladores, tijeras');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 3', '5','Herramientas: Crimpadoras');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 4', '1','Material para placas base');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 4', '2','Placas base');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 4', '3','Router 4G | Portatiles');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 4', '4','Tornilleria');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 4', '5','Tarjetas Graficas');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 5', '1','Destornilladores de precision');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 5', '2','Tarjetas Graficas');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 5', '3','Placas base AMD');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 5', '4','Tarjetas Graficas');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 5', '5','Placas base Intel');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 5', '6','Placas base Intel');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 5', '7','Discos SSD / WebCam');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 5', '8','Fuentes de alimentacion');
    
INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 5', '9','Microprocesadores Intel');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 5', '10','Microprocesadores AMD');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 5', '11','Multimetros');

INSERT INTO ubicacion (tipo,nombre,cajon,descripcion)
VALUES (
	'armario', 'Armario 5', '12','Fuentes de alimentacion');


-- ============================================
--         TABLA MATERIALES (Generada por IA, dandole el contexto y lo que se necesitaba para su correcto funcionamiento)
-- ============================================
-- Categorías (referencia):
--   1 = Componentes de red
--   2 = Componentes hardware
--   3 = Herramientas
--   4 = Material fungible
--   5 = Equipos completos
--
-- Estados (referencia):
--   1 = disponible
--   2 = prestado
--   3 = en reparacion
--   4 = baja
--
-- Ubicaciones generadas por el INSERT previo:
--   Las IDs se asignan en el orden en que se insertaron:
--
--   id  | armario | cajón | descripción
--   1   | 2       | 1     | Material Soldadura Regletas
--   2   | 2       | 2     | Cableado (SATA, VGA, Alimentacion)
--   3   | 2       | 3     | Material Soldadura: Alfombrillas, soportes, soldadores
--   4   | 2       | 4     | Memorias RAM
--   5   | 2       | 5     | Discos Duros
--   6   | 3       | 1     | Tarjetas Graficas/Tarjetas de red
--   7   | 3       | 2     | Pasta termica
--   8   | 3       | 3     | Fuentes de alimentacion
--   9   | 3       | 4     | Herramientas: destornilladores, tijeras
--   10  | 3       | 5     | Herramientas: Crimpadoras
--   11  | 4       | 1     | Material para placas base
--   12  | 4       | 2     | Placas base
--   13  | 4       | 3     | Router 4G | Portatiles
--   14  | 4       | 4     | Tornilleria
--   15  | 4       | 5     | Tarjetas Graficas
--   16  | 5       | 1     | Destornilladores de precision
--   17  | 5       | 2     | Tarjetas Graficas
--   18  | 5       | 3     | Placas base AMD
--   19  | 5       | 4     | Tarjetas Graficas
--   20  | 5       | 5     | Placas base Intel
--   21  | 5       | 6     | Placas base Intel
--   22  | 5       | 7     | Discos SSD / WebCam
--   23  | 5       | 8     | Fuentes de alimentacion
--   24  | 5       | 9     | Microprocesadores Intel
--   25  | 5       | 10    | Microprocesadores AMD
--   26  | 5       | 11    | Multimetros
--   27  | 5       | 12    | Fuentes de alimentacion
-- ============================================


-- -------------------------------------------
-- ARMARIO 2
-- -------------------------------------------

-- Cajón 1 - Material Soldadura Regletas (cat: 4 Material fungible)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Regletas de soldadura', 'Regletas para conexiones eléctricas en soldadura', 12, CURDATE(), 4, 1, 1);

-- Cajón 2 - Cableado (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Cable SATA', 'Cables SATA para conexión de almacenamiento', 18, CURDATE(), 2, 1, 2);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Cable VGA', 'Cables VGA para conexión de monitores', 10, CURDATE(), 2, 1, 2);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Cable de alimentación ATX', 'Cables de alimentación para fuentes ATX', 14, CURDATE(), 2, 1, 2);

-- Cajón 3 - Material Soldadura (cat: 3 Herramientas)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Alfombrilla antiestática', 'Alfombrilla para trabajo antiestático', 6, CURDATE(), 3, 1, 3);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Soporte para soldador', 'Soporte metálico con esponja para soldador', 5, CURDATE(), 3, 1, 3);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Soldador de estaño', 'Soldador eléctrico de 40W para electrónica', 8, CURDATE(), 3, 1, 3);

-- Cajón 4 - Memorias RAM (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Módulo RAM DDR3 4GB', 'Memoria RAM DDR3 de 4GB para sobremesa', 20, CURDATE(), 2, 1, 4);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Módulo RAM DDR4 8GB', 'Memoria RAM DDR4 de 8GB para sobremesa', 15, CURDATE(), 2, 1, 4);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Módulo RAM DDR4 4GB', 'Memoria RAM DDR4 de 4GB para sobremesa', 12, CURDATE(), 2, 1, 4);

-- Cajón 5 - Discos Duros (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Disco duro HDD 500GB', 'Disco duro mecánico de 500GB 3.5"', 10, CURDATE(), 2, 1, 5);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Disco duro HDD 1TB', 'Disco duro mecánico de 1TB 3.5"', 7, CURDATE(), 2, 1, 5);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Disco duro HDD 250GB', 'Disco duro mecánico de 250GB 3.5"', 8, CURDATE(), 2, 1, 5);


-- -------------------------------------------
-- ARMARIO 3
-- -------------------------------------------

-- Cajón 1 - Tarjetas Gráficas / Tarjetas de red (cat: 2 Componentes hardware / 1 Componentes de red)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Tarjeta gráfica GT 710', 'Tarjeta gráfica NVIDIA GeForce GT 710 1GB', 6, CURDATE(), 2, 1, 6);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Tarjeta de red PCI Ethernet', 'Tarjeta de red PCI 10/100/1000 Mbps', 9, CURDATE(), 1, 1, 6);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Tarjeta de red WiFi PCI', 'Tarjeta de red inalámbrica PCI 300Mbps', 7, CURDATE(), 1, 1, 6);

-- Cajón 2 - Pasta térmica (cat: 4 Material fungible)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Pasta térmica Arctic MX-4', 'Pasta térmica de alta conductividad para procesadores', 15, CURDATE(), 4, 1, 7);

-- Cajón 3 - Fuentes de alimentación (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Fuente de alimentación 500W', 'Fuente ATX 500W para sobremesa', 5, CURDATE(), 2, 1, 8);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Fuente de alimentación 650W', 'Fuente ATX 650W para sobremesa', 4, CURDATE(), 2, 1, 8);

-- Cajón 4 - Herramientas: destornilladores, tijeras (cat: 3 Herramientas)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Destornillador Phillips PH2', 'Destornillador de estrella tamaño PH2', 10, CURDATE(), 3, 1, 9);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Destornillador plano', 'Destornillador plano mediano', 8, CURDATE(), 3, 1, 9);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Tijeras de electricista', 'Tijeras para corte de cables y bridas', 6, CURDATE(), 3, 1, 9);

-- Cajón 5 - Herramientas: Crimpadoras (cat: 3 Herramientas)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Crimpadora RJ45', 'Crimpadora para conectores RJ45 de red', 5, CURDATE(), 3, 1, 10);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Crimpadora RJ11', 'Crimpadora para conectores RJ11 de teléfono', 3, CURDATE(), 3, 1, 10);


-- -------------------------------------------
-- ARMARIO 4
-- -------------------------------------------

-- Cajón 1 - Material para placas base (cat: 4 Material fungible)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Pulsera antiestática', 'Pulsera antiestática con cable a tierra', 10, CURDATE(), 4, 1, 11);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Separadores de placa base', 'Separadores metálicos hex para montaje de placa base', 50, CURDATE(), 4, 1, 11);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Bracket trasero ATX', 'Chapa trasera I/O para diferentes modelos de placa base', 8, CURDATE(), 4, 1, 11);

-- Cajón 2 - Placas base (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Placa base micro-ATX socket AM4', 'Placa base micro-ATX compatible con procesadores AMD AM4', 4, CURDATE(), 2, 1, 12);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Placa base micro-ATX socket LGA1151', 'Placa base micro-ATX compatible con procesadores Intel LGA1151', 5, CURDATE(), 2, 1, 12);

-- Cajón 3 - Router 4G / Portátiles (cat: 1 Componentes de red / 5 Equipos completos)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Router 4G LTE', 'Router inalámbrico con soporte para SIM 4G LTE', 3, CURDATE(), 1, 1, 13);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Portátil Lenovo ThinkPad', 'Portátil Lenovo ThinkPad para prácticas de taller', 4, CURDATE(), 5, 1, 13);

-- Cajón 4 - Tornillería (cat: 4 Material fungible)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Tornillos M3 para HDD', 'Tornillos M3 para fijación de discos duros', 100, CURDATE(), 4, 1, 14);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Tornillos M2 para SSD', 'Tornillos M2 para fijación de discos SSD M.2', 80, CURDATE(), 4, 1, 14);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Bridas de nylon', 'Bridas para gestión de cableado interno', 200, CURDATE(), 4, 1, 14);

-- Cajón 5 - Tarjetas Gráficas (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Tarjeta gráfica GTX 1050', 'Tarjeta gráfica NVIDIA GeForce GTX 1050 2GB', 4, CURDATE(), 2, 1, 15);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Tarjeta gráfica RX 550', 'Tarjeta gráfica AMD Radeon RX 550 4GB', 3, CURDATE(), 2, 1, 15);


-- -------------------------------------------
-- ARMARIO 5
-- -------------------------------------------

-- Cajón 1 - Destornilladores de precisión (cat: 3 Herramientas)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Set destornilladores de precisión', 'Juego de destornilladores de precisión para electrónica (32 piezas)', 6, CURDATE(), 3, 1, 16);

-- Cajón 2 - Tarjetas Gráficas (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Tarjeta gráfica GTX 750 Ti', 'Tarjeta gráfica NVIDIA GeForce GTX 750 Ti 2GB', 5, CURDATE(), 2, 1, 17);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Tarjeta gráfica HD 7770', 'Tarjeta gráfica AMD Radeon HD 7770 1GB', 4, CURDATE(), 2, 1, 17);

-- Cajón 3 - Placas base AMD (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Placa base ATX socket AM4', 'Placa base ATX compatible con procesadores AMD Ryzen AM4', 3, CURDATE(), 2, 1, 18);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Placa base ATX socket AM3+', 'Placa base ATX compatible con procesadores AMD FX AM3+', 4, CURDATE(), 2, 1, 18);

-- Cajón 4 - Tarjetas Gráficas (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Tarjeta gráfica GTX 1060 3GB', 'Tarjeta gráfica NVIDIA GeForce GTX 1060 3GB', 3, CURDATE(), 2, 1, 19);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Tarjeta gráfica RX 580', 'Tarjeta gráfica AMD Radeon RX 580 8GB', 3, CURDATE(), 2, 1, 19);

-- Cajón 5 - Placas base Intel (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Placa base ATX socket LGA1200', 'Placa base ATX compatible con procesadores Intel 10ª gen LGA1200', 3, CURDATE(), 2, 1, 20);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Placa base ATX socket LGA1151 v2', 'Placa base ATX compatible con procesadores Intel 8ª/9ª gen LGA1151', 4, CURDATE(), 2, 1, 20);

-- Cajón 6 - Placas base Intel (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Placa base ATX socket LGA1700', 'Placa base ATX compatible con procesadores Intel 12ª/13ª gen LGA1700', 2, CURDATE(), 2, 1, 21);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Placa base mATX socket LGA1151', 'Placa base micro-ATX compatible con Intel 6ª/7ª gen LGA1151', 5, CURDATE(), 2, 1, 21);

-- Cajón 7 - Discos SSD / WebCam (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Disco SSD 2.5" 240GB', 'Disco SSD SATA 2.5" de 240GB', 10, CURDATE(), 2, 1, 22);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Disco SSD M.2 256GB', 'Disco SSD NVMe M.2 de 256GB', 6, CURDATE(), 2, 1, 22);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Webcam USB HD', 'Webcam USB 720p para prácticas de configuración', 8, CURDATE(), 2, 1, 22);

-- Cajón 8 - Fuentes de alimentación (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Fuente de alimentación 400W', 'Fuente ATX 400W económica para prácticas', 6, CURDATE(), 2, 1, 23);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Fuente de alimentación 750W', 'Fuente ATX 750W modular para equipos de alto rendimiento', 3, CURDATE(), 2, 1, 23);

-- Cajón 9 - Microprocesadores Intel (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Intel Core i5-9400F', 'Procesador Intel Core i5-9400F socket LGA1151 6 núcleos', 4, CURDATE(), 2, 1, 24);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Intel Core i3-10100', 'Procesador Intel Core i3-10100 socket LGA1200 4 núcleos', 5, CURDATE(), 2, 1, 24);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Intel Pentium G4560', 'Procesador Intel Pentium G4560 socket LGA1151 2 núcleos', 6, CURDATE(), 2, 1, 24);

-- Cajón 10 - Microprocesadores AMD (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('AMD Ryzen 5 3600', 'Procesador AMD Ryzen 5 3600 socket AM4 6 núcleos', 4, CURDATE(), 2, 1, 25);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('AMD Ryzen 3 2200G', 'Procesador AMD Ryzen 3 2200G socket AM4 4 núcleos con gráfica integrada', 5, CURDATE(), 2, 1, 25);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('AMD FX-8350', 'Procesador AMD FX-8350 socket AM3+ 8 núcleos', 3, CURDATE(), 2, 1, 25);

-- Cajón 11 - Multímetros (cat: 3 Herramientas)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Multímetro digital DT830B', 'Multímetro digital básico para mediciones eléctricas', 10, CURDATE(), 3, 1, 26);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Multímetro digital profesional', 'Multímetro digital con pantalla retroiluminada y auto-rango', 5, CURDATE(), 3, 1, 26);

-- Cajón 12 - Fuentes de alimentación (cat: 2 Componentes hardware)
INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Fuente de alimentación 550W', 'Fuente ATX 550W para prácticas de ensamblaje', 4, CURDATE(), 2, 1, 27);

INSERT INTO materiales (nombre, descripcion, cantidad, fecha_alta, id_categoria, id_estado, id_ubicacion)
VALUES ('Fuente de alimentación 600W', 'Fuente ATX 600W semi-modular para prácticas avanzadas', 3, CURDATE(), 2, 1, 27);


-- Compruebo los materiales insertados
SELECT m.id_material, m.nombre, m.cantidad, c.nombre AS categoria, e.nombre AS estado,
       CONCAT(u.nombre, ' - Cajón ', u.cajon) AS ubicacion
FROM materiales m
JOIN categorias c ON m.id_categoria = c.id_categoria
JOIN estado e ON m.id_estado = e.id_estado
JOIN ubicacion u ON m.id_ubicacion = u.id_ubicacion
ORDER BY u.nombre, u.cajon, m.nombre;


SELECT * FROM ubicacion;

SELECT * FROM prestamos;

SELECT * FROM registro;
