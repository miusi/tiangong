-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Database: template
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
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `tiangong_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiangong_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deleted` int(11) DEFAULT '0',
  `createBy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateBy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='部门';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiangong_dept`
--

LOCK TABLES `tiangong_dept` WRITE;
/*!40000 ALTER TABLE `tiangong_dept` DISABLE KEYS */;
INSERT INTO `tiangong_dept` VALUES (1,0,'超级管理员','2019-11-21 13:49:41','超级管理员','2019-11-21 13:49:41','电气事业部',0),(2,1,'超级管理员','2019-11-21 13:49:52','超级管理员','2019-11-21 13:50:34','软件开发',1);
/*!40000 ALTER TABLE `tiangong_dept` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `tiangong_menu`;
CREATE TABLE `tiangong_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
   `deleted` int(11) DEFAULT '0',
  `createBy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateBy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `orderNum` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `visible` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单';

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `tiangong_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiangong_permission` (
  `id` int(11) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiangong_permission`
--

LOCK TABLES `tiangong_permission` WRITE;
/*!40000 ALTER TABLE `tiangong_permission` DISABLE KEYS */;
INSERT INTO `tiangong_permission` VALUES (1,'1','系统管理',0),(2,'2','DEMO管理',0),(100,'user:query','用户管理-查看',1),(101,'user:edit','用户管理-增删改',1),(110,'role:query','角色管理-查看',1),(111,'role:edit','角色管理-增删改',1),(120,'dept:query','部门管理-查看',1),(121,'dept:edit','部门管理-增删改',1),(130,'device:query','设备管理-查看',2),(131,'device:edit','设备管理-增删改',2);
/*!40000 ALTER TABLE `tiangong_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiangong_role`
--

DROP TABLE IF EXISTS `tiangong_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiangong_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createBy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateBy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `note` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiangong_role`
--

LOCK TABLES `tiangong_role` WRITE;
/*!40000 ALTER TABLE `tiangong_role` DISABLE KEYS */;
INSERT INTO `tiangong_role` VALUES (1,'超级管理员','2019-11-21 12:15:14','超级管理员','2019-11-21 14:08:58','管理员','123'),(2,'超级管理员','2019-11-21 16:17:49','超级管理员','2019-11-21 16:47:29','设备管理','');
/*!40000 ALTER TABLE `tiangong_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiangong_role_permission`
--

DROP TABLE IF EXISTS `tiangong_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiangong_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `permissionId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色权限关联';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiangong_role_permission`
--

LOCK TABLES `tiangong_role_permission` WRITE;
/*!40000 ALTER TABLE `tiangong_role_permission` DISABLE KEYS */;
INSERT INTO `tiangong_role_permission` VALUES (1,1,100),(2,1,120),(3,1,121),(4,1,101),(5,1,110),(6,1,111),(7,1,131),(8,1,130),(9,1,100),(10,1,120),(11,1,121),(12,1,101),(13,1,110),(14,1,111),(15,1,131),(16,1,130),(17,2,130),(18,2,131),(19,2,130),(20,2,131),(21,2,100);
/*!40000 ALTER TABLE `tiangong_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiangong_user`
--

DROP TABLE IF EXISTS `tiangong_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiangong_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1删除，0正常',
  `createBy` varchar(20) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateBy` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  `updateDate` datetime DEFAULT NULL COMMENT '最后修改时间',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `phone` varchar(30) NOT NULL COMMENT '电话',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `name` varchar(30) NOT NULL COMMENT '姓名',
  `status` varchar(30) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiangong_user`
--

LOCK TABLES `tiangong_user` WRITE;
/*!40000 ALTER TABLE `tiangong_user` DISABLE KEYS */;
INSERT INTO `tiangong_user` VALUES (1,_binary '\0','系统','2019-11-15 15:26:40','超级管理员','2019-11-21 15:54:59','admin','10000000000','$2a$10$gsNlpjlKbOT30QNZ/23knO/ymgYZtWc2zpCdzWoqTCE9g3Ys9zVN6','超级管理员','enabled'),(2,_binary '\0','超级管理员','2019-11-21 16:16:27','超级管理员','2019-11-21 16:18:08','hgdyecdokzom','18918296251','$2a$10$yrYXaMcryY4be9U1d8UnR.lHWYQqGqiqBd5Vo/gQMbfVM0ur9PkSO','测试','enabled');
/*!40000 ALTER TABLE `tiangong_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiangong_user_role`
--

DROP TABLE IF EXISTS `tiangong_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiangong_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户角色关联';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiangong_user_role`
--

LOCK TABLES `tiangong_user_role` WRITE;
/*!40000 ALTER TABLE `tiangong_user_role` DISABLE KEYS */;
INSERT INTO `tiangong_user_role` VALUES (3,2,2);
/*!40000 ALTER TABLE `tiangong_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-22 16:57:18
