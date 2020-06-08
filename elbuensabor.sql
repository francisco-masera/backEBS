CREATE DATABASE  IF NOT EXISTS `elbuensabor` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
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
  `denominacion` varchar(255) NOT NULL,
  `esExtra` bit(1) NOT NULL,
  `unidadMedida` varchar(255) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articuloinsumo`
--

LOCK TABLES `articuloinsumo` WRITE;
/*!40000 ALTER TABLE `articuloinsumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `articuloinsumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articuloventa`
--

DROP TABLE IF EXISTS `articuloventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articuloventa` (
  `DTYPE` varchar(31) NOT NULL,
  `idArticuloVenta` bigint NOT NULL AUTO_INCREMENT,
  `baja` tinyint(1) NOT NULL DEFAULT '0',
  `denominacion` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `enVenta` bit(1) NOT NULL,
  `imagen` varchar(255) NOT NULL,
  `precioVenta` float NOT NULL,
  `unidadMedida` varchar(255) NOT NULL,
  `aptoCeliaco` bit(1) NOT NULL,
  `tiempoCocina` int NOT NULL,
  `vegano` bit(1) NOT NULL,
  `vegetariano` bit(1) NOT NULL,
  `idHistorial` bigint NOT NULL,
  `idOrden` bigint NOT NULL,
  `idRubro` bigint NOT NULL,
  `idStock` bigint NOT NULL,
  PRIMARY KEY (`idArticuloVenta`),
  KEY `FK2ntchlwtjfwu965vtqyq4jhnw` (`idHistorial`),
  KEY `FKi8ocnc1g72t610dshgo8gwj60` (`idOrden`),
  KEY `FKidx82sk84m05m13a80lpc2sgf` (`idRubro`),
  KEY `FKlfo1oaqgbg72crng3minhv55j` (`idStock`),
  CONSTRAINT `FK2ntchlwtjfwu965vtqyq4jhnw` FOREIGN KEY (`idHistorial`) REFERENCES `historialventas` (`idHistorial`),
  CONSTRAINT `FKi8ocnc1g72t610dshgo8gwj60` FOREIGN KEY (`idOrden`) REFERENCES `ordencompra` (`idOrdenCompra`),
  CONSTRAINT `FKidx82sk84m05m13a80lpc2sgf` FOREIGN KEY (`idRubro`) REFERENCES `rubroinsumo` (`idRubroInsumo`),
  CONSTRAINT `FKlfo1oaqgbg72crng3minhv55j` FOREIGN KEY (`idStock`) REFERENCES `stock` (`idStock`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articuloventa`
--

LOCK TABLES `articuloventa` WRITE;
/*!40000 ALTER TABLE `articuloventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `articuloventa` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config` (
  `idConfig` bigint NOT NULL AUTO_INCREMENT,
  `cantidadCocineros` int NOT NULL,
  `emailEmpresa` varchar(255) NOT NULL,
  PRIMARY KEY (`idConfig`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallepedido`
--

LOCK TABLES `detallepedido` WRITE;
/*!40000 ALTER TABLE `detallepedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallepedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domicilio`
--

DROP TABLE IF EXISTS `domicilio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `domicilio` (
  `idDomicilio` bigint NOT NULL AUTO_INCREMENT,
  `baja` tinyint(1) NOT NULL DEFAULT '0',
  `calle` varchar(255) NOT NULL,
  `departamento` varchar(255) NOT NULL,
  `localidad` varchar(255) NOT NULL,
  `numero` int NOT NULL,
  `piso` int NOT NULL,
  `idPersona` bigint NOT NULL,
  PRIMARY KEY (`idDomicilio`),
  UNIQUE KEY `UK_jlk5doq3tgd010o2jup0vhn8c` (`idPersona`),
  CONSTRAINT `FKqskjqmjbcu4wcn120ovey9hm3` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domicilio`
--

LOCK TABLES `domicilio` WRITE;
/*!40000 ALTER TABLE `domicilio` DISABLE KEYS */;
/*!40000 ALTER TABLE `domicilio` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historialventas`
--

LOCK TABLES `historialventas` WRITE;
/*!40000 ALTER TABLE `historialventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `historialventas` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordencompra`
--

LOCK TABLES `ordencompra` WRITE;
/*!40000 ALTER TABLE `ordencompra` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordencompra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `idPedido` bigint NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) NOT NULL,
  `hora` time DEFAULT NULL,
  `numero` bigint NOT NULL,
  `tipoEntrega` bit(1) NOT NULL,
  `idCliente` bigint NOT NULL,
  PRIMARY KEY (`idPedido`),
  UNIQUE KEY `UK_q9xfythglxw2839yg5w89r8w2` (`idCliente`),
  CONSTRAINT `FKmxf6j0o4wr2yx9xbld3inu8hv` FOREIGN KEY (`idCliente`) REFERENCES `persona` (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `DTYPE` varchar(31) NOT NULL,
  `idPersona` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) NOT NULL,
  `baja` tinyint(1) NOT NULL DEFAULT '0',
  `email` varchar(255) NOT NULL,
  `foto` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `telefono` int NOT NULL,
  `contrasenia` varchar(255) NOT NULL,
  `rol` varchar(255) NOT NULL,
  `usuario` varchar(255) NOT NULL,
  PRIMARY KEY (`idPersona`),
  UNIQUE KEY `UK_464ovdn1df4kwfmo8udtscdpd` (`contrasenia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

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
  KEY `FKkim9lroxn5qx1wqn15qwwhv7v` (`idManufacturado`),
  CONSTRAINT `FKkim9lroxn5qx1wqn15qwwhv7v` FOREIGN KEY (`idManufacturado`) REFERENCES `articuloventa` (`idArticuloVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receta`
--

LOCK TABLES `receta` WRITE;
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recetasugerida`
--

LOCK TABLES `recetasugerida` WRITE;
/*!40000 ALTER TABLE `recetasugerida` DISABLE KEYS */;
/*!40000 ALTER TABLE `recetasugerida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rubroinsumo`
--

DROP TABLE IF EXISTS `rubroinsumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rubroinsumo` (
  `idRubroInsumo` bigint NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(255) NOT NULL,
  `rubroPadre` bigint DEFAULT NULL,
  PRIMARY KEY (`idRubroInsumo`),
  KEY `FKfs4bgn4in17blfq4tl2b1nd1e` (`rubroPadre`),
  CONSTRAINT `FKfs4bgn4in17blfq4tl2b1nd1e` FOREIGN KEY (`rubroPadre`) REFERENCES `rubroinsumo` (`idRubroInsumo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rubroinsumo`
--

LOCK TABLES `rubroinsumo` WRITE;
/*!40000 ALTER TABLE `rubroinsumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `rubroinsumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rubromanufacturado`
--

DROP TABLE IF EXISTS `rubromanufacturado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rubromanufacturado` (
  `idRubroManufacturado` bigint NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(255) NOT NULL,
  PRIMARY KEY (`idRubroManufacturado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rubromanufacturado`
--

LOCK TABLES `rubromanufacturado` WRITE;
/*!40000 ALTER TABLE `rubromanufacturado` DISABLE KEYS */;
/*!40000 ALTER TABLE `rubromanufacturado` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,80,80,80),(2,90,90,90);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarjetadebito`
--

DROP TABLE IF EXISTS `tarjetadebito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarjetadebito` (
  `idTarjeta` bigint NOT NULL AUTO_INCREMENT,
  `baja` tinyint(1) NOT NULL DEFAULT '0',
  `nombreTitular` varchar(255) NOT NULL,
  `numero` bigint NOT NULL,
  `vecimiento` date DEFAULT NULL,
  `idCliente` bigint NOT NULL,
  PRIMARY KEY (`idTarjeta`),
  UNIQUE KEY `UK_4mvihldyb4ogi4uqtdyvgwaht` (`numero`),
  KEY `FKfr3w7xn5arbxmvjgadnq7ouam` (`idCliente`),
  CONSTRAINT `FKfr3w7xn5arbxmvjgadnq7ouam` FOREIGN KEY (`idCliente`) REFERENCES `persona` (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjetadebito`
--

LOCK TABLES `tarjetadebito` WRITE;
/*!40000 ALTER TABLE `tarjetadebito` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarjetadebito` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-08  6:50:55
