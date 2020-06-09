CREATE DATABASE  IF NOT EXISTS `elbuensabor` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `elbuensabor`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: elbuensabor
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `articuloinsumo`
--

DROP TABLE IF EXISTS `articuloinsumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articuloinsumo` (
  `idArticuloInsumo` bigint NOT NULL AUTO_INCREMENT,
  `baja` tinyint(1) NOT NULL DEFAULT '0',
  `denominacion` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `esExtra` bit(1) NOT NULL,
  `unidadMedida` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idOrden` bigint NOT NULL,
  `idReceta` bigint NOT NULL,
  `idRecetaSugerida` bigint NOT NULL,
  `idRubro` bigint NOT NULL,
  `idStock` bigint NOT NULL,
  PRIMARY KEY (`idArticuloInsumo`),
  UNIQUE KEY `UK_dixx250sev33i662dg0jqkkeq` (`idRubro`),
  KEY `FK6ngdwgmkp0p0jpcf9jjk7eqvu` (`idOrden`),
  KEY `FKmtgs0xq1uckxj3cad1vels70f` (`idReceta`),
  KEY `FKnj5mthww6mim3mkfllhd1whmn` (`idRecetaSugerida`),
  KEY `FKdur7lkwnfjp5ppgk2dxhy2trj` (`idStock`),
  CONSTRAINT `FK6ngdwgmkp0p0jpcf9jjk7eqvu` FOREIGN KEY (`idOrden`) REFERENCES `ordencompra` (`idOrdenCompra`),
  CONSTRAINT `FKdur7lkwnfjp5ppgk2dxhy2trj` FOREIGN KEY (`idStock`) REFERENCES `stock` (`idStock`),
  CONSTRAINT `FKmtgs0xq1uckxj3cad1vels70f` FOREIGN KEY (`idReceta`) REFERENCES `receta` (`idReceta`),
  CONSTRAINT `FKnj5mthww6mim3mkfllhd1whmn` FOREIGN KEY (`idRecetaSugerida`) REFERENCES `recetasugerida` (`idSugerencia`),
  CONSTRAINT `FKs1o7k890mmk2ebwrf8y99ncdk` FOREIGN KEY (`idRubro`) REFERENCES `rubroinsumo` (`idRubroInsumo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `articuloinsumoventa`
--

DROP TABLE IF EXISTS `articuloinsumoventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articuloinsumoventa` (
  `unidadMedida` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idArticulo` bigint NOT NULL,
  `idOrden` bigint NOT NULL,
  `idRubro` bigint NOT NULL,
  `idStock` bigint NOT NULL,
  PRIMARY KEY (`idArticulo`),
  KEY `FKf5qc310xt7afddxendyvx47vr` (`idOrden`),
  KEY `FK4g8apoiflxqx8xdow9hsfg3jq` (`idRubro`),
  KEY `FKesfurrpvuwwmd3dj7ee9rq1mg` (`idStock`),
  CONSTRAINT `FK41994p2aoy2vewakpvj2h30fo` FOREIGN KEY (`idArticulo`) REFERENCES `articuloventa` (`idArticuloVenta`),
  CONSTRAINT `FK4g8apoiflxqx8xdow9hsfg3jq` FOREIGN KEY (`idRubro`) REFERENCES `rubroinsumo` (`idRubroInsumo`),
  CONSTRAINT `FKesfurrpvuwwmd3dj7ee9rq1mg` FOREIGN KEY (`idStock`) REFERENCES `stock` (`idStock`),
  CONSTRAINT `FKf5qc310xt7afddxendyvx47vr` FOREIGN KEY (`idOrden`) REFERENCES `ordencompra` (`idOrdenCompra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `articulomanufacturado`
--

DROP TABLE IF EXISTS `articulomanufacturado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulomanufacturado` (
  `aptoCeliaco` bit(1) NOT NULL,
  `tiempoCocina` int NOT NULL,
  `vegano` bit(1) NOT NULL,
  `vegetariano` bit(1) NOT NULL,
  `idArticulo` bigint NOT NULL,
  `idRubro` bigint NOT NULL,
  PRIMARY KEY (`idArticulo`),
  KEY `FKmleihlxvcplpjv5e3wtmnepuy` (`idRubro`),
  CONSTRAINT `FKmleihlxvcplpjv5e3wtmnepuy` FOREIGN KEY (`idRubro`) REFERENCES `rubromanufacturado` (`idRubroManufacturado`),
  CONSTRAINT `FKwq00hqjrhsywb1pmthi8559t` FOREIGN KEY (`idArticulo`) REFERENCES `articuloventa` (`idArticuloVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `articuloventa`
--

DROP TABLE IF EXISTS `articuloventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articuloventa` (
  `idArticuloVenta` bigint NOT NULL AUTO_INCREMENT,
  `baja` tinyint(1) NOT NULL DEFAULT '0',
  `denominacion` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `imagen` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `precioVenta` float NOT NULL,
  `idHistorial` bigint NOT NULL,
  PRIMARY KEY (`idArticuloVenta`),
  KEY `FK2ntchlwtjfwu965vtqyq4jhnw` (`idHistorial`),
  CONSTRAINT `FK2ntchlwtjfwu965vtqyq4jhnw` FOREIGN KEY (`idHistorial`) REFERENCES `historialventas` (`idHistorial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idPersona` bigint NOT NULL,
  PRIMARY KEY (`idPersona`),
  CONSTRAINT `FKf1o3sjwom8eonggxy45geovk2` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `idCompra` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` float NOT NULL,
  `precioUnitario` float NOT NULL,
  `idOrden` bigint NOT NULL,
  PRIMARY KEY (`idCompra`),
  KEY `FKkt9twtkdc5o6nwwgfcxwwbobe` (`idOrden`),
  CONSTRAINT `FKkt9twtkdc5o6nwwgfcxwwbobe` FOREIGN KEY (`idOrden`) REFERENCES `ordencompra` (`idOrdenCompra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config` (
  `idConfig` bigint NOT NULL AUTO_INCREMENT,
  `cantidadCocineros` int NOT NULL,
  `emailEmpresa` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idConfig`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detallepedido`
--

DROP TABLE IF EXISTS `detallepedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallepedido` (
  `idDetalle` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `idArticulo` bigint NOT NULL,
  `idPedido` bigint NOT NULL,
  PRIMARY KEY (`idDetalle`),
  UNIQUE KEY `UK_l7herh1mff4m0y418om20097n` (`idPedido`),
  KEY `FKe15gkayxekm9nnocgq2q5n8i` (`idArticulo`),
  CONSTRAINT `FKcu5cmqwe950m98ffijdd759wr` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`idPedido`),
  CONSTRAINT `FKe15gkayxekm9nnocgq2q5n8i` FOREIGN KEY (`idArticulo`) REFERENCES `articuloventa` (`idArticuloVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `domicilio`
--

DROP TABLE IF EXISTS `domicilio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `domicilio` (
  `idDomicilio` bigint NOT NULL AUTO_INCREMENT,
  `baja` tinyint(1) NOT NULL DEFAULT '0',
  `calle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `departamento` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `localidad` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `numero` int NOT NULL,
  `piso` int NOT NULL,
  `idPersona` bigint NOT NULL,
  PRIMARY KEY (`idDomicilio`),
  UNIQUE KEY `UK_jlk5doq3tgd010o2jup0vhn8c` (`idPersona`),
  CONSTRAINT `FKqskjqmjbcu4wcn120ovey9hm3` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `contrasenia` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `rol` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `usuario` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idPersona` bigint NOT NULL,
  PRIMARY KEY (`idPersona`),
  UNIQUE KEY `UK_ibcn21urofhpvgf0j5er3sn21` (`contrasenia`),
  CONSTRAINT `FKbo0fbx2es8v3xj4igldj598bc` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `idFactura` bigint NOT NULL AUTO_INCREMENT,
  `fechaHora` datetime DEFAULT NULL,
  `formaPago` bit(1) NOT NULL,
  `numero` bigint NOT NULL,
  `total` double NOT NULL,
  `idPedido` bigint NOT NULL,
  PRIMARY KEY (`idFactura`),
  UNIQUE KEY `UK_c1jtshpjhg01detpak886jv5i` (`numero`),
  KEY `FKly6sa5ad8ua8p5pyyc1rxirq1` (`idPedido`),
  CONSTRAINT `FKly6sa5ad8ua8p5pyyc1rxirq1` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`idPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `historialventas`
--

DROP TABLE IF EXISTS `historialventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historialventas` (
  `idHistorial` bigint NOT NULL AUTO_INCREMENT,
  `costo` float NOT NULL,
  `fechaVenta` date DEFAULT NULL,
  `precioVenta` float NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`idHistorial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ordencompra`
--

DROP TABLE IF EXISTS `ordencompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordencompra` (
  `idOrdenCompra` bigint NOT NULL AUTO_INCREMENT,
  `fechaHora` datetime DEFAULT NULL,
  `numero` bigint NOT NULL,
  PRIMARY KEY (`idOrdenCompra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `idPedido` bigint NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hora` time DEFAULT NULL,
  `numero` bigint NOT NULL,
  `tipoEntrega` bit(1) NOT NULL,
  `idCliente` bigint NOT NULL,
  PRIMARY KEY (`idPedido`),
  UNIQUE KEY `UK_q9xfythglxw2839yg5w89r8w2` (`idCliente`),
  CONSTRAINT `FKaxpy7jnkxyiemmhwpryyksqmd` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `idPersona` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `baja` tinyint(1) NOT NULL DEFAULT '0',
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `foto` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` int NOT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `receta`
--

DROP TABLE IF EXISTS `receta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receta` (
  `idReceta` bigint NOT NULL AUTO_INCREMENT,
  `baja` tinyint(1) NOT NULL DEFAULT '0',
  `cantidadInsumo` float NOT NULL,
  `idManufacturado` bigint NOT NULL,
  PRIMARY KEY (`idReceta`),
  KEY `FKlk4tqorrqitu489uoj0kdkggf` (`idManufacturado`),
  CONSTRAINT `FKlk4tqorrqitu489uoj0kdkggf` FOREIGN KEY (`idManufacturado`) REFERENCES `articulomanufacturado` (`idArticulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recetasugerida`
--

DROP TABLE IF EXISTS `recetasugerida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recetasugerida` (
  `idSugerencia` bigint NOT NULL AUTO_INCREMENT,
  `cantidadInsumo` float NOT NULL,
  `estado` bit(1) NOT NULL,
  PRIMARY KEY (`idSugerencia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rubroinsumo`
--

DROP TABLE IF EXISTS `rubroinsumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rubroinsumo` (
  `idRubroInsumo` bigint NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `rubroPadre` bigint DEFAULT NULL,
  PRIMARY KEY (`idRubroInsumo`),
  KEY `FKfs4bgn4in17blfq4tl2b1nd1e` (`rubroPadre`),
  CONSTRAINT `FKfs4bgn4in17blfq4tl2b1nd1e` FOREIGN KEY (`rubroPadre`) REFERENCES `rubroinsumo` (`idRubroInsumo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rubromanufacturado`
--

DROP TABLE IF EXISTS `rubromanufacturado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rubromanufacturado` (
  `idRubroManufacturado` bigint NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idRubroManufacturado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `idStock` bigint NOT NULL AUTO_INCREMENT,
  `actual` bigint NOT NULL,
  `maximo` bigint NOT NULL,
  `minimo` int NOT NULL,
  PRIMARY KEY (`idStock`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sugerenciachef`
--

DROP TABLE IF EXISTS `sugerenciachef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sugerenciachef` (
  `aptoCeliaco` bit(1) NOT NULL,
  `tiempoCocina` int NOT NULL,
  `vegano` bit(1) NOT NULL,
  `vegetariano` bit(1) NOT NULL,
  `idArticulo` bigint NOT NULL,
  PRIMARY KEY (`idArticulo`),
  CONSTRAINT `FKbrj6dq7b8rwrfa4g1fkaqw90p` FOREIGN KEY (`idArticulo`) REFERENCES `articuloventa` (`idArticuloVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tarjetadebito`
--

DROP TABLE IF EXISTS `tarjetadebito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarjetadebito` (
  `idTarjeta` bigint NOT NULL AUTO_INCREMENT,
  `baja` tinyint(1) NOT NULL DEFAULT '0',
  `nombreTitular` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `numero` bigint NOT NULL,
  `vecimiento` date DEFAULT NULL,
  `idCliente` bigint NOT NULL,
  PRIMARY KEY (`idTarjeta`),
  UNIQUE KEY `UK_4mvihldyb4ogi4uqtdyvgwaht` (`numero`),
  KEY `FK5wsu9jc73rxhb2msbco5dc86n` (`idCliente`),
  CONSTRAINT `FK5wsu9jc73rxhb2msbco5dc86n` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-09 11:06:50
