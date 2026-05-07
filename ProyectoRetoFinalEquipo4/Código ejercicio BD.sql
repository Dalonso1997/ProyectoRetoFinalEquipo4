CREATE PROCEDURE cargar_direcciones(IN num_min INT UNSIGNED, IN num_max INT
UNSIGNED)
BEGIN
	DECLARE i INT;
	DECLARE resp BOOLEAN;
	SET i=num_min;
-- Mientras i no haya llegado al número máximo del intervalo
 -- Asigna direccion y localidad aleatorias a la persona con múmero i
	WHILE i<= num_max DO
	CALL asigna_direccion(i,resp);
	SET i=i+1;
	END WHILE;
END 