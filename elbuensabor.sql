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


-- Volcando estructura de base de datos para elbuensabor
CREATE DATABASE IF NOT EXISTS `elbuensabor` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `elbuensabor`;

-- Volcando estructura para tabla elbuensabor.articuloinsumo
CREATE TABLE IF NOT EXISTS `articuloinsumo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `esExtra` bit(1) NOT NULL,
  `unidadMedida` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `idOrden` bigint NOT NULL,
  `idReceta` bigint NOT NULL,
  `idRecetaSugerida` bigint NOT NULL,
  `idRubro` bigint NOT NULL,
  `idStock` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6ngdwgmkp0p0jpcf9jjk7eqvu` (`idOrden`),
  KEY `FKmtgs0xq1uckxj3cad1vels70f` (`idReceta`),
  KEY `FKnj5mthww6mim3mkfllhd1whmn` (`idRecetaSugerida`),
  KEY `FKs1o7k890mmk2ebwrf8y99ncdk` (`idRubro`),
  KEY `FKdur7lkwnfjp5ppgk2dxhy2trj` (`idStock`),
  CONSTRAINT `FK6ngdwgmkp0p0jpcf9jjk7eqvu` FOREIGN KEY (`idOrden`) REFERENCES `ordencompra` (`id`),
  CONSTRAINT `FKdur7lkwnfjp5ppgk2dxhy2trj` FOREIGN KEY (`idStock`) REFERENCES `stock` (`id`),
  CONSTRAINT `FKmtgs0xq1uckxj3cad1vels70f` FOREIGN KEY (`idReceta`) REFERENCES `receta` (`id`),
  CONSTRAINT `FKnj5mthww6mim3mkfllhd1whmn` FOREIGN KEY (`idRecetaSugerida`) REFERENCES `recetasugerida` (`id`),
  CONSTRAINT `FKs1o7k890mmk2ebwrf8y99ncdk` FOREIGN KEY (`idRubro`) REFERENCES `rubroinsumo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.articuloinsumo: ~3 rows (aproximadamente)
DELETE FROM `articuloinsumo`;
/*!40000 ALTER TABLE `articuloinsumo` DISABLE KEYS */;
INSERT INTO `articuloinsumo` (`id`, `denominacion`, `esExtra`, `unidadMedida`, `idOrden`, `idReceta`, `idRecetaSugerida`, `idRubro`, `idStock`) VALUES
	(1, 'Queso Mantecoso', b'0', 'kg', 2, 1, 1, 13, 3),
	(3, 'Tomates Frescos', b'1', 'kg', 2, 2, 2, 14, 4),
	(4, 'Harina 0000', b'0', 'kg', 2, 3, 3, 15, 5);
/*!40000 ALTER TABLE `articuloinsumo` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.articuloinsumoventa
CREATE TABLE IF NOT EXISTS `articuloinsumoventa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `unidadMedida` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `cantidad` float NOT NULL,
  `idRubro` bigint NOT NULL,
  `idOrdenCompra` bigint NOT NULL,
  `idStock` bigint NOT NULL,
  `idArticuloVenta` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idArticuloVenta_idx` (`idArticuloVenta`),
  CONSTRAINT `idArticuloVenta` FOREIGN KEY (`idArticuloVenta`) REFERENCES `articuloventa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.articuloinsumoventa: ~2 rows (aproximadamente)
DELETE FROM `articuloinsumoventa`;
/*!40000 ALTER TABLE `articuloinsumoventa` DISABLE KEYS */;
INSERT INTO `articuloinsumoventa` (`id`, `unidadMedida`, `cantidad`, `idRubro`, `idOrdenCompra`, `idStock`, `idArticuloVenta`) VALUES
	(1, 'L', 1, 10, 1, 1, 4),
	(2, 'L', 1, 10, 1, 2, 5);
/*!40000 ALTER TABLE `articuloinsumoventa` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.articulomanufacturado
CREATE TABLE IF NOT EXISTS `articulomanufacturado` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tiempoCocina` int NOT NULL,
  `aptoCeliaco` bit(1) NOT NULL,
  `vegano` bit(1) NOT NULL,
  `vegetariano` bit(1) NOT NULL,
  `idRubro` bigint NOT NULL,
  `idArticuloVenta` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idArticuloVenta_idx` (`idArticuloVenta`) /*!80000 INVISIBLE */,
  CONSTRAINT `idArticulo` FOREIGN KEY (`idArticuloVenta`) REFERENCES `articuloventa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.articulomanufacturado: ~3 rows (aproximadamente)
DELETE FROM `articulomanufacturado`;
/*!40000 ALTER TABLE `articulomanufacturado` DISABLE KEYS */;
INSERT INTO `articulomanufacturado` (`id`, `tiempoCocina`, `aptoCeliaco`, `vegano`, `vegetariano`, `idRubro`, `idArticuloVenta`) VALUES
	(1, 25, b'0', b'0', b'1', 1, 1),
	(2, 30, b'0', b'0', b'0', 2, 2),
	(3, 15, b'0', b'1', b'1', 3, 3);
/*!40000 ALTER TABLE `articulomanufacturado` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.articuloventa
CREATE TABLE IF NOT EXISTS `articuloventa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `precioVenta` float NOT NULL,
  `imagen` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `enVenta` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.articuloventa: ~5 rows (aproximadamente)
DELETE FROM `articuloventa`;
/*!40000 ALTER TABLE `articuloventa` DISABLE KEYS */;
INSERT INTO `articuloventa` (`id`, `denominacion`, `descripcion`, `precioVenta`, `imagen`, `enVenta`) VALUES
	(1, 'Pizza Mozzarella', 'Pizza clásica de mozzarella', 350, ' ', b'1'),
	(2, 'Lomo casero', 'Lomo en pan casero a la parrilla', 500, ' ', b'1'),
	(3, 'Carlitos', 'Un buen carlitos tradicional', 225, ' ', b'1'),
	(4, 'CocaCola', 'La clásica bebida sabor cola', 160, ' ', b'1'),
	(5, 'Sprite', 'Bebida sabor lima-limón de la marca Coca-Cola', 150, ' ', b'1');
/*!40000 ALTER TABLE `articuloventa` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `idPersona` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idPersona_idx` (`idPersona`),
  CONSTRAINT `idPersonaC` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.cliente: ~0 rows (aproximadamente)
DELETE FROM `cliente`;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.compra
CREATE TABLE IF NOT EXISTS `compra` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` float NOT NULL,
  `precioUnitario` float NOT NULL,
  `idOrdenCompra` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh8rnl96wlhwcjnfruqebmm3lm` (`idOrdenCompra`),
  CONSTRAINT `FKh8rnl96wlhwcjnfruqebmm3lm` FOREIGN KEY (`idOrdenCompra`) REFERENCES `ordencompra` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.compra: ~0 rows (aproximadamente)
DELETE FROM `compra`;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.config
CREATE TABLE IF NOT EXISTS `config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidadCocineros` int NOT NULL,
  `emailEmpresa` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.config: ~0 rows (aproximadamente)
DELETE FROM `config`;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
/*!40000 ALTER TABLE `config` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.detallepedido
CREATE TABLE IF NOT EXISTS `detallepedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `idPedido` bigint NOT NULL,
  `idArticulo` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcu5cmqwe950m98ffijdd759wr` (`idPedido`),
  KEY `fkArticuloD_idx` (`idArticulo`),
  CONSTRAINT `FKcu5cmqwe950m98ffijdd759wr` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`id`),
  CONSTRAINT `idArticuloD` FOREIGN KEY (`idArticulo`) REFERENCES `articuloventa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.detallepedido: ~0 rows (aproximadamente)
DELETE FROM `detallepedido`;
/*!40000 ALTER TABLE `detallepedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallepedido` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.domicilio
CREATE TABLE IF NOT EXISTS `domicilio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `calle` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `departamento` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `localidad` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `numero` int NOT NULL,
  `piso` int NOT NULL,
  `idPersona` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idPersonaD_idx` (`idPersona`),
  CONSTRAINT `idPersona` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.domicilio: ~0 rows (aproximadamente)
DELETE FROM `domicilio`;
/*!40000 ALTER TABLE `domicilio` DISABLE KEYS */;
/*!40000 ALTER TABLE `domicilio` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.empleado
CREATE TABLE IF NOT EXISTS `empleado` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `usuario` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `contrasenia` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `rol` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `fkPersona` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idPersonaE_idx` (`fkPersona`),
  CONSTRAINT `idPersonaE` FOREIGN KEY (`fkPersona`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.empleado: ~0 rows (aproximadamente)
DELETE FROM `empleado`;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.factura
CREATE TABLE IF NOT EXISTS `factura` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fechaHora` datetime NOT NULL,
  `formaPago` bit(1) NOT NULL,
  `numero` bigint NOT NULL,
  `total` double NOT NULL,
  `idPedido` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKly6sa5ad8ua8p5pyyc1rxirq1` (`idPedido`),
  CONSTRAINT `FKly6sa5ad8ua8p5pyyc1rxirq1` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.factura: ~0 rows (aproximadamente)
DELETE FROM `factura`;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.historialventas
CREATE TABLE IF NOT EXISTS `historialventas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `costo` float NOT NULL,
  `fechaVenta` date NOT NULL,
  `precioVenta` float NOT NULL,
  `total` double NOT NULL,
  `idArticulo` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fkArticuloH_idx` (`idArticulo`),
  CONSTRAINT `fkArticuloH` FOREIGN KEY (`idArticulo`) REFERENCES `articuloventa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.historialventas: ~0 rows (aproximadamente)
DELETE FROM `historialventas`;
/*!40000 ALTER TABLE `historialventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `historialventas` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.ordencompra
CREATE TABLE IF NOT EXISTS `ordencompra` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fechaHora` datetime NOT NULL,
  `numero` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.ordencompra: ~2 rows (aproximadamente)
DELETE FROM `ordencompra`;
/*!40000 ALTER TABLE `ordencompra` DISABLE KEYS */;
INSERT INTO `ordencompra` (`id`, `fechaHora`, `numero`) VALUES
	(1, '2020-05-28 00:00:00', 1),
	(2, '2020-05-15 15:24:00', 2);
/*!40000 ALTER TABLE `ordencompra` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.pedido
CREATE TABLE IF NOT EXISTS `pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `hora` time DEFAULT NULL,
  `numero` bigint NOT NULL,
  `tipoEntrega` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.pedido: ~0 rows (aproximadamente)
DELETE FROM `pedido`;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.persona
CREATE TABLE IF NOT EXISTS `persona` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `apellido` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `foto` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.persona: ~0 rows (aproximadamente)
DELETE FROM `persona`;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.receta
CREATE TABLE IF NOT EXISTS `receta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidadInsumo` float NOT NULL,
  `idManufacturado` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idManufacturado` (`idManufacturado`),
  CONSTRAINT `fkManufacturado` FOREIGN KEY (`idManufacturado`) REFERENCES `articulomanufacturado` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.receta: ~6 rows (aproximadamente)
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

-- Volcando estructura para tabla elbuensabor.recetasugerida
CREATE TABLE IF NOT EXISTS `recetasugerida` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidadInsumo` float NOT NULL,
  `idSugerencia` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idSugerencia_idx` (`idSugerencia`),
  CONSTRAINT `fkSugerencia` FOREIGN KEY (`idSugerencia`) REFERENCES `sugerenciachef` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.recetasugerida: ~6 rows (aproximadamente)
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

-- Volcando estructura para tabla elbuensabor.rubroinsumo
CREATE TABLE IF NOT EXISTS `rubroinsumo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `rubroPadre` bigint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `rubroPadre` (`rubroPadre`),
  CONSTRAINT `fkRubro` FOREIGN KEY (`rubroPadre`) REFERENCES `rubroinsumo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.rubroinsumo: ~11 rows (aproximadamente)
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

-- Volcando estructura para tabla elbuensabor.rubromanufacturado
CREATE TABLE IF NOT EXISTS `rubromanufacturado` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.rubromanufacturado: ~3 rows (aproximadamente)
DELETE FROM `rubromanufacturado`;
/*!40000 ALTER TABLE `rubromanufacturado` DISABLE KEYS */;
INSERT INTO `rubromanufacturado` (`id`, `denominacion`) VALUES
	(1, 'Pizzas'),
	(2, 'Lomos'),
	(3, 'Sándwiches sin carne');
/*!40000 ALTER TABLE `rubromanufacturado` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.stock
CREATE TABLE IF NOT EXISTS `stock` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `actual` bigint NOT NULL,
  `maximo` bigint NOT NULL,
  `minimo` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.stock: ~5 rows (aproximadamente)
DELETE FROM `stock`;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` (`id`, `actual`, `maximo`, `minimo`) VALUES
	(1, 3000, 5000, 250),
	(2, 1500, 3000, 200),
	(3, 30, 50, 10),
	(4, 40, 60, 5),
	(5, 300, 400, 80);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.sugerenciachef
CREATE TABLE IF NOT EXISTS `sugerenciachef` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tiempoCocina` int NOT NULL,
  `aptoCeliaco` bit(1) NOT NULL,
  `vegano` bit(1) NOT NULL,
  `vegetariano` bit(1) NOT NULL,
  `estado` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.sugerenciachef: ~3 rows (aproximadamente)
DELETE FROM `sugerenciachef`;
/*!40000 ALTER TABLE `sugerenciachef` DISABLE KEYS */;
INSERT INTO `sugerenciachef` (`id`, `tiempoCocina`, `aptoCeliaco`, `vegano`, `vegetariano`, `estado`) VALUES
	(1, 25, b'0', b'0', b'1', b'1'),
	(2, 30, b'0', b'0', b'0', b'1'),
	(3, 15, b'0', b'1', b'1', b'1');
/*!40000 ALTER TABLE `sugerenciachef` ENABLE KEYS */;

-- Volcando estructura para tabla elbuensabor.tarjetadebito
CREATE TABLE IF NOT EXISTS `tarjetadebito` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombreTitular` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `numero` bigint NOT NULL,
  `vecimiento` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla elbuensabor.tarjetadebito: ~0 rows (aproximadamente)
DELETE FROM `tarjetadebito`;
/*!40000 ALTER TABLE `tarjetadebito` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarjetadebito` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
