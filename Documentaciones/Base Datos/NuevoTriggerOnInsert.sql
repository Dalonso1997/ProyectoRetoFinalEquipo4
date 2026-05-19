
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