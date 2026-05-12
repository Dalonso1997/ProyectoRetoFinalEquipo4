USE inventario_taller;

DROP TRIGGER IF EXISTS controlNegativo;

DELIMITER //
CREATE TRIGGER controlNegativo
BEFORE UPDATE ON inventario
FOR EACH ROW
BEGIN
	IF NEW.cantidad<0 THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Stock insuficiente';
	END IF;
END //
DELIMITER ;

SELECT * FROM inventario;

UPDATE inventario
SET cantidad = cantidad -5
WHERE id_inventario=3;

SHOW TRIGGERS FROM inventario_taller;
DROP TABLE aux_control_movimientos;
CREATE TABLE IF NOT EXISTS aux_control_movimientos (
    id_registro INT AUTO_INCREMENT PRIMARY KEY,
    id_inventario INT NOT NULL,
    id_material INT NOT NULL, 
    cantidad_anterior INT,
    cantidad_nueva INT,
    fecha_movimiento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_inventario) REFERENCES inventario(id_inventario)
);

DROP TRIGGER IF EXISTS control_mov;

DELIMITER //

CREATE TRIGGER control_mov 
AFTER UPDATE ON inventario
FOR EACH ROW
BEGIN
    IF OLD.cantidad <> NEW.cantidad THEN
        INSERT INTO aux_control_movimientos (
            id_inventario, 
            id_material, 
            cantidad_anterior, 
            cantidad_nueva
        )
        VALUES (
            OLD.id_inventario, 
            OLD.id_material, 
            OLD.cantidad, 
            NEW.cantidad
        );
    END IF;
END //

DELIMITER ;
select * from aux_control_movimientos;