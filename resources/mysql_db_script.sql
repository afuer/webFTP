CREATE DATABASE  IF NOT EXISTS `FTPSERVERU` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `FTPSERVERU`;
-- MySQL dump 10.13  Distrib 5.6.27, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: FTPSERVERU
-- ------------------------------------------------------
-- Server version	5.6.27

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
-- Table structure for table `FTP_EXCEPTIONS`
--

DROP TABLE IF EXISTS `FTP_EXCEPTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FTP_EXCEPTIONS` (
  `EXCEPTION_ID` int(6) NOT NULL AUTO_INCREMENT,
  `EXCEPTION_DETAILS` varchar(4000) CHARACTER SET utf8 DEFAULT NULL,
  `JOB_ID` int(6) DEFAULT NULL,
  `LOG_DATE` date DEFAULT NULL,
  PRIMARY KEY (`EXCEPTION_ID`),
  KEY `fk_FTP_EXCEPTIONS_JOB_FK_idx` (`JOB_ID`),
  CONSTRAINT `fk_FTP_EXCEPTIONS_JOB_FK1` FOREIGN KEY (`JOB_ID`) REFERENCES `FTP_JOB` (`JOB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FTP_EXCEPTIONS`
--

LOCK TABLES `FTP_EXCEPTIONS` WRITE;
/*!40000 ALTER TABLE `FTP_EXCEPTIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `FTP_EXCEPTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FTP_JOB`
--

DROP TABLE IF EXISTS `FTP_JOB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FTP_JOB` (
  `JOB_ID` int(6) NOT NULL AUTO_INCREMENT,
  `JOB_NAME` varchar(45) DEFAULT NULL,
  `CRON_EXP` varchar(33) DEFAULT NULL,
  `SRC_SERVER_URL` varchar(55) DEFAULT NULL,
  `SRC_SERVER_USER` varchar(33) DEFAULT NULL,
  `SRC_SERVER_PASS` varchar(33) DEFAULT NULL,
  `SRC_SERVER_PATH` varchar(200) DEFAULT NULL,
  `DEST_SERVER_URL` varchar(200) DEFAULT NULL,
  `DEST_SERVER_USER` varchar(33) DEFAULT NULL,
  `DEST_SERVER_PASS` varchar(33) DEFAULT NULL,
  `DEST_SERVER_PATH` varchar(200) DEFAULT NULL,
  `DEST_SECURE_CON` int(6) DEFAULT NULL,
  `STATUS` int(6) DEFAULT '-1',
  `USER_ID` int(11) DEFAULT NULL,
  `SRC_SECURE_CON` int(11) DEFAULT NULL,
  `POST_FTP_OPTION` int(11) DEFAULT '0',
  `SRC_BACKUP_DIR` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`JOB_ID`),
  KEY `JOB_USER_FK1_idx` (`USER_ID`),
  CONSTRAINT `JOB_USR_FK1` FOREIGN KEY (`USER_ID`) REFERENCES `FTP_USER_TABLE` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `FTP_JOB_USER_MAPPING`
--

DROP TABLE IF EXISTS `FTP_JOB_USER_MAPPING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FTP_JOB_USER_MAPPING` (
  `JOB_USER_MAPPING_ID` int(6) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(6) DEFAULT NULL,
  `JOB_ID` int(6) DEFAULT NULL,
  PRIMARY KEY (`JOB_USER_MAPPING_ID`),
  KEY `FTP_JOB_USER_MAPPING_FK1_idx` (`JOB_ID`),
  KEY `FTP_JOB_USER_MAPPING_FK2_idx` (`USER_ID`),
  CONSTRAINT `FTP_JOB_USER_MAPPING_FK1` FOREIGN KEY (`JOB_ID`) REFERENCES `FTP_JOB` (`JOB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FTP_JOB_USER_MAPPING_FK2` FOREIGN KEY (`USER_ID`) REFERENCES `FTP_USER_TABLE` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FTP_JOB_USER_MAPPING`
--

LOCK TABLES `FTP_JOB_USER_MAPPING` WRITE;
/*!40000 ALTER TABLE `FTP_JOB_USER_MAPPING` DISABLE KEYS */;
/*!40000 ALTER TABLE `FTP_JOB_USER_MAPPING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FTP_USER_TABLE`
--

DROP TABLE IF EXISTS `FTP_USER_TABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FTP_USER_TABLE` (
  `USER_ID` int(6) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(33) DEFAULT NULL,
  `PASSWORD` varchar(33) DEFAULT NULL,
  `USER_TYPE_ID` int(6) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `USER_TYPE_FK1_idx` (`USER_TYPE_ID`),
  CONSTRAINT `USER_TYPE_FK1` FOREIGN KEY (`USER_TYPE_ID`) REFERENCES `FTP_USER_TYPE` (`USER_TYPE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `FTP_USER_TYPE`
--

DROP TABLE IF EXISTS `FTP_USER_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FTP_USER_TYPE` (
  `USER_TYPE_ID` int(6) NOT NULL AUTO_INCREMENT,
  `USER_TYPE_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`USER_TYPE_ID`),
  UNIQUE KEY `USER_TYPE_NAME_UNIQUE` (`USER_TYPE_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `STG_MASTER`
--

DROP TABLE IF EXISTS `STG_MASTER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STG_MASTER` (
  `OFFER_ID` int(11) DEFAULT NULL,
  `EXTERNAL_OFFER_ID` varchar(100) DEFAULT NULL,
  `CAMPAIGN_ID` varchar(1000) DEFAULT NULL,
  `LIFECYCLE_ID` int(11) DEFAULT NULL,
  `EXTERNAL_SERVICE_ID` varchar(100) DEFAULT NULL,
  `SERVICE_ID` int(11) DEFAULT NULL,
  `RP_ID` int(11) DEFAULT NULL,
  `RPA_CATEGORY` int(11) DEFAULT NULL,
  `RESTRICTION_CATEGORY` int(11) DEFAULT NULL,
  `IS_PDP` int(11) DEFAULT NULL,
  `QUOTA_AMOUNT` varchar(1000) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `STATUS` int(11) DEFAULT '0',
  `CANCELLATION_POLICY` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STG_MASTER`
--

LOCK TABLES `STG_MASTER` WRITE;
/*!40000 ALTER TABLE `STG_MASTER` DISABLE KEYS */;
/*!40000 ALTER TABLE `STG_MASTER` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-10  8:44:25
