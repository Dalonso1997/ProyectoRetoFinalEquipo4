
-- Eliminada de la tabla ubicacion el campo balda --
ALTER TABLE ubicacion DROP COLUMN balda;


-- Creo un procedure para anadir automaticamente los cajones al armarioA --
DROP PROCEDURE IF EXISTS generarCajonesArmarioA;

-- vuelvo a anadir el armario raiz

INSERT INTO ubicacion (ubicacion, descripcion) VALUES ('armarioA','Armario para almacenar dispositivos electronicos');

DELIMITER //
drop procedure generarCajonesArmarioA;
CREATE PROCEDURE generarCajonesArmarioA()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 39 DO
        -- Ahora solo insertamos armario y cajon
        INSERT INTO ubicacion (ubicacion, cajon) 
        VALUES ('armarioA', i);
        SET i = i + 1;
    END WHILE;
END //
DELIMITER ;

-- Ejecútalo para llenar la tabla
CALL generarCajonesArmarioA();

-- compruebo que se han creado correctamente --
select * from ubicacion;


-- Aprovecho tambien para anadir a la base de datos las mesas que hay en el taller ademas, cambio el nombre del campo armario por el de ubicacion, ya que seran mesas o armario --
ALTER TABLE ubicacion 
RENAME COLUMN armario TO ubicacion;


Insert into ubicacion (ubicacion) values ('mesaA');
Insert into ubicacion (ubicacion) values ('mesaB');
Insert into ubicacion (ubicacion) values ('mesaC');

-- Creo un procedure para eliminar todos los cajones creados ya que los cree con un nombre erroneo
DELIMITER //
drop procedure eliminarUbicacionesPorArmario;
CREATE PROCEDURE eliminarUbicacionesPorArmario(IN nombre_armario VARCHAR(255))
BEGIN
    -- Borramos todas las ubicaciones que coincidan con el nombre pasado
    DELETE FROM ubicacion 
    WHERE ubicacion = nombre_armario;
    
    -- Opcional: Mostrar cuántas filas se han borrado
    SELECT ROW_COUNT() AS filas_eliminadas;
END //

DELIMITER ;

CALL eliminarUbicacionesPorArmario('Armario A');

-- Elimino todos los datos en la tabla ubicacion, para limpiar las ID ya que al usar delete, empezaban desde la 41, ademas de eso, checkeo todas las foreign key
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE materiales;
TRUNCATE TABLE ubicacion;
SET FOREIGN_KEY_CHECKS = 1;



-- cambio tambien el tipo de dato de cajon para que sean INT asi en la Aplicacion saldran ordenados
ALTER TABLE ubicacion 
MODIFY COLUMN cajon INT;