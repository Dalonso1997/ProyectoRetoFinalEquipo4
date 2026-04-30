

delimiter //

create function disponibilidad(numMatricula CHAR(7)) returns tinyint
deterministic
begin

declare alquilado_v int;

SELECT alquilado into alquilado_v from automoviles where matricula = numMatricula;

return alquilado_v;

end//

delimiter ;

select disponibilidad('1234JMY');


DELIMITER //

create procedure multiplicar (IN num int, out salida int)
begin

IF num < 49 and num > 0 then

	set salida = num * 2;
    
elseif num >= 49 and num < 100 then

	set salida = num * 4;

else
		set salida = num;
end if;

end//

delimiter ;

call multiplicar(51,@salida);

select @salida;

use concursomusica;
drop procedure repartirPremios;
delimiter //
create procedure repartirPremios()
begin
declare usuarioRandom varchar(15);

drop table if exists premios ;
create temporary table if not exists premios(premio int, nombre varchar(15), apellidos varchar(30));

repeat

	select usuario into usuarioRandom from usuarios order by rand() limit 1;
		insert into premios (premio, nombre, apellidos) values (
			
            concat(floor(rand()*1000), 'E'),
            (select nombre from usuarios where usuario = usuarioRandom),
            (select apellidos from usuarios where usuario = usuarioRandom)
            );
            
until (select count(*) from premios) = 10
end repeat;

select * from premios;
END//
delimiter ;

call repartirPremios();