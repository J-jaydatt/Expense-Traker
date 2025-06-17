-- MySQL dump 10.13  Distrib 8.1.0, for macos13 (x86_64)
--
-- Host: localhost    Database: ExpenseTraker
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `budget`
--

DROP TABLE IF EXISTS `budget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `budget` (
  `id` int NOT NULL,
  `end_date` date DEFAULT NULL,
  `remaining` bigint DEFAULT NULL,
  `spent` bigint DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `total_budget` bigint DEFAULT NULL,
  `user_id` int NOT NULL,
  `days` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budget`
--

LOCK TABLES `budget` WRITE;
/*!40000 ALTER TABLE `budget` DISABLE KEYS */;
INSERT INTO `budget` VALUES (752,'2025-07-14',5098,14902,'2025-06-14',20000,1,30,'INACTIVATE'),(802,'2025-07-17',20000,0,'2025-06-17',20000,2,30,'ACTIVE'),(803,'2025-07-17',20000,0,'2025-06-17',20000,52,30,'ACTIVE'),(804,'2025-07-17',20000,0,'2025-06-17',20000,1,30,'ACTIVE'),(852,'2025-07-17',20000,0,'2025-06-17',20000,102,30,'ACTIVE'),(902,'2025-07-17',19000,1000,'2025-06-17',20000,152,30,'ACTIVE'),(952,'2025-07-17',14750,5250,'2025-06-17',20000,202,30,'INACTIVATE'),(953,'2025-07-17',2350,7650,'2025-06-17',10000,202,30,'ACTIVE');
/*!40000 ALTER TABLE `budget` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `budget_seq`
--

DROP TABLE IF EXISTS `budget_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `budget_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budget_seq`
--

LOCK TABLES `budget_seq` WRITE;
/*!40000 ALTER TABLE `budget_seq` DISABLE KEYS */;
INSERT INTO `budget_seq` VALUES (1051);
/*!40000 ALTER TABLE `budget_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL,
  `date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` bigint DEFAULT NULL,
  `time` time(6) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `budget_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK979liw4xk18ncpl87u4tygx2u` (`user_id`),
  KEY `FK96kn0aur6pl25dci9kwevjqyj` (`budget_id`),
  CONSTRAINT `FK96kn0aur6pl25dci9kwevjqyj` FOREIGN KEY (`budget_id`) REFERENCES `budget` (`id`),
  CONSTRAINT `FK979liw4xk18ncpl87u4tygx2u` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (202,'2025-06-15','pizza',100,'00:17:15.000000','Food',1,752),(252,'2025-06-15','pizza',100,'00:26:54.000000','Food',1,752),(302,'2025-06-15','burger',100,'00:31:25.000000','Food',1,752),(352,'2025-06-15','samosa',1000,'10:08:35.068000','Food',1,752),(402,'2025-06-15','samosa',1000,'10:15:37.517000','Food',1,752),(452,'2025-06-15','samosa',200,'10:18:24.576000','Food',1,752),(502,'2025-06-15','cold drink',200,'10:23:58.598000','Food',1,752),(552,'2025-06-15','cold drink',1000,'10:28:16.708000','Food',1,752),(602,'2025-06-15','samosa',1000,'10:37:59.034000','Food',1,752),(652,'2025-06-15','momo',100,'10:39:32.168000','Food',1,752),(702,'2025-06-15','asdas',100,'10:43:10.684000','Food',1,752),(752,'2025-06-15','sdfs',100,'10:45:49.796000','Food',1,752),(802,'2025-06-15','vadapav',100,'13:17:40.543000','Food',1,752),(852,'2025-06-15','dfsd',2000,'13:50:01.440000','Food',1,752),(902,'2025-06-15','samosa',100,'13:59:37.778000','Food',1,752),(952,'2025-06-15','dad',1222,'14:04:26.058000','Food',1,752),(1002,'2025-06-15','panner cheeze pizza',180,'22:25:41.221000','Food',1,752),(1052,'2025-06-15','Uber',100,'22:43:10.026000','Travel',1,752),(1102,'2025-06-16','dress',100,'13:27:43.491000','Wishes',1,752),(1152,'2025-06-16','litght bill',1100,'13:35:12.537000','Needs',1,752),(1202,'2025-06-16','demo',1000,'13:55:54.468000','',1,752),(1252,'2025-06-16','Friend Loan',1000,'21:28:11.346000','Others,Others',1,752),(1302,'2025-06-16','Loan',1000,'21:31:19.379000','Others',1,752),(1352,'2025-06-17','burger',1000,'23:17:05.721000','Food',152,902),(1402,'2025-06-17','Pizza',500,'23:22:02.642000','Food',202,952),(1403,'2025-06-17','Uber',250,'23:22:18.354000','Travel',202,952),(1404,'2025-06-17','Shirt',1000,'23:22:35.040000','Wishes',202,952),(1405,'2025-06-17','Light bill',1500,'23:22:53.995000','Needs',202,952),(1406,'2025-06-17','Party',2000,'23:23:13.395000','Others',202,952),(1407,'2025-06-17','Pizza',500,'23:28:43.111000','Food',202,953),(1408,'2025-06-17','Uber',250,'23:48:07.576000','Travel',202,953),(1409,'2025-06-17','Light bill',1400,'23:48:17.673000','Wishes',202,953),(1410,'2025-06-17','Shirt',1000,'23:48:56.073000','Wishes',202,953),(1411,'2025-06-17','Grocery',3000,'23:49:10.285000','Needs',202,953),(1412,'2025-06-17','Party',1500,'23:49:27.114000','Others',202,953);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_seq`
--

DROP TABLE IF EXISTS `product_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_seq`
--

LOCK TABLES `product_seq` WRITE;
/*!40000 ALTER TABLE `product_seq` DISABLE KEYS */;
INSERT INTO `product_seq` VALUES (1501);
/*!40000 ALTER TABLE `product_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `mob` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'9730502948','Ajay','1234'),(2,'8600550202','Demo ','1234'),(52,'9730502947','demo2','1234'),(102,'9730502921','demo3','1234'),(152,'9730502922','demo4','1234'),(202,'1234567890','User','1234');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_seq`
--

DROP TABLE IF EXISTS `user_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_seq`
--

LOCK TABLES `user_seq` WRITE;
/*!40000 ALTER TABLE `user_seq` DISABLE KEYS */;
INSERT INTO `user_seq` VALUES (301);
/*!40000 ALTER TABLE `user_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-18  0:53:46
