-- ============================================================
--  BASE DE DATOS: Gestión del Taller de Informática
--  IES Miguel Herrero Pereda — Reto Intermodular DAM1 2025/26
-- ============================================================
 
DROP DATABASE IF EXISTS inventario_taller;
CREATE DATABASE inventario_taller
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_spanish_ci;
 
USE inventario_taller;
 
-- ------------------------------------------------------------
-- CATEGORIAS
-- Tipos de material del taller
-- ------------------------------------------------------------
CREATE TABLE categorias (
    id_categoria   INT            NOT NULL AUTO_INCREMENT,
    nombre         VARCHAR(50)    NOT NULL,
    descripcion    VARCHAR(255),
    CONSTRAINT pk_categorias PRIMARY KEY (id_categoria),
    CONSTRAINT uq_categorias_nombre UNIQUE (nombre)
);
 
-- ------------------------------------------------------------
-- UBICACION
-- Mesas (sin baldas ni cajones) y ArmarioA (con cajones)
--   tipo  = 'mesa'    → solo nombre (ej: 'Mesa 1'), cajon NULL
--   tipo  = 'armario' → nombre = 'ArmarioA', cajon = número
-- ------------------------------------------------------------
CREATE TABLE ubicacion (
    id_ubicacion   INT            NOT NULL AUTO_INCREMENT,
    tipo           ENUM('mesa','armario') NOT NULL,
    nombre         VARCHAR(50)    NOT NULL,   -- 'Mesa 1', 'Mesa 2', 'ArmarioA'
    cajon          TINYINT UNSIGNED,          -- NULL para mesas; 1-N para armario
    descripcion    VARCHAR(255),
    CONSTRAINT pk_ubicacion PRIMARY KEY (id_ubicacion),
    CONSTRAINT uq_ubicacion UNIQUE (nombre, cajon),
    CONSTRAINT chk_cajon CHECK (
        (tipo = 'mesa'    AND cajon IS NULL) OR
        (tipo = 'armario' AND cajon IS NOT NULL)
    )
);
 
-- ------------------------------------------------------------
-- ESTADO
-- Tabla catálogo: evita ENUMs rígidos y permite ampliar sin
-- alterar la estructura de la tabla materiales
-- ------------------------------------------------------------
CREATE TABLE estado (
    id_estado      INT            NOT NULL AUTO_INCREMENT,
    nombre         VARCHAR(50)    NOT NULL,   -- disponible | prestado | en reparación | baja
    CONSTRAINT pk_estado PRIMARY KEY (id_estado),
    CONSTRAINT uq_estado_nombre UNIQUE (nombre)
);
 
-- ------------------------------------------------------------
-- USUARIOS
-- Perfiles: administrador | profesor
-- ------------------------------------------------------------
CREATE TABLE usuarios (
    id_usuario     INT            NOT NULL AUTO_INCREMENT,
    nombre         VARCHAR(50)    NOT NULL,
    apellidos      VARCHAR(150)   NOT NULL,
    email          VARCHAR(200)   NOT NULL,
    password       VARCHAR(255)   NOT NULL,   -- hash bcrypt
    rol            ENUM('administrador','profesor') NOT NULL DEFAULT 'profesor',
    activo         TINYINT(1)     NOT NULL DEFAULT 1,
    fecha_alta     DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_usuarios PRIMARY KEY (id_usuario),
    CONSTRAINT uq_usuarios_email UNIQUE (email)
);
 
-- ------------------------------------------------------------
-- MATERIALES
-- Entidad central del inventario
-- ------------------------------------------------------------
CREATE TABLE materiales (
    id_material    INT            NOT NULL AUTO_INCREMENT,
    nombre         VARCHAR(100)   NOT NULL,
    descripcion    VARCHAR(255),
    cantidad       INT UNSIGNED   NOT NULL DEFAULT 0,
    fecha_alta     DATE           NOT NULL DEFAULT (CURRENT_DATE),
    id_categoria   INT            NOT NULL,
    id_estado      INT            NOT NULL,
    id_ubicacion   INT            NOT NULL,
    CONSTRAINT pk_materiales      PRIMARY KEY (id_material),
    CONSTRAINT fk_mat_categoria   FOREIGN KEY (id_categoria)
        REFERENCES categorias(id_categoria),
    CONSTRAINT fk_mat_estado      FOREIGN KEY (id_estado)
        REFERENCES estado(id_estado),
    CONSTRAINT fk_mat_ubicacion   FOREIGN KEY (id_ubicacion)
        REFERENCES ubicacion(id_ubicacion),
    CONSTRAINT chk_cantidad CHECK (cantidad >= 0)
);
 
-- ------------------------------------------------------------
-- PRESTAMOS
-- Registro operacional de préstamos activos y cerrados
-- ------------------------------------------------------------
CREATE TABLE prestamos (
    id_prestamo      INT          NOT NULL AUTO_INCREMENT,
    cantidad         INT UNSIGNED NOT NULL DEFAULT 1,
    fecha_prestamo   DATE         NOT NULL DEFAULT (CURRENT_DATE),
    fecha_devolucion DATE,                  -- NULL = préstamo aún activo
    observaciones    TEXT,
    id_material      INT          NOT NULL,
    id_usuario       INT          NOT NULL,
    CONSTRAINT pk_prestamos      PRIMARY KEY (id_prestamo),
    CONSTRAINT fk_pres_material  FOREIGN KEY (id_material)
        REFERENCES materiales(id_material),
    CONSTRAINT fk_pres_usuario   FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id_usuario),
    CONSTRAINT chk_fechas CHECK (
        fecha_devolucion IS NULL OR fecha_devolucion >= fecha_prestamo
    ),
    CONSTRAINT chk_cantidad_pres CHECK (cantidad > 0)
);
 
-- ------------------------------------------------------------
-- REGISTRO (histórico)
-- Alimentado automáticamente por triggers.
-- Nunca se escribe manualmente desde la aplicación.
-- ------------------------------------------------------------
CREATE TABLE registro (
    id_registro       INT          NOT NULL AUTO_INCREMENT,
    tipo_movimiento   ENUM(
                        'alta',
                        'baja',
                        'modificacion_estado',
                        'modificacion_cantidad',
                        'prestamo',
                        'devolucion'
                      )            NOT NULL,
    cantidad_anterior INT,
    cantidad_nueva    INT,
    estado_anterior   VARCHAR(50),
    estado_nuevo      VARCHAR(50),
    observaciones     TEXT,
    fecha             DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_material       INT          NOT NULL,
    id_usuario        INT,                  -- NULL si lo genera el sistema
    CONSTRAINT pk_registro       PRIMARY KEY (id_registro),
    CONSTRAINT fk_reg_material   FOREIGN KEY (id_material)
        REFERENCES materiales(id_material),
    CONSTRAINT fk_reg_usuario    FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id_usuario)
);