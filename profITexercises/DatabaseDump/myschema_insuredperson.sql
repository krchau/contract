-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: myschema
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `insuredperson`
--

DROP TABLE IF EXISTS `insuredperson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `insuredperson` (
  `idInsured` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `patronymic` varchar(45) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `price` int(11) NOT NULL,
  `Contract_idContract` int(11) NOT NULL,
  PRIMARY KEY (`idInsured`,`Contract_idContract`),
  KEY `fk_contractId_idx` (`Contract_idContract`),
  CONSTRAINT `fk_contractId` FOREIGN KEY (`Contract_idContract`) REFERENCES `contract` (`idContract`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insuredperson`
--

LOCK TABLES `insuredperson` WRITE;
/*!40000 ALTER TABLE `insuredperson` DISABLE KEYS */;
INSERT INTO `insuredperson` VALUES (1,'SimpleHumanName1','SimpleHumanSurname','SimpleHumanPatronymic1','1999-02-03',100,1),(2,'SimpleHumanName2','SimpleHumanSurname','SimpleHumanPatronymic2','1999-02-03',100,1),(3,'SimpleHumanName3','ItisSimpleHumanSurname','SimpleHumanPatronymic3','1999-02-03',100,1),(4,'SimpleHumanName4','IsItSimpleHumanSurname','SimpleHumanPatronymic4','1999-02-03',100,1),(5,'SimpleHumanName5','B','SimpleHumanPatronymic5','1999-02-03',100,1),(6,'SimpleHumanName6','AB','SimpleHumanPatronymic6','1999-02-03',100,1),(7,'SimpleHumanName1','SimpleHumanSurname','SimpleHumanPatronymic1','1999-02-03',100,2),(8,'SimpleHumanName2','SimpleHumanSurname','SimpleHumanPatronymic2','1999-02-03',100,2),(9,'SimpleHumanName3','ItisSimpleHumanSurname','SimpleHumanPatronymic3','1999-02-03',100,2),(10,'test','ins','per','1000-01-01',400,2),(11,'SimpleHumanName5','B','SimpleHumanPatronymic5','1999-02-03',100,2),(12,'SimpleHumanName6','AB','SimpleHumanPatronymic6','1999-02-03',100,2),(19,'SimpleHumanName1','SimpleHumanSurname','SimpleHumanPatronymic1','1999-02-03',100,4),(20,'SimpleHumanName2','SimpleHumanSurname','SimpleHumanPatronymic2','1999-02-03',100,4),(21,'SimpleHumanName3','ItisSimpleHumanSurname','SimpleHumanPatronymic3','1999-02-03',100,4),(22,'SimpleHumanName4','IsItSimpleHumanSurname','SimpleHumanPatronymic4','1999-02-03',100,4),(23,'SimpleHumanName5','B','SimpleHumanPatronymic5','1999-02-03',100,4),(24,'SimpleHumanName6','AB','SimpleHumanPatronymic6','1999-02-03',100,4),(25,'SimpleHumanName1','SimpleHumanSurname','SimpleHumanPatronymic1','1999-02-03',100,5),(26,'SimpleHumanName2','SimpleHumanSurname','SimpleHumanPatronymic2','1999-02-03',100,5),(27,'SimpleHumanName3','ItisSimpleHumanSurname','SimpleHumanPatronymic3','1999-02-03',100,5),(28,'SimpleHumanName4','IsItSimpleHumanSurname','SimpleHumanPatronymic4','1999-02-03',100,5),(29,'SimpleHumanName5','B','SimpleHumanPatronymic5','1999-02-03',100,5),(30,'SimpleHumanName6','AB','SimpleHumanPatronymic6','1999-02-03',100,5),(31,'SimpleHumanName1','SimpleHumanSurname','SimpleHumanPatronymic1','1999-02-03',100,6),(32,'SimpleHumanName2','SimpleHumanSurname','SimpleHumanPatronymic2','1999-02-03',100,6),(33,'SimpleHumanName3','ItisSimpleHumanSurname','SimpleHumanPatronymic3','1999-02-03',100,6),(34,'SimpleHumanName4','IsItSimpleHumanSurname','SimpleHumanPatronymic4','1999-02-03',100,6),(35,'SimpleHumanName5','B','SimpleHumanPatronymic5','1999-02-03',100,6),(36,'SimpleHumanName6','AB','SimpleHumanPatronymic6','1999-02-03',100,6),(37,'SimpleHumanName1','SimpleHumanSurname','SimpleHumanPatronymic1','1999-02-03',100,7),(38,'SimpleHumanName2','SimpleHumanSurname','SimpleHumanPatronymic2','1999-02-03',100,7),(39,'SimpleHumanName3','ItisSimpleHumanSurname','SimpleHumanPatronymic3','1999-02-03',100,7),(40,'SimpleHumanName4','IsItSimpleHumanSurname','SimpleHumanPatronymic4','1999-02-03',100,7),(41,'SimpleHumanName5','B','SimpleHumanPatronymic5','1999-02-03',100,7),(42,'SimpleHumanName6','AB','SimpleHumanPatronymic6','1999-02-03',100,7);
/*!40000 ALTER TABLE `insuredperson` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-03 21:22:38
