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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (5,'James Patterson'),(6,'Lisa See'),(7,'Chris Bohjalian'),(8,'Ben Shapiro'),(10,'Stephen King'),(11,'Liane Moriarty'),(13,'Steven Pinker'),(14,'Simone St. James');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billing_info`
--

DROP TABLE IF EXISTS `billing_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `billing_info` (
  `id_billing_info` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `company_name` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `postcode` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_billing_info`),
  UNIQUE KEY `id_user_UNIQUE` (`id_user`),
  CONSTRAINT `fk_billing_info_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billing_info`
--

LOCK TABLES `billing_info` WRITE;
/*!40000 ALTER TABLE `billing_info` DISABLE KEYS */;
INSERT INTO `billing_info` VALUES (7,'Senan','Kazimov','Company LTD','Azerbaijan','Ozbekistan str 1','AZ1020','055-555-55-55','senan0144@gmail.com',22);
/*!40000 ALTER TABLE `billing_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `blog` (
  `id_blog` int(11) NOT NULL AUTO_INCREMENT,
  `title` text,
  `desc` text,
  `share_date` datetime DEFAULT NULL,
  `image_path` varchar(100) DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_blog`),
  KEY `fk_blog_user_idx` (`id_user`),
  CONSTRAINT `fk_blog_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,'Putting Literary Miami on the Map','Thirty-seven years, an international book fair and eight additional locations later, Kaplan is celebrated as the man who turned Miami into a book town, and one of the foremost literary centers in the world, starting at a time when nobody took it seriously.','2019-02-04 00:00:00','default.jpg',22),(2,'Looking Back at ‘A Heartbreaking Work of Staggering Genius’','“A Heartbreaking Work of Staggering Genius” may start off sounding like one of those coy, solipsistic exercises that put everything in little ironic quote marks, but it quickly becomes a virtuosic piece of writing, a big, daring, manic-depressive stew of a book that noisily announces the debut of a talented — yes, staggeringly talented new writer.','2019-02-05 00:00:00','default.jpg',22);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_review`
--

DROP TABLE IF EXISTS `blog_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `blog_review` (
  `id_blog_review` int(11) NOT NULL AUTO_INCREMENT,
  `desc` text,
  `share_date` datetime DEFAULT NULL,
  `id_blog` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_blog_review`),
  KEY `fk_blog_review_blog_idx` (`id_blog`),
  KEY `fk_blog_review_user_idx` (`id_user`),
  CONSTRAINT `fk_blog_review_blog` FOREIGN KEY (`id_blog`) REFERENCES `blog` (`id_blog`),
  CONSTRAINT `fk_blog_review_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_review`
--

LOCK TABLES `blog_review` WRITE;
/*!40000 ALTER TABLE `blog_review` DISABLE KEYS */;
INSERT INTO `blog_review` VALUES (1,'Good blog!','2019-02-04 00:00:00',1,22),(2,'Thanks!','2019-02-04 00:00:00',1,22),(4,'Great!','2019-03-29 21:55:20',1,22),(5,'My favorite blog!','2019-03-29 21:56:09',2,22),(6,'Thanks a lot!','2019-03-29 22:00:26',2,22),(9,'I have got a problem!','2019-03-31 22:06:19',1,22);
/*!40000 ALTER TABLE `blog_review` ENABLE KEYS */;
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
  `first_image_path` varchar(45) DEFAULT NULL,
  `second_image_path` varchar(45) DEFAULT NULL,
  `language` varchar(45) DEFAULT NULL,
  `write_date` date DEFAULT NULL,
  `id_author` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_book`),
  KEY `fk_book_author_idx` (`id_author`),
  CONSTRAINT `fk_book_author` FOREIGN KEY (`id_author`) REFERENCES `author` (`id_author`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (6,'The Cornwalls Are Gone','Intelligence officer Amy Cornwall knows too much, way too much. ','the-cornwalls-are-gone.jpg','demo-back-side.jpg','eng','2019-04-01',5),(7,'The Island of Sea Women','Mi-ja and Young-sook, two girls living on the Korean island of Jeju, are best friends that come from very different backgrounds. When they are old enough, they begin working in the sea with their village’s all-female diving collective, led by Young-sook’s mother. As the girls take up their positions as baby divers, they know they are beginning a life of excitement and responsibility but also danger.','the-island-of-sea-women.jpg','demo-back-side.jpg','eng','2019-04-02',6),(8,'The Flight Attendant','Cassandra Bowden is no stranger to hungover mornings. She\'s a binge drinker, her job with the airline making it easy to find adventure, and the occasional blackouts seem to be inevitable. She lives with them, and the accompanying self-loathing. When she awakes in a Dubai hotel room, she tries to piece the previous night back together, counting the minutes until she has to catch her crew shuttle to the airport. She quietly slides out of bed, careful not to aggravate her already pounding head, and looks at the man she spent the night with. She sees his dark hair. His utter stillness. And blood, a slick, still wet pool on the crisp white sheets. Afraid to call the police - she\'s a single woman alone in a hotel room far from home - Cassie begins to lie. She lies as she joins the other flight attendants and pilots in the van. She lies on the way to Paris as she works the first class cabin. She lies to the FBI agents in New York who meet her at the gate. Soon it\'s too late to come clean-or face the truth about what really happened back in Dubai. Could she have killed him? If not, who did? ','the-flight-attendant.jpg','demo-back-side.jpg','eng','2019-03-01',7),(9,'The Right Side of History','America has a God-shaped hole in its heart, argues New York Times bestselling author Ben Shapiro, and we shouldn\'t fill it with politics and hate.','the-right-side-of-history.jpg','demo-back-side.jpg','eng','2019-02-01',8),(11,'Pet Sematary','Dr. Louis Creed and his wife Rachel chose rural Maine to settle his family and bring up their children. It was a better place than smog-covered Chicago — or so he thought. But that was before he became acquainted with the old pet burial ground located in the backwoods of the quiet community of Ludlow.','pet-sematary.jpg','demo-back-side.jpg','eng','2018-06-23',10),(12,'What Alice Forgot','Alice Love is twenty-nine, crazy about her husband, and pregnant with her first child. So imagine Alice’s surprise when she comes to on the floor of a gym (a gym! She HATES the gym) and is whisked off to the hospital where she discovers the honeymoon is truly over—she’s getting divorced, she has three kids, and she’s actually 39 years old. Alice must reconstruct the events of a lost decade, and find out whether it’s possible to reconstruct her life at the same time. She has to figure out why her sister hardly talks to her, and how is it that she’s become one of those super skinny moms with really expensive clothes. Ultimately, Alice must discover whether forgetting is a blessing or a curse, and whether it’s possible to start over...','what-alice-forgot.jpg','demo-back-side.jpg','eng','2017-03-06',11),(13,'The First Lady','In James Patterson\'s new stand-alone thriller, one secret can bring down a government when the President\'s affair to remember becomes a nightmare he wishes he could forget. ','the-first-lady.jpg','demo-back-side.jpg','eng','2016-12-11',5),(14,'Enlightenment Now','Is the world really falling apart? Is the ideal of progress obsolete? In this elegant assessment of the human condition in the third millennium, cognitive scientist and public intellectual Steven Pinker urges us to step back from the gory headlines and prophecies of doom, which play to our psychological biases. Instead, follow the data: In seventy-five jaw-dropping graphs, Pinker shows that life, health, prosperity, safety, peace, knowledge, and happiness are on the rise, not just in the West, but worldwide. This progress is not the result of some cosmic force. It is a gift of the Enlightenment: the conviction that reason and science can enhance human flourishing.','enlightenment-now.jpg','demo-back-side.jpg','eng','2016-07-09',13),(15,'The Broken Girls','Vermont, 1950. There\'s a place for the girls whom no one wants—the troublemakers, the illegitimate, the too smart for their own good. It\'s called Idlewild Hall, and local legend says the boarding school is haunted. Four roommates bond over their whispered fears, their budding friendship blossoming—until one of them mysteriously disappears . . .','the-broken-girls.jpg','demo-back-side.jpg','eng','2019-01-08',14);
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
  CONSTRAINT `fk_book_category_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`) ON DELETE CASCADE,
  CONSTRAINT `fk_book_category_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_category`
--

LOCK TABLES `book_category` WRITE;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
INSERT INTO `book_category` VALUES (9,6,1),(10,7,2),(11,8,3),(12,9,4),(14,11,6),(15,12,7),(16,13,8),(17,14,9),(18,15,10),(19,6,13),(20,7,12),(21,12,11),(23,13,15);
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
  UNIQUE KEY `unique_index` (`id_user`,`id_book`),
  KEY `fk_basket_user_idx` (`id_user`),
  KEY `fk_basket_book_idx` (`id_book`),
  CONSTRAINT `fk_cart_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`) ON DELETE CASCADE,
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
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
  `desc` text,
  `write_date` datetime DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_book` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_review`),
  KEY `fk_review_user_idx` (`id_user`),
  KEY `fk_review_book_idx` (`id_book`),
  CONSTRAINT `fk_review_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`) ON DELETE CASCADE,
  CONSTRAINT `fk_review_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (61,'Thanks!','2018-06-02 17:26:39',4,22,8),(62,'My favorite book.','2018-11-24 17:27:26',5,22,8),(63,'I have got a problem!','2018-04-02 17:27:57',2,22,9),(64,'Thanks for fast shipping!','2019-04-02 17:29:42',5,22,15),(65,'I don\'t like this book.','2019-01-11 17:30:28',3,22,6),(66,'Thanks!','2019-03-02 17:32:24',4,22,7),(67,'Prices aren\'t suitable!','2019-02-04 17:32:24',1,22,11);
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
  `discount` int(11) NOT NULL DEFAULT '0',
  `age_range` int(11) DEFAULT NULL,
  `upsell` int(11) DEFAULT '0',
  `last_added_date` datetime DEFAULT NULL,
  `id_book` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_stock`),
  KEY `fk_stock_book_idx` (`id_book`),
  CONSTRAINT `fk_stock_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (6,12,12.75,10,12,496,'2018-04-03 00:00:00',6),(7,24,16.95,10,8,320,'2018-02-17 00:00:00',7),(8,0,18.65,20,8,240,'2019-04-02 00:00:00',8),(9,12,19.95,20,12,66,'2019-03-01 00:00:00',9),(11,28,20.75,20,8,420,'2017-11-23 00:00:00',11),(12,18,12.15,10,6,343,'2017-10-12 00:00:00',12),(13,0,14.75,10,6,76,'2019-02-18 00:00:00',13),(14,27,22.85,15,12,21,'2018-09-14 00:00:00',14),(15,34,25.95,20,12,69,'2019-01-12 00:00:00',15);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriber`
--

DROP TABLE IF EXISTS `subscriber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `subscriber` (
  `id_subscriber` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id_subscriber`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriber`
--

LOCK TABLES `subscriber` WRITE;
/*!40000 ALTER TABLE `subscriber` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscriber` ENABLE KEYS */;
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
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_user_role_idx` (`id_role`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (22,'Senan','Kazimov','senan0144','$2a$10$XMlS3Q4Y6A78RswoQ06OrezBbfXciv4/8ByPGL0Zumz3SFQPnEcXa','senan0144@gmail.com',1,'980eaf6b-a6f5-4c43-8a30-bace75232bd3',2);
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
  CONSTRAINT `fk_wishlist_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`) ON DELETE CASCADE,
  CONSTRAINT `fk_wishlist_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
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

-- Dump completed on 2019-04-06 14:14:48
