-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: music_database
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `izvodjac`
--

DROP TABLE IF EXISTS `izvodjac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `izvodjac` (
  `IzvodjacID` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IzvodjacID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `izvodjac`
--

LOCK TABLES `izvodjac` WRITE;
/*!40000 ALTER TABLE `izvodjac` DISABLE KEYS */;
INSERT INTO `izvodjac` VALUES (1,'Test orkestar'),(2,'Test orkestar 2'),(4,'1'),(7,'Perimir'),(8,'Nikola'),(10,'Luisi'),(11,'asd'),(13,'yyyy'),(14,'yyy'),(15,'Latry'),(16,'Ensemble la Tempesta'),(17,'Slavojski');
/*!40000 ALTER TABLE `izvodjac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kompozicija`
--

DROP TABLE IF EXISTS `kompozicija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kompozicija` (
  `KompozicijaID` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(45) DEFAULT NULL,
  `UBaziOd` datetime DEFAULT NULL,
  `KorisnikID` int(11) DEFAULT NULL,
  PRIMARY KEY (`KompozicijaID`),
  KEY `komponovana od strane_idx` (`KorisnikID`),
  CONSTRAINT `komponovana od strane` FOREIGN KEY (`KorisnikID`) REFERENCES `korisnik` (`KorisnikID`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kompozicija`
--

LOCK TABLES `kompozicija` WRITE;
/*!40000 ALTER TABLE `kompozicija` DISABLE KEYS */;
INSERT INTO `kompozicija` VALUES (21,'Partita in A minor, BWV 827','2020-01-28 00:00:00',33),(43,'Fuge in D major bwv 532','2020-02-04 00:00:00',33),(44,'Scherzo No. 2, Op. 31','2020-02-04 00:00:00',43),(45,'Notturno Terzo: Lezione III, Quare de vulva','2020-02-04 00:00:00',44);
/*!40000 ALTER TABLE `kompozicija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `KorisnikID` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(45) DEFAULT NULL,
  `Prezime` varchar(45) DEFAULT NULL,
  `ClanOd` datetime DEFAULT NULL,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`KorisnikID`),
  UNIQUE KEY `Username_UNIQUE` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,'Leon','Vilovic','2018-10-10 00:00:00','admin','admin','ledutheman@gmail.com'),(2,'Pera','Peric','2010-10-20 00:00:00','pera','pera','pera@notgimail.com'),(3,'Mika','Mika','2019-11-11 00:00:00','mika','mika','blabla'),(4,'Nikola','Simonovic','2020-00-10 00:00:00','sima901','sima','sima@mail.com'),(10,'Mira','Mirkovic','2020-00-23 00:00:00','mira01','aaa',''),(16,'Eric','Maltez','2020-00-24 00:00:00','Eric123','123','Eric99988@gmail.com'),(25,'Marija','Tripkovic','2020-00-24 00:00:00','Amaranta','wicked33','philosopher6662002@gmail.com'),(27,'Nikola','Petrovic','2020-00-25 00:00:00','Nik90','123456','mojemail@skipmail.com'),(30,'Zika','Petrovic','2020-00-29 00:00:00','Zika','zika','Zikinemail'),(31,'Petar','Petrovic','2020-01-14 00:00:00','user1122','kjh.op.6','pera@gmx.com'),(33,'Bach ','Johann Sebastian','2020-01-14 00:00:00','bach1','bach1',''),(40,'Zika','Zikic','2020-01-16 00:00:00','Zika2','zika',''),(43,'Frederic F.','Chopin','2020-02-04 00:00:00','chopin','chopin',''),(44,'Nicola','Porpora','2020-02-04 00:00:00','nikola_porpora','123123',''),(45,'Laza','Lazarevic','2020-02-06 00:00:00','laza','laza','Laza@zmail.com');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kupovina`
--

DROP TABLE IF EXISTS `kupovina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kupovina` (
  `KorisnikID` int(11) NOT NULL,
  `KompozicijaID` int(11) NOT NULL,
  `DatumVremeKupovine` datetime NOT NULL,
  `Cena` double DEFAULT '0',
  `Valuta` varchar(45) DEFAULT 'EUR',
  `Popust` double DEFAULT '0',
  KEY `kupio_idx` (`KorisnikID`),
  KEY `kupljena_idx` (`KompozicijaID`),
  CONSTRAINT `kupio` FOREIGN KEY (`KorisnikID`) REFERENCES `korisnik` (`KorisnikID`) ON DELETE CASCADE,
  CONSTRAINT `kupljena` FOREIGN KEY (`KompozicijaID`) REFERENCES `kompozicija` (`KompozicijaID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kupovina`
--

LOCK TABLES `kupovina` WRITE;
/*!40000 ALTER TABLE `kupovina` DISABLE KEYS */;
INSERT INTO `kupovina` VALUES (2,21,'2020-02-03 00:00:00',1.5,'EUR',0);
/*!40000 ALTER TABLE `kupovina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partitura`
--

DROP TABLE IF EXISTS `partitura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partitura` (
  `PartituraID` int(11) NOT NULL AUTO_INCREMENT,
  `PutDoFajla` varchar(45) DEFAULT NULL,
  `Format` varchar(10) DEFAULT 'png',
  `Cena` double DEFAULT '0',
  `Valuta` varchar(45) DEFAULT 'EUR',
  `KompozicijaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PartituraID`),
  KEY `pripada_idx` (`KompozicijaID`),
  CONSTRAINT `pripada` FOREIGN KEY (`KompozicijaID`) REFERENCES `kompozicija` (`KompozicijaID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partitura`
--

LOCK TABLES `partitura` WRITE;
/*!40000 ALTER TABLE `partitura` DISABLE KEYS */;
INSERT INTO `partitura` VALUES (17,'files\\bwv827.pdf','pdf',0.1,'eur',21),(28,'files\\bwv532.pdf','pdf',0.1,'eur',43),(29,'files\\notturno.pdf','pdf',0.2,'eur',45);
/*!40000 ALTER TABLE `partitura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zvucni_zapis`
--

DROP TABLE IF EXISTS `zvucni_zapis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zvucni_zapis` (
  `ZvucniZapisID` int(11) NOT NULL AUTO_INCREMENT,
  `Duzina` int(11) DEFAULT NULL,
  `PutDoFajla` varchar(200) DEFAULT NULL,
  `Format` varchar(45) DEFAULT 'wav',
  `Cena` double DEFAULT '0',
  `Valuta` varchar(45) DEFAULT NULL,
  `KompozicijaID` int(11) DEFAULT NULL,
  `IzvodjacID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ZvucniZapisID`),
  KEY `komp_idx` (`KompozicijaID`),
  KEY `izvo_idx` (`IzvodjacID`),
  CONSTRAINT `izvo` FOREIGN KEY (`IzvodjacID`) REFERENCES `izvodjac` (`IzvodjacID`),
  CONSTRAINT `komp` FOREIGN KEY (`KompozicijaID`) REFERENCES `kompozicija` (`KompozicijaID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zvucni_zapis`
--

LOCK TABLES `zvucni_zapis` WRITE;
/*!40000 ALTER TABLE `zvucni_zapis` DISABLE KEYS */;
INSERT INTO `zvucni_zapis` VALUES (5,98,'files\\luisi_bach_partita_a-minor_bwv-827.wav','wav',1.5,'EUR',21,10),(29,155,'files\\latry_bach_fugue_in_d_major_bwv-532.wav','wav',2,'eur',43,15),(30,138,'files\\luisi_chopin_scherzo_2_31.wav','wav',2.5,'eur',44,10),(31,131,'files\\ensemble-la-tempesta_porpora_iii-notturno_iii-lezione.wav','wav',2,'eur',45,16);
/*!40000 ALTER TABLE `zvucni_zapis` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-17 18:08:15
