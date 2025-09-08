-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: link
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `t_group`
--

DROP TABLE IF EXISTS `t_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_group` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `gid` varchar(32) DEFAULT NULL COMMENT '分组标识',
  `name` varchar(64) DEFAULT NULL COMMENT '分组名称',
  `username` varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
  `sort_order` int DEFAULT NULL COMMENT '分组排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_group`
--

LOCK TABLES `t_group` WRITE;
/*!40000 ALTER TABLE `t_group` DISABLE KEYS */;
INSERT INTO `t_group` VALUES (1,'jdixow','短链接示例','hxw',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_0`
--

DROP TABLE IF EXISTS `t_user_0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_0` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_0`
--

LOCK TABLES `t_user_0` WRITE;
/*!40000 ALTER TABLE `t_user_0` DISABLE KEYS */;
INSERT INTO `t_user_0` VALUES (1,'hanxuewei8','123456','韩雪伟','bpUmWEmIeqC2/OEjfYPdDQ==','sFmBpo23E3FeQzhnFnV8ohzFeSv0bUlc4sznntxk3/0=',NULL,'2025-09-06 11:24:38','2025-09-06 11:24:38',0);
/*!40000 ALTER TABLE `t_user_0` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_1`
--

DROP TABLE IF EXISTS `t_user_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_1` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_1`
--

LOCK TABLES `t_user_1` WRITE;
/*!40000 ALTER TABLE `t_user_1` DISABLE KEYS */;
INSERT INTO `t_user_1` VALUES (1,'hanxuewei7','123456891','韩雪伟','gh2aieTcgoX3Scxu720H2g==','sFmBpo23E3FeQzhnFnV8ohzFeSv0bUlc4sznntxk3/0=',NULL,'2025-09-05 11:02:57','2025-09-05 11:07:39',0);
/*!40000 ALTER TABLE `t_user_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_10`
--

DROP TABLE IF EXISTS `t_user_10`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_10` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_10`
--

LOCK TABLES `t_user_10` WRITE;
/*!40000 ALTER TABLE `t_user_10` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_10` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_11`
--

DROP TABLE IF EXISTS `t_user_11`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_11` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_11`
--

LOCK TABLES `t_user_11` WRITE;
/*!40000 ALTER TABLE `t_user_11` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_11` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_12`
--

DROP TABLE IF EXISTS `t_user_12`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_12` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_12`
--

LOCK TABLES `t_user_12` WRITE;
/*!40000 ALTER TABLE `t_user_12` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_12` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_13`
--

DROP TABLE IF EXISTS `t_user_13`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_13` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_13`
--

LOCK TABLES `t_user_13` WRITE;
/*!40000 ALTER TABLE `t_user_13` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_13` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_14`
--

DROP TABLE IF EXISTS `t_user_14`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_14` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_14`
--

LOCK TABLES `t_user_14` WRITE;
/*!40000 ALTER TABLE `t_user_14` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_14` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_15`
--

DROP TABLE IF EXISTS `t_user_15`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_15` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_15`
--

LOCK TABLES `t_user_15` WRITE;
/*!40000 ALTER TABLE `t_user_15` DISABLE KEYS */;
INSERT INTO `t_user_15` VALUES (1,'hanxuewei9','123456','韩雪伟','bpUmWEmIeqC2/OEjfYPdDQ==','sFmBpo23E3FeQzhnFnV8ohzFeSv0bUlc4sznntxk3/0=',NULL,'2025-09-06 11:34:23','2025-09-06 11:34:23',0);
/*!40000 ALTER TABLE `t_user_15` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_2`
--

DROP TABLE IF EXISTS `t_user_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_2` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_2`
--

LOCK TABLES `t_user_2` WRITE;
/*!40000 ALTER TABLE `t_user_2` DISABLE KEYS */;
INSERT INTO `t_user_2` VALUES (1,'hanxuewei6','123456','韩雪伟','bpUmWEmIeqC2/OEjfYPdDQ==','sFmBpo23E3FeQzhnFnV8ohzFeSv0bUlc4sznntxk3/0=',NULL,'2025-09-04 10:03:39','2025-09-04 10:03:39',0);
/*!40000 ALTER TABLE `t_user_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_3`
--

DROP TABLE IF EXISTS `t_user_3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_3` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_3`
--

LOCK TABLES `t_user_3` WRITE;
/*!40000 ALTER TABLE `t_user_3` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_4`
--

DROP TABLE IF EXISTS `t_user_4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_4` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_4`
--

LOCK TABLES `t_user_4` WRITE;
/*!40000 ALTER TABLE `t_user_4` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_5`
--

DROP TABLE IF EXISTS `t_user_5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_5` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_5`
--

LOCK TABLES `t_user_5` WRITE;
/*!40000 ALTER TABLE `t_user_5` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_5` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_6`
--

DROP TABLE IF EXISTS `t_user_6`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_6` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_6`
--

LOCK TABLES `t_user_6` WRITE;
/*!40000 ALTER TABLE `t_user_6` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_6` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_7`
--

DROP TABLE IF EXISTS `t_user_7`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_7` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_7`
--

LOCK TABLES `t_user_7` WRITE;
/*!40000 ALTER TABLE `t_user_7` DISABLE KEYS */;
INSERT INTO `t_user_7` VALUES (1,'hanxuewei1','123456','韩雪伟','18962947619','3025731614@qq.com',NULL,'2025-09-03 16:39:38','2025-09-03 16:39:38',0),(2,'hanxuewei10','123456','韩雪伟','bpUmWEmIeqC2/OEjfYPdDQ==','sFmBpo23E3FeQzhnFnV8ohzFeSv0bUlc4sznntxk3/0=',NULL,'2025-09-06 11:34:37','2025-09-06 11:34:37',0);
/*!40000 ALTER TABLE `t_user_7` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_8`
--

DROP TABLE IF EXISTS `t_user_8`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_8` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_8`
--

LOCK TABLES `t_user_8` WRITE;
/*!40000 ALTER TABLE `t_user_8` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_8` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_9`
--

DROP TABLE IF EXISTS `t_user_9`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_9` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0：未删除 1：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_9`
--

LOCK TABLES `t_user_9` WRITE;
/*!40000 ALTER TABLE `t_user_9` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_9` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-08 10:35:54
