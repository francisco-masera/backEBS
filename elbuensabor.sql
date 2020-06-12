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
-- Table structure for table `articulomanufacturado`
--

DROP TABLE IF EXISTS `articulomanufacturado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulomanufacturado` (
  `aptoCeliaco` bit(1) NOT NULL,
  `baja` tinyint(1) NOT NULL DEFAULT '0',
  `denominacion` varchar(255) NOT NULL,
  `tiempoCocina` int NOT NULL,
  `vegano` bit(1) NOT NULL,
  `vegetariano` bit(1) NOT NULL,
  `idArticuloManufacturado` bigint NOT NULL,
  `idRubro` bigint NOT NULL,
  PRIMARY KEY (`idArticuloManufacturado`),
  KEY `FKmleihlxvcplpjv5e3wtmnepuy` (`idRubro`),
  CONSTRAINT `FKmleihlxvcplpjv5e3wtmnepuy` FOREIGN KEY (`idRubro`) REFERENCES `rubromanufacturado` (`idRubroManufacturado`),
  CONSTRAINT `FKtimme61neajt7a5i0lgf4rk20` FOREIGN KEY (`idArticuloManufacturado`) REFERENCES `informacionarticuloventa` (`idArticuloVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulomanufacturado`
--

LOCK TABLES `articulomanufacturado` WRITE;
/*!40000 ALTER TABLE `articulomanufacturado` DISABLE KEYS */;
/*!40000 ALTER TABLE `articulomanufacturado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idCliente` bigint NOT NULL,
  PRIMARY KEY (`idCliente`),
  CONSTRAINT `FKidh5cjaj6rwvd7ffi3f8xyeve` FOREIGN KEY (`idCliente`) REFERENCES `persona` (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
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
  KEY `FKgblnghlb028vohg62ha0fb5xd` (`idArticulo`),
  CONSTRAINT `FKcu5cmqwe950m98ffijdd759wr` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`idPedido`),
  CONSTRAINT `FKgblnghlb028vohg62ha0fb5xd` FOREIGN KEY (`idArticulo`) REFERENCES `informacionarticuloventa` (`idArticuloVenta`)
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
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `rol` varchar(255) NOT NULL,
  `idEmpleado` bigint NOT NULL,
  PRIMARY KEY (`idEmpleado`),
  CONSTRAINT `FKpnt4sbao6fyenlefl82l95v5r` FOREIGN KEY (`idEmpleado`) REFERENCES `persona` (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
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
  `porcentajeDescuento` float NOT NULL,
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
-- Table structure for table `historialcompraaproveedores`
--

DROP TABLE IF EXISTS `historialcompraaproveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historialcompraaproveedores` (
  `idCompra` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` float NOT NULL,
  `fechaCompra` datetime DEFAULT NULL,
  `precioUnitario` float NOT NULL,
  `idInsumo` bigint NOT NULL,
  PRIMARY KEY (`idCompra`),
  KEY `FK1gmxti380q4ukilmuts8d4u33` (`idInsumo`),
  CONSTRAINT `FK1gmxti380q4ukilmuts8d4u33` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`idInsumo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historialcompraaproveedores`
--

LOCK TABLES `historialcompraaproveedores` WRITE;
/*!40000 ALTER TABLE `historialcompraaproveedores` DISABLE KEYS */;
/*!40000 ALTER TABLE `historialcompraaproveedores` ENABLE KEYS */;
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
  `idArticulo` bigint NOT NULL,
  PRIMARY KEY (`idHistorial`),
  KEY `FKiqpk8sh4anogq7cbvmnmvswxi` (`idArticulo`),
  CONSTRAINT `FKiqpk8sh4anogq7cbvmnmvswxi` FOREIGN KEY (`idArticulo`) REFERENCES `informacionarticuloventa` (`idArticuloVenta`)
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
-- Table structure for table `informacionarticuloventa`
--

DROP TABLE IF EXISTS `informacionarticuloventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `informacionarticuloventa` (
  `idArticuloVenta` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `imagen` varchar(255) NOT NULL,
  `precioVenta` float NOT NULL,
  PRIMARY KEY (`idArticuloVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacionarticuloventa`
--

LOCK TABLES `informacionarticuloventa` WRITE;
/*!40000 ALTER TABLE `informacionarticuloventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `informacionarticuloventa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informacionarticuloventa_insumo`
--

DROP TABLE IF EXISTS `informacionarticuloventa_insumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `informacionarticuloventa_insumo` (
  `idInsumoVenta` bigint NOT NULL,
  `idInsumo` bigint NOT NULL,
  PRIMARY KEY (`idInsumoVenta`),
  KEY `FKgpo776a8ag8iapsyjk8y9aiom` (`idInsumo`),
  CONSTRAINT `FK59f0r37j795t0er9ru7e8ot3s` FOREIGN KEY (`idInsumoVenta`) REFERENCES `informacionarticuloventa` (`idArticuloVenta`),
  CONSTRAINT `FKgpo776a8ag8iapsyjk8y9aiom` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`idInsumo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacionarticuloventa_insumo`
--

LOCK TABLES `informacionarticuloventa_insumo` WRITE;
/*!40000 ALTER TABLE `informacionarticuloventa_insumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `informacionarticuloventa_insumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insumo`
--

DROP TABLE IF EXISTS `insumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insumo` (
  `idInsumo` bigint NOT NULL AUTO_INCREMENT,
  `baja` tinyint(1) NOT NULL DEFAULT '0',
  `denominacion` varchar(255) NOT NULL,
  `esExtra` bit(1) NOT NULL,
  `esInsumo` bit(1) NOT NULL,
  `unidadMedida` varchar(255) NOT NULL,
  `idRubro` bigint NOT NULL,
  `idStock` bigint NOT NULL,
  PRIMARY KEY (`idInsumo`),
  KEY `FKaul117ixxf3b3im6dmxaxsnts` (`idRubro`),
  KEY `FKhin7lpolmf7r2u3cx3wsvt1k5` (`idStock`),
  CONSTRAINT `FKaul117ixxf3b3im6dmxaxsnts` FOREIGN KEY (`idRubro`) REFERENCES `rubroinsumo` (`idRubroInsumo`),
  CONSTRAINT `FKhin7lpolmf7r2u3cx3wsvt1k5` FOREIGN KEY (`idStock`) REFERENCES `stock` (`idStock`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insumo`
--

LOCK TABLES `insumo` WRITE;
/*!40000 ALTER TABLE `insumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `insumo` ENABLE KEYS */;
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
  CONSTRAINT `FKaxpy7jnkxyiemmhwpryyksqmd` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`)
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
  `idPersona` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) NOT NULL,
  `baja` tinyint(1) NOT NULL DEFAULT '0',
  `contrasenia` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `foto` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `telefono` int NOT NULL,
  `usuario` varchar(255) NOT NULL,
  PRIMARY KEY (`idPersona`)
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
  `idInsumo` bigint NOT NULL,
  `idManufacturado` bigint NOT NULL,
  PRIMARY KEY (`idReceta`),
  KEY `FKp05k0p75y456y0nfrpaxwl8yy` (`idInsumo`),
  KEY `FKlk4tqorrqitu489uoj0kdkggf` (`idManufacturado`),
  CONSTRAINT `FKlk4tqorrqitu489uoj0kdkggf` FOREIGN KEY (`idManufacturado`) REFERENCES `articulomanufacturado` (`idArticuloManufacturado`),
  CONSTRAINT `FKp05k0p75y456y0nfrpaxwl8yy` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`idInsumo`)
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
  `idInsumo` bigint NOT NULL,
  `idRecetaSugerida` bigint NOT NULL,
  PRIMARY KEY (`idSugerencia`),
  KEY `FK4octeds5a27vbbsimmljsi966` (`idInsumo`),
  KEY `FKc2jjkpcdeds5f54dc1vovk5rj` (`idRecetaSugerida`),
  CONSTRAINT `FK4octeds5a27vbbsimmljsi966` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`idInsumo`),
  CONSTRAINT `FKc2jjkpcdeds5f54dc1vovk5rj` FOREIGN KEY (`idRecetaSugerida`) REFERENCES `sugerenciachef` (`idSugerencia`)
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
  PRIMARY KEY (`idRubroInsumo`)
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
  `baja` tinyint(1) NOT NULL DEFAULT '0',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sugerenciachef`
--

DROP TABLE IF EXISTS `sugerenciachef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sugerenciachef` (
  `idSugerencia` bigint NOT NULL AUTO_INCREMENT,
  `aptoCeliaco` bit(1) NOT NULL,
  `denominacion` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `imagen` varchar(255) NOT NULL,
  `tiempoCocina` int NOT NULL,
  `vegano` bit(1) NOT NULL,
  `vegetariano` bit(1) NOT NULL,
  PRIMARY KEY (`idSugerencia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sugerenciachef`
--

LOCK TABLES `sugerenciachef` WRITE;
/*!40000 ALTER TABLE `sugerenciachef` DISABLE KEYS */;
/*!40000 ALTER TABLE `sugerenciachef` ENABLE KEYS */;
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
  KEY `FK5wsu9jc73rxhb2msbco5dc86n` (`idCliente`),
  CONSTRAINT `FK5wsu9jc73rxhb2msbco5dc86n` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`)
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

-- Dump completed on 2020-06-12 15:46:31
