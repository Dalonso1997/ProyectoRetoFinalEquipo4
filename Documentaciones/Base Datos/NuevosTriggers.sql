
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
