-- MariaDB dump 10.19  Distrib 10.6.4-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: epub_reading_service
-- ------------------------------------------------------
-- Server version	10.6.4-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account_info`
--

DROP TABLE IF EXISTS `account_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_info` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `account_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `account_cert` text DEFAULT NULL COMMENT '用户验证凭据',
  `account_security_key` varchar(64) DEFAULT NULL COMMENT '用户安全密钥',
  `account_type` int(11) DEFAULT 0 COMMENT '用户类型, 0-阅读者; 1-管理员',
  `is_locked` int(11) DEFAULT 0 COMMENT '锁定状态标识, 0-正常; 1-锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账号信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_info`
--

LOCK TABLES `account_info` WRITE;
/*!40000 ALTER TABLE `account_info` DISABLE KEYS */;
INSERT INTO `account_info` (`id`, `account_name`, `account_cert`, `account_security_key`, `account_type`, `is_locked`) VALUES ('1000','admin',NULL,NULL,1,0),('1462036313120247809','shuheng','1bb26a9e54a54de13eb7d81352d0ca6a20fa3c71c6a4717a2ccecd9506e8e03f','68E7286EF4C04E3985F24870E1F493E8',1,0),('1462055930358214658','heng','0c5b5f595ace647a94b3122065883fe215f15f7c9853f53a7c912feef833fd0f','F16572A922BC4715AC1DBAC145F412B7',0,0);
/*!40000 ALTER TABLE `account_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_global_config_info`
--

DROP TABLE IF EXISTS `app_global_config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_global_config_info` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `account_id` varchar(64) NOT NULL COMMENT '用户账号ID, account_id -> account_info.id',
  `app_theme` int(11) DEFAULT 0 COMMENT '应用界面主题',
  `app_info_sync_mode` int(11) DEFAULT 0 COMMENT '应用信息同步模式',
  `reader_theme` int(11) DEFAULT 0 COMMENT '阅读器主题',
  `reader_font_size` int(11) DEFAULT NULL COMMENT '阅读器字号',
  `reader_font_family` varchar(16) DEFAULT NULL COMMENT '阅读器字体',
  `reader_text_space` int(11) DEFAULT NULL COMMENT '阅读器页面文本行间距',
  `is_deleted` int(11) DEFAULT 0 COMMENT '删除标识, 0-正常; 1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用全局配置信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_global_config_info`
--

LOCK TABLES `app_global_config_info` WRITE;
/*!40000 ALTER TABLE `app_global_config_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `app_global_config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_meta_info`
--

DROP TABLE IF EXISTS `book_meta_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_meta_info` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `book_id` varchar(64) NOT NULL COMMENT '书目ID, book_id -> book_resource_info.id',
  `book_title` varchar(32) DEFAULT NULL COMMENT '书目标题',
  `book_authors` varchar(256) DEFAULT NULL COMMENT '书目作者列表, 使用逗号分隔',
  `book_description` text DEFAULT NULL COMMENT '书目简介',
  `is_deleted` int(11) DEFAULT 0 COMMENT '删除标识, 0-正常; 1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='书目元数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_meta_info`
--

LOCK TABLES `book_meta_info` WRITE;
/*!40000 ALTER TABLE `book_meta_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_meta_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_resource_info`
--

DROP TABLE IF EXISTS `book_resource_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_resource_info` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `account_id` varchar(64) NOT NULL COMMENT '用户(拥有者)ID, account_id -> account_info.id',
  `book_origin_file_name` varchar(128) DEFAULT NULL COMMENT '书目源文件名',
  `book_resource_url` text DEFAULT NULL COMMENT '书目资源目录路径',
  `book_cover_url` text DEFAULT NULL COMMENT '书目封面路径',
  `book_resource_size` int(11) DEFAULT NULL COMMENT '书目资源容量',
  `book_pushed_time` datetime DEFAULT NULL COMMENT '书目导入时间',
  `is_deleted` int(11) DEFAULT 0 COMMENT '删除标识, 0-正常; 1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='书目资源信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_resource_info`
--

LOCK TABLES `book_resource_info` WRITE;
/*!40000 ALTER TABLE `book_resource_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_resource_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookmark_info`
--

DROP TABLE IF EXISTS `bookmark_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookmark_info` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `book_id` varchar(64) NOT NULL COMMENT '关联书目ID, book_id -> book_resource_info.id',
  `marked_chapter_name` varchar(32) DEFAULT NULL COMMENT '记录章节名称',
  `marked_location` text DEFAULT NULL COMMENT '记录文本位置定位符',
  `marked_time` datetime DEFAULT NULL COMMENT '书签创建时间',
  `is_deleted` int(11) DEFAULT 0 COMMENT '删除标识, 0-正常; 1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='书签信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmark_info`
--

LOCK TABLES `bookmark_info` WRITE;
/*!40000 ALTER TABLE `bookmark_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookmark_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reading_progress_info`
--

DROP TABLE IF EXISTS `reading_progress_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reading_progress_info` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `book_id` varchar(64) NOT NULL COMMENT '关联书目ID, book_id -> book_resource_info.id',
  `cur_read_location` text DEFAULT NULL COMMENT '当前阅读文本定位符',
  `cur_read_percentage` decimal(5,2) DEFAULT NULL COMMENT '当前阅读进度百分比',
  `cur_chapter_name` varchar(32) DEFAULT NULL COMMENT '当前阅读章节名称',
  `latest_read_time` datetime DEFAULT NULL COMMENT '最近阅读时间',
  `is_deleted` int(11) DEFAULT 0 COMMENT '删除标识, 0-正常; 1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='阅读进度信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reading_progress_info`
--

LOCK TABLES `reading_progress_info` WRITE;
/*!40000 ALTER TABLE `reading_progress_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `reading_progress_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-21 22:55:07
