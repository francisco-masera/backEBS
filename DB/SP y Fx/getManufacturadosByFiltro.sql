create definer = root@localhost procedure getManufacturadosByFiltro(IN params varchar(255))
BEGIN

DROP TABLE IF EXISTS Resultados;
	CREATE TEMPORARY TABLE Resultados
	(
		idArticuloVenta bigint PRIMARY KEY not null,
		descripcion varchar(255) null,
		imagen varchar(255) null,
		precioVenta float null,
		aptoCeliaco bit(1) null,
		denominacion varchar(255) null,
		tiempoCocina int null,
		vegano bit(1) null,
		vegetariano bit(1) null,
		idArticuloManufacturado bigint null,
		idRubro bigint null,
        idInsumo bigint null,
        idStock bigint null
	);

DROP TABLE IF EXISTS Terminos;
CREATE TABLE Terminos (
    Termino VARCHAR(255)
);

INSERT INTO Terminos SELECT `SPLIT_STRING`(params, ' ', 1);

INSERT INTO	Resultados(
		idArticuloVenta, 
        descripcion,
		imagen,
		precioVenta, 
        aptoCeliaco,
		denominacion,
		tiempoCocina,
		vegano, 
		vegetariano,
        idArticuloManufacturado,
		idRubro
        )
        
    SELECT 
		ia.idArticuloVenta, 
        ia.descripcion,
		ia.imagen,
		ia.precioVenta,
        am.aptoCeliaco,
        am.denominacion,
		am.tiempoCocina,
		am.vegano, 
		am.vegetariano,
        am.idArticuloManufacturado,
		am.idRubro

	FROM informacionarticuloventa ia
    INNER JOIN articulomanufacturado am ON ia.idArticuloVenta = am.idArticuloManufacturado
    INNER JOIN (SELECT Termino FROM Terminos) as t
    WHERE am.baja = 0
    AND(ia.descripcion LIKE CONCAT('%', t.Termino,'%')
      OR am.denominacion LIKE CONCAT('%', t.Termino,'%'));
    
SELECT 
    *
FROM
    Resultados;
    
DROP TABLE IF EXISTS Terminos;
END;

