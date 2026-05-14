-- USUARIO
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol ENUM('Administrador', 'Profesor') NOT NULL DEFAULT 'Profesor'
);
-- CATEGORIAS
CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);
-- UBICACION
CREATE TABLE ubicacion (
    id_ubicacion INT AUTO_INCREMENT PRIMARY KEY,
    ubicacion VARCHAR(50) NOT NULL,
    cajon INT,
    descripcion VARCHAR(255)
);
-- MATERIALES
CREATE TABLE materiales (
    id_material INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255),
    cantidad INT NOT NULL DEFAULT 0 CHECK (cantidad >= 0),
    estado ENUM('disponible', 'prestado', 'reparacion', 'baja') NOT NULL DEFAULT 'disponible',
    fecha_alta DATE NOT NULL,
    id_categoria INT NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
        ON UPDATE CASCADE ON DELETE RESTRICT
);
-- PRESTAMOS
CREATE TABLE prestamos (
    id_prestamo INT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE,
    observaciones TEXT,
    id_material INT NOT NULL,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_material) REFERENCES materiales(id_material)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
        ON UPDATE CASCADE ON DELETE RESTRICT
);
-- REGISTRO (histórico de movimientos)
CREATE TABLE registro (
    id_registro INT AUTO_INCREMENT PRIMARY KEY,
    tipo_movimiento ENUM('entrada', 'salida', 'prestamo', 'devolucion', 'baja') NOT NULL,
    cantidad INT NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    observaciones TEXT,
    id_material INT NOT NULL,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_material) REFERENCES materiales(id_material)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
        ON UPDATE CASCADE ON DELETE RESTRICT
);
CREATE TABLE inventario (
    id_inventario INT AUTO_INCREMENT PRIMARY KEY,
    fecha_registro DATETIME ,
    cantidad INT NOT NULL,
    id_material INT NOT NULL,
    id_ubicacion INT NOT NULL,
    FOREIGN KEY (id_material) REFERENCES materiales(id_material)
        ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY (id_ubicacion) REFERENCES ubicacion(id_ubicacion)
	ON UPDATE CASCADE ON DELETE RESTRICT
);