-- Script datos de prueba --

-- DATOS DE PRUEBA PARA CATEGORIAS --
INSERT INTO categorias (id_categoria,nombre,descripcion)
VALUES (1,"Herramientas","Destornilladores, taladros, metros");

select * from categorias;

-- DATOS DE PRUEBA PARA UBICACION --
INSERT INTO ubicacion (armario, balda, cajon, descripcion) VALUES ("Armario A", null, null, "Armario para guardar objetos electronicos");

select * from ubicacion;

-- DATOS DE PRUEBA PARA MATERIALES -- 

INSERT INTO materiales (nombre,descripcion,cantidad,estado,fecha_alta,id_categoria,id_ubicacion) VALUES  ("Switches de red", "Switch para la conexion de red", 50, "disponible", curdate(), 1, 1);

select * from materiales;

-- DATOS DE PRUEBA PARA INVENTARIO --

INSERT INTO inventario (fecha_registro,cantidad,id_material) VALUES (curdate(),3,1);
select * from inventario;

-- DATOS DE PRUEBA PARA USUARIO --
Insert into usuario (id_usuario, nombre, apellidos, email, password, rol, activo) VALUES
(1,"Adrian","Gonzalez","6767@6767.es","1234","Administrador",true);

select * from usuario;


-- DATOS DE PRUEBA PARA PRESTAMOS --
INSERT INTO prestamos (cantidad,fecha_prestamo,fecha_devolucion,observaciones,id_material,id_usuario) values (1,curdate(),curdate(),"Le falta un puerto", 1, 1);

select * from prestamos;

-- DATOS DE PRUEBA PARA REGISTRO --
INSERT INTO registro (tipo_movimiento,cantidad,fecha,observaciones,id_material,id_usuario) VALUES ("entrada", 25,curdate(),null,1,1);

select * from registro;

DELETE FROM registro where id_registro = 2;

