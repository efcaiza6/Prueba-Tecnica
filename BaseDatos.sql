CREATE DATABASE  IF NOT EXISTS `prueba_tecnica` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `prueba_tecnica`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: prueba_tecnica
-- ------------------------------------------------------
-- Server version	5.7.44-log

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` varchar(255) NOT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `identificacion` varchar(255) NOT NULL,
  PRIMARY KEY (`identificacion`),
  UNIQUE KEY `UKqfu9lhga79cj19jnxshe1uhdj` (`id_cliente`),
  CONSTRAINT `FK1e94xjah82829p6l77xndwpms` FOREIGN KEY (`identificacion`) REFERENCES `persona` (`identificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('jl12345','1234','Activo','1234567890'),('mm12345','5678','Activo','1234567891'),('jo12345','1245','Activo','1234567892');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta` (
  `id_cuenta` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_id` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `numero_cuenta` varchar(255) DEFAULT NULL,
  `saldo_inicial` double DEFAULT NULL,
  `tipo_cuenta` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cuenta`),
  UNIQUE KEY `UKpj7ncg765kt4klndu25bwbwe4` (`numero_cuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
INSERT INTO `cuenta` VALUES (1,'1234567890','Activo','478758',1425,'Ahorros'),(2,'1234567891','Activo','225487',700,'Corriente'),(3,'1234567892','Activo','495878',150,'Ahorros'),(4,'1234567891','Activo','496825',100,'Ahorros'),(5,'1234567890','Activo','585545',1000,'Corriente');
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento`
--

DROP TABLE IF EXISTS `movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimiento` (
  `id_movimiento` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `tipo_movimiento` varchar(255) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `cuenta_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_movimiento`),
  KEY `FK4ea11fe7p3xa1kwwmdgi9f2fi` (`cuenta_id`),
  CONSTRAINT `FK4ea11fe7p3xa1kwwmdgi9f2fi` FOREIGN KEY (`cuenta_id`) REFERENCES `cuenta` (`id_cuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento`
--

LOCK TABLES `movimiento` WRITE;
/*!40000 ALTER TABLE `movimiento` DISABLE KEYS */;
INSERT INTO `movimiento` VALUES (1,'2025-05-17 22:41:39.564535',1425,'Retiro',-575,1),(2,'2025-05-17 22:42:16.058877',700,'Deposito',600,2),(3,'2025-05-17 22:42:42.902753',150,'Deposito',150,3),(4,'2025-05-17 22:43:06.812674',0,'Retiro',-540,4),(5,'2025-05-17 22:51:04.667733',100,'Deposito',100,4);
/*!40000 ALTER TABLE `movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `identificacion` varchar(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `edad` int(11) NOT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`identificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES ('1234567890','Otavalo sn y principal',30,'Masculino','Jose Lema','098254785'),('1234567891','Amazonas y  NNUU',33,'Femenino','Marianela Montalvo','097548965'),('1234567892','13 junio y Equinoccial',23,'Femenino','Juan Osorio','098874587');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-17 23:16:41
