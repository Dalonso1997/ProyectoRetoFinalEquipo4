
-- -----------------------------------------------------------
--     TRIGGERS ACTUALIZADOS A LA BASE DE DATOS NUEVA		--
-- -----------------------------------------------------------



-- TRIGGER PARA EL REGISTRO DE MOVIMIENTOS EN LA CANTIDAD DE MATERIALES

DELIMITER $$

CREATE TRIGGER trg_cantidad_material -- Creacion del trigger
AFTER UPDATE ON materiales -- Decimos cuando se debe ejecutar el trigger
FOR EACH ROW -- Definimos que se debera ejecutar en cada fila
BEGIN -- Inicio del trigger
	IF OLD.cantidad <> NEW.cantidad THEN -- Si despues de la update la cantidad se ha movido, OLD hace referencia a como estaba ese campo antes del update, new hace referencia a como queda despues1
		INSERT INTO registro ( -- insertamos en registro los siguientes datos
			tipo_movimiento,
            cantidad_anterior,
            cantidad_nueva,
            fecha,
            id_material
		) VALUES (
			'modificacion_cantidad', -- Escribimos en el registro que el cambio que se ha hecho es de cantidad
            OLD.cantidad, -- guardamos en la cantidad anterior lo que habia en el campo previo al update
            NEW.cantidad, -- guardamos en la cantidad nueva lo que hay despues
            NOW(), -- guardamos como fecha la del momento del disparo del trigger
            NEW.id_material -- id del material que ha sido modificado
		);
	END IF;
END $$

DELIMITER ;	
			
-- TRIGGER PARA EL REGISTRO DE CAMBIOS DE ESTADO

DELIMITER $$ 

CREATE TRIGGER trg_estado_material -- Creamos el trigger
AFTER UPDATE ON materiales -- Decretamos que se dispara en el update de materiales
FOR EACH ROW -- para todas las filas
BEGIN 
	IF OLD.id_estado <> NEW.id_estado THEN -- si hay cambio de estado, anadimos los campos a la tabla de registro
		INSERT INTO registro (
			tipo_movimiento,
            estado_anterior,
            estado_nuevo,
            fecha,
            id_material
		) VALUES (
			'modificacion_estado',
            (SELECT nombre FROM estado WHERE id_estado = OLD.id_estado), -- Usamos subconsultas para que lo que aparezca en el campo estado anterior/nuevo sea el texto pj: "disponble", "prestado"
            (SELECT nombre FROM estado WHERE id_estado = NEW.id_estado),
            NOW(),
            NEW.id_material
		);
	END IF;
END $$

DELIMITER ;

-- Trigger para cambiar estado de producto a prestado cuando el material quede en 0

DELIMITER //

CREATE TRIGGER trg_actualizar_estado
BEFORE UPDATE ON materiales
FOR EACH ROW
BEGIN
    -- comprobamos si la cantidad nueva que se va a guardar es exactamente cero
    IF NEW.cantidad = 0 THEN
        -- buscamos de forma dinamica la id del estado 'prestado' y se la asignamos al material
        SET NEW.id_estado = (SELECT id_estado FROM estado WHERE nombre = 'prestado');
    END IF;
END //

DELIMITER ;

-- Trigger para cambiar estado cuando pasa cantidad a 0 o de 0 a mas

DELIMITER //

CREATE TRIGGER trg_actualizar_estado
BEFORE UPDATE ON materiales
FOR EACH ROW
BEGIN
    IF NEW.cantidad = 0 THEN
        SET NEW.id_estado = (SELECT id_estado FROM estado WHERE nombre = 'prestado');
	ELSE IF OLD.cantidad = 0 AND NEW.cantidad>0 THEN
		SET NEW.id_estado = (SELECT id_estado FROM estado WHERE nombre = 'disponible');
    END IF;
    END IF;
END //

DELIMITER ;

-- TRIGGER PARA REGISTRAR EL ALTA DE UN NUEVO MATERIAL

DROP TRIGGER anadido_material;

DELIMITER //

CREATE TRIGGER anadido_material AFTER INSERT ON materiales FOR EACH ROW
BEGIN

INSERT INTO registro (tipo_movimiento, cantidad_nueva, estado_nuevo, fecha, id_material) VALUES 
('alta', 
new.cantidad,
(SELECT nombre from estado where id_estado = new.id_estado),
curdate(),
new.id_material
);

END //
DELIMITER ;



SELECT * FROM materiales;

SELECT * FROM registro;


