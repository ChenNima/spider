CREATE DATABASE  IF NOT EXISTS `spiderdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `spiderDB`;
-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: localhost    Database: spiderDB
-- ------------------------------------------------------
-- Server version	5.5.40

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
-- Table structure for table `JDitems`
--

DROP TABLE IF EXISTS `JDitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `JDitems` (
  `P_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Iname` varchar(255) DEFAULT NULL,
  `Iid` varchar(255) NOT NULL,
  `Ibrand` varchar(255) DEFAULT NULL,
  `Ihost` varchar(255) DEFAULT NULL,
  `Iprice` varchar(255) DEFAULT NULL,
  `Ifirst_cat` varchar(255) DEFAULT NULL,
  `Isecond_cat` varchar(255) DEFAULT NULL,
  `Ithird_cat` varchar(255) DEFAULT NULL,
  `Iurl` varchar(255) DEFAULT NULL,
  `Iimg_url` varchar(255) DEFAULT NULL,
  `Idescription` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`P_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `JDitems`
--

LOCK TABLES `JDitems` WRITE;
/*!40000 ALTER TABLE `JDitems` DISABLE KEYS */;
/*!40000 ALTER TABLE `JDitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SNitems`
--

DROP TABLE IF EXISTS `SNitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SNitems` (
  `P_id` int(11) NOT NULL AUTO_INCREMENT,
  `Iname` varchar(255) DEFAULT NULL,
  `Iid` varchar(255) NOT NULL,
  `Ibrand` varchar(255) DEFAULT NULL,
  `Ihost` varchar(255) DEFAULT NULL,
  `Iprice` varchar(255) DEFAULT NULL,
  `Ifirst_cat` varchar(255) DEFAULT NULL,
  `Isecond_cat` varchar(255) DEFAULT NULL,
  `Ithird_cat` varchar(255) DEFAULT NULL,
  `Iurl` varchar(255) DEFAULT NULL,
  `Iimg_url` varchar(255) DEFAULT NULL,
  `Idescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`P_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SNitems`
--

LOCK TABLES `SNitems` WRITE;
/*!40000 ALTER TABLE `SNitems` DISABLE KEYS */;
/*!40000 ALTER TABLE `SNitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `YHDitems`
--

DROP TABLE IF EXISTS `YHDitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `YHDitems` (
  `P_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Iname` varchar(255) DEFAULT NULL,
  `Iid` varchar(255) NOT NULL,
  `Ibrand` varchar(255) DEFAULT NULL,
  `Ihost` varchar(255) DEFAULT NULL,
  `Iprice` varchar(255) DEFAULT NULL,
  `Ifirst_cat` varchar(255) DEFAULT NULL,
  `Isecond_cat` varchar(255) DEFAULT NULL,
  `Ithird_cat` varchar(255) DEFAULT NULL,
  `Iurl` varchar(255) DEFAULT NULL,
  `Iimg_url` varchar(255) DEFAULT NULL,
  `Idescription` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`P_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `YHDitems`
--

LOCK TABLES `YHDitems` WRITE;
/*!40000 ALTER TABLE `YHDitems` DISABLE KEYS */;
/*!40000 ALTER TABLE `YHDitems` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-31 11:58:36
