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
SET cantidad = cantidad -4
WHERE id_inventario=3;
