CREATE DATABASE  IF NOT EXISTS `elbuensabor` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016;
USE `elbuensabor`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: elbuensabor
-- ------------------------------------------------------
-- Server version	8.0.22

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
INSERT INTO `articulomanufacturado` VALUES (_binary '\0',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',1,1),(_binary '',1,'Hamburguesa con queso',30,_binary '',_binary '',2,2),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',9,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',10,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',11,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',12,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',13,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',14,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',15,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',16,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',17,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',18,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',19,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',20,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',21,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',22,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',23,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',24,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',25,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',26,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',27,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',28,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',29,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',30,4),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',31,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',32,4),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',33,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',34,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',35,4),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',36,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',37,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',38,4),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',39,4),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',40,6),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',41,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',42,4),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',43,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',44,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',45,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',46,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',47,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',48,4),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',49,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',50,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',51,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',52,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',53,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',54,4),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',55,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',56,4),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',57,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',58,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',59,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',60,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',61,2),(_binary '',0,'dDAS',11,_binary '',_binary '',62,5),(_binary '',0,'dDAS',11,_binary '',_binary '',63,3),(_binary '',0,'AAA',111,_binary '',_binary '',64,5),(_binary '',0,'dDAS',11,_binary '',_binary '',65,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',66,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',67,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',68,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',69,6),(_binary '\0',0,'a',1,_binary '\0',_binary '',70,5),(_binary '',0,'AAA',111,_binary '',_binary '',71,6),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',72,6),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',73,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',74,4),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',75,4),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',76,5),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',77,5),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',78,5),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',79,5),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',80,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',81,5),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',82,2),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',83,4),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',84,5),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',85,5),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',86,4),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',87,5),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',88,3),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',89,3),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',90,2),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',91,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',92,3),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',93,2),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',94,3),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',95,3),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',96,5),(_binary '',0,'Hamburguesa con queso',30,_binary '',_binary '',97,3),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',98,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',99,3),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',100,3),(_binary '',0,'Pizza con anchoas',11,_binary '\0',_binary '\0',101,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',102,3),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',103,4),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',104,3),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',105,3),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',106,4),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',107,5),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',108,5),(_binary '',0,'Hamburguesa con queso',30,_binary '',_binary '',109,3),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',110,2),(_binary '',0,'Pizza con anchoas',20,_binary '\0',_binary '\0',111,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',112,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',113,3),(_binary '',0,'Sándwich de Atún',5,_binary '',_binary '',114,3);
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
INSERT INTO `cliente` VALUES (7);
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
  KEY `FKqskjqmjbcu4wcn120ovey9hm3` (`idPersona`),
  CONSTRAINT `FKqskjqmjbcu4wcn120ovey9hm3` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domicilio`
--

LOCK TABLES `domicilio` WRITE;
/*!40000 ALTER TABLE `domicilio` DISABLE KEYS */;
INSERT INTO `domicilio` VALUES (1,0,'San Martin','C','Godoy calle',1338,2,5),(2,0,'San Martin','C','Godoy calle',1338,2,5),(3,0,'San Martin','C','Godoy calle',1338,2,6),(4,0,'San Martin','C','Godoy calle',1338,2,4),(5,0,'San Martin','C','Godoy calle',1338,2,4);
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
INSERT INTO `empleado` VALUES ('cocina',4),('admin',5),('admin',6);
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
  `fechaCompra` datetime NOT NULL,
  `precioUnitario` float NOT NULL,
  `idInsumo` bigint NOT NULL,
  PRIMARY KEY (`idCompra`),
  KEY `FK1gmxti380q4ukilmuts8d4u33` (`idInsumo`),
  CONSTRAINT `FK1gmxti380q4ukilmuts8d4u33` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`idInsumo`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historialcompraaproveedores`
--

LOCK TABLES `historialcompraaproveedores` WRITE;
/*!40000 ALTER TABLE `historialcompraaproveedores` DISABLE KEYS */;
INSERT INTO `historialcompraaproveedores` VALUES (1,3,'2010-01-31 00:00:00',120,2),(2,4,'2020-02-10 00:00:00',140,10),(3,5,'2009-01-01 00:00:00',100,7),(4,5,'2008-01-01 00:00:00',106,14),(5,200,'2015-01-31 00:00:00',15.321,11),(6,300,'2009-01-31 00:00:00',24,13),(7,400,'2008-01-01 00:00:00',21,1),(8,80,'2020-11-04 03:00:00',80,18);
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
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacionarticuloventa`
--

LOCK TABLES `informacionarticuloventa` WRITE;
/*!40000 ALTER TABLE `informacionarticuloventa` DISABLE KEYS */;
INSERT INTO `informacionarticuloventa` VALUES (1,'Pizza muy rica para compartir con amigos','activo-6.png',120),(2,'Hamburguesa muy rica para compartir con amigos','1.jpg',200),(3,'Bebida gaseosa','1.jpg',59),(9,'Ideal para Pato el pez','activo-3.png',0),(10,'Ideal para Pato el pez','activo-3.png',0),(11,'Ideal para Pato el pez','activo-3.png',0),(12,'Ideal para Pato el pez','activo-3.png',0),(13,'Ideal para Pato el pez','heladosDelArbol.jpg',0),(14,'Ideal para Pato el pez','heladosDelArbol.jpg',0),(15,'Ideal para Pato el pez','heladosDelArbol.jpg',0),(16,'Ideal para Pato el pez','heladosDelArbol.jpg',0),(17,'Ideal para Pato el pez','heladosDelArbol.jpg',0),(18,'Ideal para Pato el pez','heladosDelArbol.jpg',0),(19,'Ideal para Pato el pez','heladosDelArbol.jpg',0),(20,'Ideal para Pato el pez','heladosDelArbol.jpg',0),(21,'Ideal para Pato el pez','heladosDelArbol.jpg',0),(22,'Ideal para Pato el pez','heladosDelArbol.jpg',0),(23,'Ideal para Pato el pez','activo-7.png',0),(24,'Ideal para Pato el pez','activo-7.png',0),(25,'Ideal para Pato el pez','activo-7.png',0),(26,'Ideal para Pato el pez','activo-7.png',6396.5),(27,'Ideal para Pato el pez','activo-7.png',11),(28,'Ideal para Pato el pez','activo-7.png',10000),(29,'Ideal para Pato el pez','activo-7.png',111),(30,'Ideal para Pato el pez','activo-7.png',550),(31,'Ideal para Pato el pez','activo-7.png',5550),(32,'Ideal para Pato el pez','activo-7.png',1),(33,'Ideal para Pato el pez','activo-7.png',0),(34,'Ideal para Pato el pez','activo-7.png',111),(35,'Ideal para Pato el pez','activo-7.png',111),(36,'Ideal para Pato el pez','activo-7.png',0),(37,'Ideal para Pato el pez','activo-7.png',1),(38,'Ideal para Pato el pez','activo-7.png',0),(39,'Ideal para Pato el pez','activo-7.png',0),(40,'Ideal para Pato el pez','activo-7.png',11),(41,'Ideal para Pato el pez','activo-7.png',0),(42,'Ideal para Pato el pez','activo-7.png',0),(43,'Ideal para Pato el pez','activo-7.png',0),(44,'Ideal para Pato el pez','activo-7.png',11),(45,'Ideal para Pato el pez','activo-7.png',0),(46,'Ideal para Pato el pez','activo-7.png',0),(47,'Ideal para Pato el pez','activo-7.png',0),(48,'Ideal para Pato el pez','activo-7.png',0),(49,'Ideal para Pato el pez','fondoIndex.jpg',0),(50,'Ideal para Pato el pez','fondoIndex.jpg',11111),(51,'Ideal para Pato el pez','fondoIndex.jpg',111),(52,'Ideal para Pato el pez','fondoIndex.jpg',111),(53,'Ideal para Pato el pez','fondoIndex.jpg',111),(54,'Ideal para Pato el pez','fondoIndex.jpg',11),(55,'Ideal para Pato el pez','fondoIndex.jpg',0),(56,'Ideal para Pato el pez','fondoIndex.jpg',111),(57,'Ideal para Pato el pez','fondoIndex.jpg',120),(58,'Ideal para Pato el pez','fondoIndex.jpg',11),(59,'Ideal para Pato el pez','fondoIndex.jpg',11),(60,'Ideal para Pato el pez','fondoIndex.jpg',111),(61,'Ideal para Pato el pez','fondoIndex.jpg',111),(62,'ASDASD','588a6ad0d06f6719692a2d29.png',500),(63,'ASDASD','588a6ad0d06f6719692a2d29.png',500),(64,'AA','588a6ad0d06f6719692a2d29.png',55),(65,'ASDASD','588a6ad0d06f6719692a2d29.png',500),(66,'Ideal para Pato el pez','1.jpg',500),(67,'Ideal para Pato el pez','2.jpg',500),(68,'Ideal para Pato el pez','3.jpg',1322),(69,'Ideal para Pato el pez','3.jpg',33),(70,'a','3.jpg',44),(71,'AA','3.jpg',11),(72,'Pizza muy rica para compartir con amigos','2.jpg',11),(73,'Pizza muy rica para compartir con amigos','2.jpg',544),(74,'Ideal para Pato el pez','1.jpg',11),(75,'Pizza muy rica para compartir con amigos','3.jpg',11),(76,'Pizza muy rica para compartir con amigos','2.jpg',11),(77,'Pizza muy rica para compartir con amigos','3.jpg',0),(78,'Pizza muy rica para compartir con amigos','2.jpg',111),(79,'Ideal para Pato el pez','3.jpg',617),(80,'Pizza muy rica para compartir con amigos','2.jpg',11),(81,'Ideal para Pato el pez','2.jpg',111),(82,'Pizza muy rica para compartir con amigos','1.jpg',43.7),(83,'Pizza muy rica para compartir con amigos','2.jpg',144),(84,'Pizza muy rica para compartir con amigos','3.jpg',11),(85,'Pizza muy rica para compartir con amigos','1.jpg',11),(86,'Pizza muy rica para compartir con amigos','2.jpg',9.7),(87,'Pizza muy rica para compartir con amigos','3.jpg',1),(88,'Pizza muy rica para compartir con amigos','1.jpg',11),(89,'Pizza muy rica para compartir con amigos','1.jpg',11),(90,'Pizza muy rica para compartir con amigos','1.jpg',11),(91,'Pizza muy rica para compartir con amigos','1.jpg',144.1),(92,'Ideal para Pato el pez','3.jpg',2.1),(93,'Pizza muy rica para compartir con amigos','2.jpg',111),(94,'Pizza muy rica para compartir con amigos','3.jpg',112),(95,'Pizza muy rica para compartir con amigos','3.jpg',11),(96,'Pizza muy rica para compartir con amigos','2.jpg',11),(97,'Hamburguesa muy rica para compartir con amigos','2.jpg',111),(98,'Pizza muy rica para compartir con amigos','1.jpg',11),(99,'Ideal para Pato el pez','3.jpg',11),(100,'Pizza muy rica para compartir con amigos','1.jpg',111),(101,'Pizza muy rica para compartir con amigos','1.jpg',111),(102,'Ideal para Pato el pez','1.jpg',11),(103,'Pizza muy rica para compartir con amigos','1.jpg',111),(104,'Pizza muy rica para compartir con amigos','3.jpg',11),(105,'Pizza muy rica para compartir con amigos','1.jpg',11),(106,'Pizza muy rica para compartir con amigos','1.jpg',202.29),(107,'Pizza muy rica para compartir con amigos','3.jpg',18.39),(108,'Pizza muy rica para compartir con amigos','3.jpg',180),(109,'Hamburguesa muy rica para compartir con amigos','3.jpg',18900),(110,'Pizza muy rica para compartir con amigos','3.jpg',111),(111,'Pizza muy rica para compartir con amigos','2.jpg',1111),(112,'Ideal para Pato el pez','1.jpg',111),(113,'Ideal para Pato el pez','1.jpg',5555),(114,'Ideal para Pato el pez','2.jpg',1111),(115,'Salchicha','IMG-20190913-WA0032.jpeg',14),(116,'papas','2.jpg',15),(117,'Coca Cola Fresca','3.jpg',80);
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
  KEY `FKgx7s77masrtaiq2sj70fg3d06` (`idInsumo`),
  CONSTRAINT `FK4pqenon9ruljy3s0o8ldbynve` FOREIGN KEY (`idInsumoVenta`) REFERENCES `informacionarticuloventa` (`idArticuloVenta`),
  CONSTRAINT `FKgx7s77masrtaiq2sj70fg3d06` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`idInsumo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacionarticuloventa_insumo`
--

LOCK TABLES `informacionarticuloventa_insumo` WRITE;
/*!40000 ALTER TABLE `informacionarticuloventa_insumo` DISABLE KEYS */;
INSERT INTO `informacionarticuloventa_insumo` VALUES (3,13),(115,16),(116,17),(117,18);
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insumo`
--

LOCK TABLES `insumo` WRITE;
/*!40000 ALTER TABLE `insumo` DISABLE KEYS */;
INSERT INTO `insumo` VALUES (1,1,'Harina 0000',_binary '',_binary '','gr',232,1),(2,0,'Aceite de girasol',_binary '',_binary '','lt',22,2),(7,0,'sal',_binary '\0',_binary '','gr',21,6),(10,0,'Anchoas',_binary '',_binary '','gr',51,7),(11,0,'Aceitunas verdes',_binary '\0',_binary '','gr',2,8),(12,0,'Queso cremoso',_binary '\0',_binary '','gr',3,9),(13,0,'Sprite',_binary '\0',_binary '\0','u',1211,10),(14,0,'Maizena',_binary '\0',_binary '','gr',232,11),(15,0,'Sal gruesa',_binary '\0',_binary '','gr',21,12),(16,0,'Salchichas',_binary '',_binary '','kg',52,19),(17,0,'Papa',_binary '',_binary '','kg',4,20),(18,0,'CocaCola 500',_binary '\0',_binary '\0','u',1211,21);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `telefono` varchar(255) NOT NULL,
  `usuario` varchar(255) NOT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (4,'Gómez',0,'234','123@gmail.com','123.jpeg','Eduardo','234254','egomez'),(5,'Masera',0,'123','123@gmail.com','sdqrtgrsfg4t.jpg','Francisco','123','fm94'),(6,'Crespo',0,'543','123@gmail.com','sdqrtgrsfg4t.jpg','Geo','123','fgc94'),(7,'Sardá',0,'12345678AA','federico24@gmail.com','4444.jpg','Federico','2613890863','fede');
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
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receta`
--

LOCK TABLES `receta` WRITE;
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
INSERT INTO `receta` VALUES (1,0,20,10,1),(2,0,15,2,1),(3,0,2,7,2),(4,0,2,10,2),(5,0,20,11,56),(6,0,0.2,2,56),(7,0,50,7,56),(8,0,20,11,56),(9,0,0.2,2,56),(10,0,50,7,56),(11,0,20,11,57),(12,0,50,7,57),(13,0,0.2,2,57),(14,0,20,11,58),(15,0,0.2,2,58),(16,0,50,7,58),(17,0,20,11,59),(18,0,50,7,59),(19,0,0.2,2,59),(20,0,0.2,2,60),(21,0,50,7,60),(22,0,20,11,60),(23,0,20,11,61),(24,0,50,7,61),(25,0,0.2,2,61),(26,0,1,2,62),(27,0,1,10,62),(28,0,1,1,64),(29,0,1,7,64),(30,0,1,2,64),(31,0,1,10,65),(32,0,2,7,66),(33,0,11,2,67),(34,0,111,10,68),(35,0,4,11,69),(36,0,1,11,70),(37,0,1,11,71),(38,0,1,10,72),(39,0,1,7,73),(40,0,11,11,74),(41,0,1,7,75),(42,0,111,11,76),(43,0,4,11,78),(44,0,4,10,79),(45,0,1,2,80),(46,0,4,11,81),(47,0,7,10,82),(48,0,1,2,83),(49,0,11,7,84),(50,0,11,10,85),(51,0,11,10,86),(52,0,11,10,87),(53,0,11,2,88),(54,0,1,7,89),(55,0,121,11,90),(56,0,1,2,91),(57,0,1,11,92),(58,0,11,11,93),(59,0,11,11,94),(60,0,1,10,95),(61,0,1,11,95),(62,0,1,11,96),(63,0,111,11,97),(64,0,1,2,98),(65,0,1,10,98),(66,0,1,10,99),(67,0,1,7,100),(68,0,1,2,101),(69,0,1,11,102),(70,0,11,11,103),(71,0,1,2,104),(72,0,1,7,105),(73,0,1,11,105),(74,0,11,11,106),(75,0,1,11,107),(76,0,1,10,108),(77,0,1,11,108),(78,0,11,10,109),(79,0,11,7,109),(80,0,1,2,110),(81,0,1,7,110),(82,0,1,11,111),(83,0,11,10,112),(84,0,1,7,114);
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recetasugerida`
--

DROP TABLE IF EXISTS `recetasugerida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recetasugerida` (
  `idRecetaSugerida` bigint NOT NULL AUTO_INCREMENT,
  `cantidadInsumo` float NOT NULL,
  `idInsumo` bigint NOT NULL,
  `idSugerencia` bigint NOT NULL,
  PRIMARY KEY (`idRecetaSugerida`),
  KEY `FK4octeds5a27vbbsimmljsi966` (`idInsumo`),
  KEY `FKf6didu3nsm8cg4lfmkgw2hroy` (`idSugerencia`),
  CONSTRAINT `FK4octeds5a27vbbsimmljsi966` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`idInsumo`),
  CONSTRAINT `FKf6didu3nsm8cg4lfmkgw2hroy` FOREIGN KEY (`idSugerencia`) REFERENCES `sugerenciachef` (`idSugerencia`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recetasugerida`
--

LOCK TABLES `recetasugerida` WRITE;
/*!40000 ALTER TABLE `recetasugerida` DISABLE KEYS */;
INSERT INTO `recetasugerida` VALUES (91,1,7,77),(92,1,11,78),(93,1,10,79),(94,1,7,79);
/*!40000 ALTER TABLE `recetasugerida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rubroinsumo`
--

DROP TABLE IF EXISTS `rubroinsumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rubroinsumo` (
  `idRubroInsumo` bigint NOT NULL,
  `denominacion` varchar(255) NOT NULL,
  PRIMARY KEY (`idRubroInsumo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rubroinsumo`
--

LOCK TABLES `rubroinsumo` WRITE;
/*!40000 ALTER TABLE `rubroinsumo` DISABLE KEYS */;
INSERT INTO `rubroinsumo` VALUES (1,'Bebidas'),(2,'Almacén'),(3,'Lácteos y Embutidos'),(4,'Frutas y Verduras'),(5,'Carnes'),(11,'Alcohólicas'),(12,'Sin Alcohol'),(21,'Especias'),(22,'Aceites'),(23,'Panificados'),(24,'Legumbres'),(25,'Aderezos'),(31,'Quesos'),(32,'Fiambres'),(51,'Blancas'),(52,'Rojas'),(111,'Cervezas'),(112,'Vinos'),(121,'Gasificadas'),(122,'No Gasificadas'),(231,'Integral'),(232,'Blanco'),(1211,'Gaseosa'),(1212,'Soda'),(1221,'Agua'),(1222,'Agua Saborizada'),(1223,'Jugo');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rubromanufacturado`
--

LOCK TABLES `rubromanufacturado` WRITE;
/*!40000 ALTER TABLE `rubromanufacturado` DISABLE KEYS */;
INSERT INTO `rubromanufacturado` VALUES (1,0,'Pizzas'),(2,0,'Lomos'),(3,0,'Hamburguesas'),(4,0,'Papas'),(5,0,'Sándwiches'),(6,0,'Postres');
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
  `actual` float NOT NULL,
  `maximo` float NOT NULL,
  `minimo` float NOT NULL,
  PRIMARY KEY (`idStock`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,90,100,500),(2,3,200,50),(6,300,500,200),(7,200,400,50),(8,0,20,10),(9,0,1000,500),(10,0,50,20),(11,9,10,2),(12,24,12,2),(13,15,200,12),(14,0,200,2),(15,0,0,0),(16,0,200,2),(17,0,200,13),(18,0,300,50),(19,0,40,15),(20,0,150,30),(21,80,100,50);
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
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sugerenciachef`
--

LOCK TABLES `sugerenciachef` WRITE;
/*!40000 ALTER TABLE `sugerenciachef` DISABLE KEYS */;
INSERT INTO `sugerenciachef` VALUES (77,_binary '','Hamburguesa con queso','Hamburguesa muy rica para compartir con amigos','3.jpg',30,_binary '',_binary ''),(78,_binary '','Pizza con anchoas','Pizza muy rica para compartir con amigos','1.jpg',20,_binary '',_binary ''),(79,_binary '\0','Barrosluco','Rico','3.jpg',21,_binary '\0',_binary '\0');
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

--
-- Dumping routines for database 'elbuensabor'
--
/*!50003 DROP FUNCTION IF EXISTS `SPLIT_STRING` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `SPLIT_STRING`(
	str VARCHAR(255) ,
	delim VARCHAR(12) ,
	pos INT
) RETURNS varchar(255) CHARSET utf8
    NO SQL
RETURN REPLACE(	
    SUBSTRING(
		SUBSTRING_INDEX(str , delim , pos) ,
		CHAR_LENGTH(
			SUBSTRING_INDEX(str , delim , pos - 1)
		) + 1
	) ,
	delim ,
	''
) ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getInsumosVentaByFiltro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getInsumosVentaByFiltro`(IN params varchar(255))
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
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getManufacturadosByFiltro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getManufacturadosByFiltro`(IN params varchar(255))
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
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-06  1:03:12
