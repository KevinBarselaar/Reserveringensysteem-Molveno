-- MySQL dump 10.13  Distrib 5.7.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: BOOKING
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` bigint(20) NOT NULL,
  `adult_capacity` int(11) NOT NULL,
  `available` bit(1) NOT NULL,
  `disabled_friendly` bit(1) NOT NULL,
  `floor` int(11) NOT NULL,
  `minor_capacity` int(11) NOT NULL,
  `price` double NOT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (75,8,_binary '',_binary '\0',4,2,255,3),(74,2,_binary '',_binary '\0',4,2,165,1),(73,2,_binary '',_binary '\0',4,1,175,1),(72,2,_binary '',_binary '\0',4,1,175,1),(71,2,_binary '',_binary '\0',4,0,165,1),(70,2,_binary '',_binary '\0',4,0,165,1),(69,2,_binary '',_binary '\0',4,0,155,1),(68,2,_binary '',_binary '\0',4,0,155,1),(67,2,_binary '',_binary '',4,0,100,1),(66,1,_binary '',_binary '',4,0,100,0),(65,1,_binary '',_binary '',4,0,100,0),(64,1,_binary '',_binary '\0',4,1,100,0),(63,1,_binary '',_binary '\0',4,1,100,0),(62,1,_binary '',_binary '\0',4,1,100,0),(61,1,_binary '',_binary '\0',4,1,100,0),(60,1,_binary '',_binary '\0',4,0,5,0),(59,1,_binary '',_binary '\0',4,0,95,0),(58,1,_binary '',_binary '\0',4,0,95,0),(57,2,_binary '',_binary '\0',3,2,175,1),(56,2,_binary '',_binary '\0',3,2,175,1),(55,2,_binary '',_binary '\0',3,2,175,1),(54,2,_binary '',_binary '\0',3,1,175,1),(53,4,_binary '',_binary '\0',3,2,255,2),(52,2,_binary '',_binary '\0',3,0,165,1),(51,2,_binary '',_binary '\0',3,0,165,1),(50,2,_binary '',_binary '\0',3,0,155,1),(49,2,_binary '',_binary '\0',3,0,155,1),(48,2,_binary '',_binary '',3,0,155,1),(47,1,_binary '',_binary '',3,0,100,0),(46,1,_binary '',_binary '',3,0,100,0),(45,1,_binary '',_binary '\0',3,1,100,0),(44,1,_binary '',_binary '\0',3,1,100,0),(43,1,_binary '',_binary '\0',3,1,100,0),(42,1,_binary '',_binary '\0',3,1,100,0),(41,1,_binary '',_binary '\0',3,0,95,0),(40,1,_binary '',_binary '\0',3,0,95,0),(39,1,_binary '',_binary '\0',3,0,95,0),(38,2,_binary '',_binary '\0',2,2,195,1),(37,4,_binary '',_binary '\0',2,2,255,2),(36,2,_binary '',_binary '\0',2,2,195,1),(35,2,_binary '',_binary '\0',2,1,175,1),(34,2,_binary '',_binary '\0',2,1,175,1),(33,2,_binary '',_binary '\0',2,0,165,1),(32,2,_binary '',_binary '\0',2,0,165,1),(31,2,_binary '',_binary '\0',2,0,155,1),(30,2,_binary '',_binary '\0',2,0,155,1),(29,2,_binary '',_binary '',2,0,155,1),(28,1,_binary '',_binary '',2,0,100,0),(27,1,_binary '',_binary '',2,0,100,0),(26,1,_binary '',_binary '\0',2,1,100,0),(25,1,_binary '',_binary '\0',2,1,100,0),(24,1,_binary '',_binary '\0',2,1,100,0),(23,1,_binary '',_binary '\0',2,1,100,0),(22,1,_binary '',_binary '\0',2,0,95,0),(21,1,_binary '',_binary '\0',2,0,95,0),(20,1,_binary '',_binary '\0',2,0,95,0),(19,4,_binary '',_binary '\0',1,2,255,2),(18,2,_binary '',_binary '\0',1,2,195,1),(17,2,_binary '',_binary '\0',1,2,195,1),(16,2,_binary '',_binary '\0',1,1,175,1),(15,2,_binary '',_binary '\0',1,1,175,1),(14,2,_binary '',_binary '\0',1,0,165,1),(13,2,_binary '',_binary '\0',1,0,165,1),(12,2,_binary '',_binary '\0',1,0,155,1),(11,2,_binary '',_binary '\0',1,0,155,1),(10,2,_binary '',_binary '',1,0,155,1),(9,1,_binary '',_binary '',1,0,100,0),(8,1,_binary '',_binary '',1,0,100,0),(7,1,_binary '',_binary '\0',1,1,100,0),(6,1,_binary '',_binary '\0',1,1,100,0),(5,1,_binary '',_binary '\0',1,1,100,0),(4,1,_binary '',_binary '\0',1,1,100,0),(3,1,_binary '',_binary '\0',1,0,95,0),(2,1,_binary '',_binary '\0',1,0,95,0),(1,1,_binary '',_binary '\0',1,0,95,0),(76,8,_binary '',_binary '\0',4,2,255,3);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-10 16:01:52
