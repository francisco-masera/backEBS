-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.20 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando datos para la tabla elbuensabor.articuloinsumo: ~0 rows (aproximadamente)
DELETE FROM `articuloinsumo`;
/*!40000 ALTER TABLE `articuloinsumo` DISABLE KEYS */;
INSERT INTO `articuloinsumo` (`id`, `denominacion`, `esExtra`, `unidadMedida`, `idOrden`, `idReceta`, `idRecetaSugerida`, `idRubro`, `idStock`) VALUES
	(1, 'Queso Mantecoso', b'0', 'kg', 2, 1, 1, 13, 3),
	(3, 'Tomates Frescos', b'1', 'kg', 2, 2, 2, 14, 4),
	(4, 'Harina 0000', b'0', 'kg', 2, 3, 3, 15, 5);
/*!40000 ALTER TABLE `articuloinsumo` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.articuloinsumoventa: ~0 rows (aproximadamente)
DELETE FROM `articuloinsumoventa`;
/*!40000 ALTER TABLE `articuloinsumoventa` DISABLE KEYS */;
INSERT INTO `articuloinsumoventa` (`id`, `unidadMedida`, `cantidad`, `idRubro`, `idOrdenCompra`, `idStock`, `idArticuloVenta`) VALUES
	(1, 'L', 1, 10, 1, 1, 4),
	(2, 'L', 1, 10, 1, 2, 5);
/*!40000 ALTER TABLE `articuloinsumoventa` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.articulomanufacturado: ~0 rows (aproximadamente)
DELETE FROM `articulomanufacturado`;
/*!40000 ALTER TABLE `articulomanufacturado` DISABLE KEYS */;
INSERT INTO `articulomanufacturado` (`id`, `tiempoCocina`, `aptoCeliaco`, `vegano`, `vegetariano`, `idRubro`, `idArticuloVenta`) VALUES
	(1, 25, b'0', b'0', b'1', 1, 1),
	(2, 30, b'0', b'0', b'0', 2, 2),
	(3, 15, b'0', b'1', b'1', 3, 3);
/*!40000 ALTER TABLE `articulomanufacturado` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.articuloventa: ~0 rows (aproximadamente)
DELETE FROM `articuloventa`;
/*!40000 ALTER TABLE `articuloventa` DISABLE KEYS */;
INSERT INTO `articuloventa` (`id`, `denominacion`, `descripcion`, `precioVenta`, `imagen`, `enVenta`) VALUES
	(1, 'Pizza Mozzarella', 'Pizza clásica de mozzarella', 350, ' ', b'1'),
	(2, 'Lomo casero', 'Lomo en pan casero a la parrilla', 500, ' ', b'1'),
	(3, 'Carlitos', 'Un buen carlitos tradicional', 225, ' ', b'1'),
	(4, 'CocaCola', 'La clásica bebida sabor cola', 160, ' ', b'1'),
	(5, 'Sprite', 'Bebida sabor lima-limón de la marca Coca-Cola', 150, ' ', b'1');
/*!40000 ALTER TABLE `articuloventa` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.cliente: ~0 rows (aproximadamente)
DELETE FROM `cliente`;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.compra: ~0 rows (aproximadamente)
DELETE FROM `compra`;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.config: ~0 rows (aproximadamente)
DELETE FROM `config`;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
/*!40000 ALTER TABLE `config` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.detallepedido: ~0 rows (aproximadamente)
DELETE FROM `detallepedido`;
/*!40000 ALTER TABLE `detallepedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallepedido` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.domicilio: ~0 rows (aproximadamente)
DELETE FROM `domicilio`;
/*!40000 ALTER TABLE `domicilio` DISABLE KEYS */;
/*!40000 ALTER TABLE `domicilio` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.empleado: ~0 rows (aproximadamente)
DELETE FROM `empleado`;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.factura: ~0 rows (aproximadamente)
DELETE FROM `factura`;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.historialventas: ~0 rows (aproximadamente)
DELETE FROM `historialventas`;
/*!40000 ALTER TABLE `historialventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `historialventas` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.ordencompra: ~0 rows (aproximadamente)
DELETE FROM `ordencompra`;
/*!40000 ALTER TABLE `ordencompra` DISABLE KEYS */;
INSERT INTO `ordencompra` (`id`, `fechaHora`, `numero`) VALUES
	(1, '2020-05-28 00:00:00', 1),
	(2, '2020-05-15 15:24:00', 2);
/*!40000 ALTER TABLE `ordencompra` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.pedido: ~0 rows (aproximadamente)
DELETE FROM `pedido`;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.persona: ~0 rows (aproximadamente)
DELETE FROM `persona`;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.receta: ~0 rows (aproximadamente)
DELETE FROM `receta`;
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
INSERT INTO `receta` (`id`, `cantidadInsumo`, `idManufacturado`) VALUES
	(1, 0.3, 1),
	(2, 0.4, 1),
	(3, 0.5, 1),
	(5, 25, 1),
	(6, 0.01, 1),
	(7, 0.15, 2);
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.recetasugerida: ~0 rows (aproximadamente)
DELETE FROM `recetasugerida`;
/*!40000 ALTER TABLE `recetasugerida` DISABLE KEYS */;
INSERT INTO `recetasugerida` (`id`, `cantidadInsumo`, `idSugerencia`) VALUES
	(1, 300, 1),
	(2, 0.3, 1),
	(3, 0.4, 1),
	(4, 0.5, 1),
	(5, 25, 1),
	(6, 0.01, 1);
/*!40000 ALTER TABLE `recetasugerida` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.rubroinsumo: ~0 rows (aproximadamente)
DELETE FROM `rubroinsumo`;
/*!40000 ALTER TABLE `rubroinsumo` DISABLE KEYS */;
INSERT INTO `rubroinsumo` (`id`, `denominacion`, `rubroPadre`) VALUES
	(1, 'Bebidas', NULL),
	(10, 'Bebidas Gasificadas', 1),
	(12, 'Lacteos', NULL),
	(13, 'Quesos', 12),
	(14, 'Frutas y Verduras', NULL),
	(15, 'Harinas', NULL),
	(16, 'Carnes', NULL),
	(17, 'Carne de Vaca', 16),
	(18, 'Carne de cerdo', 16),
	(19, 'Carne de pollo', 16),
	(20, 'Pescado', 16);
/*!40000 ALTER TABLE `rubroinsumo` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.rubromanufacturado: ~0 rows (aproximadamente)
DELETE FROM `rubromanufacturado`;
/*!40000 ALTER TABLE `rubromanufacturado` DISABLE KEYS */;
INSERT INTO `rubromanufacturado` (`id`, `denominacion`) VALUES
	(1, 'Pizzas'),
	(2, 'Lomos'),
	(3, 'Sándwiches sin carne');
/*!40000 ALTER TABLE `rubromanufacturado` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.stock: ~0 rows (aproximadamente)
DELETE FROM `stock`;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` (`id`, `actual`, `maximo`, `minimo`) VALUES
	(1, 3000, 5000, 250),
	(2, 1500, 3000, 200),
	(3, 30, 50, 10),
	(4, 40, 60, 5),
	(5, 300, 400, 80);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.sugerenciachef: ~0 rows (aproximadamente)
DELETE FROM `sugerenciachef`;
/*!40000 ALTER TABLE `sugerenciachef` DISABLE KEYS */;
INSERT INTO `sugerenciachef` (`id`, `tiempoCocina`, `aptoCeliaco`, `vegano`, `vegetariano`, `estado`) VALUES
	(1, 25, b'0', b'0', b'1', b'1'),
	(2, 30, b'0', b'0', b'0', b'1'),
	(3, 15, b'0', b'1', b'1', b'1');
/*!40000 ALTER TABLE `sugerenciachef` ENABLE KEYS */;

-- Volcando datos para la tabla elbuensabor.tarjetadebito: ~0 rows (aproximadamente)
DELETE FROM `tarjetadebito`;
/*!40000 ALTER TABLE `tarjetadebito` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarjetadebito` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
