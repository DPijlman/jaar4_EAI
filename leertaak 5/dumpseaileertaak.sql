CREATE DATABASE  IF NOT EXISTS `eaileertaak` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `eaileertaak`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: eaileertaak
-- ------------------------------------------------------
-- Server version	8.0.19

--
-- Table structure for table `groetformaat`
--

DROP TABLE IF EXISTS `groetformaat`;

CREATE TABLE `groetformaat` (
  `groetStijl` varchar(10) NOT NULL,
  `groet` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`groetStijl`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `groetformaat`
--

LOCK TABLES `groetformaat` WRITE;
INSERT  IGNORE INTO `groetformaat` VALUES ('beleefd','Beste'),('formeel','Geachte'),('jofiaal','Hoi');
UNLOCK TABLES;

--
-- Table structure for table `verzondengroeten`
--

DROP TABLE IF EXISTS `verzondengroeten`;

CREATE TABLE `verzondengroeten` (
  `verzondenGroeten` varchar(60) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
