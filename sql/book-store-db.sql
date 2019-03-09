CREATE DATABASE  IF NOT EXISTS `book_store_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `book_store_db`;
-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: book_store_db
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `author` (
  `id_author` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_author`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Robin Parrish'),(2,'Bowen Greenwood'),(3,'Stephen King'),(4,'Kim Roundtree');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `book` (
  `id_book` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `desc` text NOT NULL,
  `image_path` varchar(45) DEFAULT NULL,
  `language` varchar(45) DEFAULT NULL,
  `write_date` date DEFAULT NULL,
  `id_author` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_book`),
  KEY `fk_book_author_idx` (`id_author`),
  CONSTRAINT `fk_book_author` FOREIGN KEY (`id_author`) REFERENCES `author` (`id_author`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Black House','In this sequel to The Talisman, Jack Sawyer is now in his late thirties and has taken early retirement from the LAPD, retreating to a small town in Wisconsin. He has no memory of his adventures as a twelve-year-old boy, when he traveled into a parallel universe in search of the talisman that would save his mother\'s life. A series of murders involving young children force him out of retirement. There is more to these cases than murder, though, and Jack must retrieve his childhood memories to rescue the latest victim, who is coveted by the killer\'s evil overlord, a powerful force from End-World, in Roland the gunslinger\'s universe','default.jpg','eng','2001-09-09',3),(2,'Christine','A love triangle involving 17-year-old misfit Arnie Cunningham, his new girlfriend and a haunted 1958 Plymouth Fury. Dubbed Christine by her previous owner, Arnie\'s first car is jealous, possessive and deadly','default.jpg','eng','1983-09-04',3),(3,'Death of Secrets','Kathy Kelver nearly trips over a murder victim on her way back to her dorm room late one night. In his last words, the dying man gives her stolen data about a secret project that could blow the lid off a shocking conspiracy. From the halls of Congress to the National Security Agency and beyond, Kathy must run for her life from shadowy forces who want her dead, while trying to build a relationship and hang on to her faith. The secret she\'s carrying could end the right to privacy forever, if she doesn\'t survive to warn the world','default.jpg','eng','1995-04-20',2),(4,'Nightmare','When Maia Peters visits during her senior year of college, she\'s not expecting to be impressed. Maia grew up as the only child of a pair of world-renowned \"ghost hunters,\" so the paranormal is nothing new. In fact, the ride feels pretty boring until the very end. There, a face appears from the mist. The face of Jordin Cole, a girl who disappeared from campus a year ago','default.jpg','eng','2004-11-30',1),(5,'Fear to Freedom','Fear  to  Freedom  is  a  collaboration  of  10 authors  who  share  their  fears  and  triumphs.  It  is  your  guide  to  a  life  of  faith,  favor  and  fulfillment','default.jpg','eng','2016-03-02',4);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_category`
--

DROP TABLE IF EXISTS `book_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `book_category` (
  `id_book_category` int(11) NOT NULL AUTO_INCREMENT,
  `id_book` int(11) DEFAULT NULL,
  `id_category` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_book_category`),
  KEY `fk_book_category_book_idx` (`id_book`),
  KEY `fk_book_category_category_idx` (`id_category`),
  CONSTRAINT `fk_book_category_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`),
  CONSTRAINT `fk_book_category_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_category`
--

LOCK TABLES `book_category` WRITE;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
INSERT INTO `book_category` VALUES (1,1,6),(2,2,5),(3,3,7),(4,4,8),(5,4,9),(6,2,9),(7,5,11),(8,5,13);
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cart` (
  `id_cart` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_cart`),
  KEY `fk_basket_user_idx` (`id_user`),
  KEY `fk_basket_book_idx` (`id_book`),
  CONSTRAINT `fk_cart_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`),
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (53,1,5,1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `id_category` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Biography'),(2,'Business'),(3,'Cookbooks'),(4,'Health & Fitness'),(5,'History'),(6,'Mystery'),(7,'Religion & Inspiration'),(8,'Romance'),(9,'Fantasy'),(10,'Sleeveless'),(11,'Science'),(12,'Harry Potter'),(13,'Self-Improvemen'),(14,'Home & Garden'),(15,'Humor books');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `review` (
  `id_review` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) DEFAULT NULL,
  `write_date` datetime DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_book` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_review`),
  KEY `fk_review_user_idx` (`id_user`),
  KEY `fk_review_book_idx` (`id_book`),
  CONSTRAINT `fk_review_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`) ON DELETE CASCADE,
  CONSTRAINT `fk_review_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `role_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER'),(3,'ROLE_UNAUTHENTICATED');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stock` (
  `id_stock` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `age_range` int(11) DEFAULT NULL,
  `last_added_date` datetime DEFAULT NULL,
  `id_book` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_stock`),
  KEY `fk_stock_book_idx` (`id_book`),
  CONSTRAINT `fk_stock_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,17,25.95,12,'2019-02-03 00:00:00',1),(2,0,18.95,18,'2019-01-03 00:00:00',2),(3,12,12.95,3,'2019-01-18 00:00:00',3),(4,35,10.95,6,'2018-11-11 00:00:00',4),(5,22,12.95,12,'2019-02-20 00:00:00',5);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(45) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `token` text,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_user_role_idx` (`id_role`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Senan','Kazimov','senan0144','$2a$10$v.xHljR4LsBOEnd6DGvOHeVNByfNuiyTjG9cYapCM8BeWgBSxa8v6','senan0144@gmail.com',1,'7a855c0f-3471-4ffb-9c02-5c995ebac6c3',2),(5,'Aslan','Ahmedov','akmedof','$2a$10$5A1IJyXwyKyOSz/oAz3JB.bXK3ZyYJdKfKaiOYh5g29J7rSqshcSK','akmedofaslan@gmail.com',1,'bb45bbd9-282c-449d-a219-f94ab5a82b7c',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `wishlist` (
  `id_wishlist` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  PRIMARY KEY (`id_wishlist`),
  KEY `fk_bookshelf_user_idx` (`id_user`),
  KEY `fk_bookshelf_book_idx` (`id_book`),
  CONSTRAINT `fk_wishlist_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`),
  CONSTRAINT `fk_wishlist_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (23,5,3),(24,5,4),(52,1,1);
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-09 10:40:17