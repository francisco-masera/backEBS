create definer = root@localhost procedure getInsumosVentaByFiltro(IN params varchar(255))
BEGIN

DROP TABLE IF EXISTS Resultados;
	CREATE TEMPORARY TABLE Resultados
	(
		idArticuloVenta bigint PRIMARY KEY not null,
		descripcion varchar(255) null,
		imagen varchar(255) null,
		precioVenta float null,
		denominacion varchar(255) null,
		idRubro bigint null,
        idStock bigint null,
        idInsumo bigint null
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
		denominacion,
		idRubro,
        idStock,
        idInsumo
        )
        
    SELECT 
		ia.idArticuloVenta, 
        ia.descripcion,
		ia.imagen,
		ia.precioVenta,
        i.denominacion,
        i.idRubro, 
        i.idStock,
        i.idInsumo
        
	FROM informacionarticuloventa ia
    INNER JOIN informacionarticuloventa_insumo ii ON ii.idInsumoVenta = ia.idArticuloVenta
    INNER JOIN insumo i ON i.idInsumo = ii.idInsumo
    INNER JOIN Stock s ON s.idStock = i.idStock
    INNER JOIN (SELECT Termino FROM Terminos) as t
    WHERE i.baja = 0
	AND i.esInsumo = 0
    AND s.Actual > 0
    AND(ia.descripcion LIKE CONCAT('%', t.Termino,'%') 
		OR i.denominacion LIKE CONCAT('%', t.Termino,'%'));
    
SELECT 
    *
FROM
    Resultados;
    
DROP TABLE IF EXISTS Terminos;
END;

