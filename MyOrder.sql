/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.40 : Database - myorder
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`myorder` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myorder`;

/*Table structure for table `bill` */

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `order_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `user_id` int(4) NOT NULL COMMENT '用户编号',
  `goods_id` int(4) NOT NULL COMMENT '商品编号',
  `order_price` decimal(20,2) NOT NULL COMMENT '订单金额',
  `order_time` datetime DEFAULT NULL COMMENT '创建时间',
  `order_status` varchar(100) NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='订单信息表';

/*Data for the table `bill` */

insert  into `bill`(`order_id`,`user_id`,`goods_id`,`order_price`,`order_time`,`order_status`) values (4,5,1,'198.00','2020-01-23 18:00:03','已支付'),(5,5,2,'99.00','2020-01-24 14:48:28','已超时'),(6,5,5,'99.00','2020-01-25 14:22:43','已支付'),(7,6,6,'79.00','2020-01-24 16:23:37','已支付'),(8,5,4,'299.00','2020-01-25 18:55:52','已支付'),(9,5,4,'299.00','2020-01-25 18:50:13','已超时'),(10,7,1,'198.00','2020-02-05 11:07:14','已超时'),(11,5,1,'198.00','2020-02-06 12:26:01','已支付'),(12,7,2,'99.00','2020-02-06 13:03:31','已支付'),(13,7,2,'99.00','2020-02-05 13:05:17','已超时'),(14,5,1,'198.00','2020-02-10 14:51:54','已支付'),(15,6,2,'99.00','2020-02-10 15:01:37','已支付'),(16,5,1,'198.00','2020-02-12 11:43:56','已支付'),(17,5,1,'198.00','2020-02-11 11:44:08','已超时'),(18,5,1,'198.00','2020-02-11 11:45:05','已超时'),(19,5,1,'198.00','2020-02-11 11:47:31','已超时'),(20,5,1,'198.00','2020-02-11 11:48:22','已超时'),(21,5,2,'99.00','2020-02-11 11:53:35','已超时'),(22,5,1,'198.00','2020-02-12 12:14:01','未支付');

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goods_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `goods_name` varchar(100) NOT NULL COMMENT '商品名称',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '商品主图',
  `goods_info` varchar(1000) DEFAULT NULL COMMENT '商品描述',
  `goods_price` decimal(20,2) NOT NULL COMMENT '商品价格',
  `goods_count` int(20) NOT NULL COMMENT '商品库存',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='商品信息表';

/*Data for the table `goods` */

insert  into `goods`(`goods_id`,`goods_name`,`pic_url`,`goods_info`,`goods_price`,`goods_count`) values (1,'机械键盘','101.jpg',' <label style=\"color: #3a8ee6\">产品名称</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                机械键盘\n              </div>\n              <div style=\"margin-bottom: 30px\"></div>\n              <label style=\"color: #cf9236;\">产品描述</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                本机械键盘使用价值超高，推荐购买\n              </div>\n              <div style=\"margin-bottom: 30px\"></div>\n              <label style=\"color: #13ce66\">产品图片</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                <img src=\"http://127.0.0.1:8080/MyOrder/101.jpg\" style=\"width: 100%;height: 100%\" />\n              </div>','198.00',93),(2,'机械鼠标','102.jpg',' <label style=\"color: #3a8ee6\">产品名称</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                机械鼠标\n              </div>\n              <div style=\"margin-bottom: 30px\"></div>\n              <label style=\"color: #cf9236;\">产品描述</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                一款好鼠标让你事半功倍\n              </div>\n              <div style=\"margin-bottom: 30px\"></div>\n              <label style=\"color: #13ce66\">产品图片</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                <img src=\"http://127.0.0.1:8080/MyOrder/102.jpg\" style=\"width: 100%;height: 100%\" />\n              </div>','99.00',198),(4,'小米耳机','103.jpg',' <label style=\"color: #3a8ee6\">产品名称</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                小米耳机\n              </div>\n              <div style=\"margin-bottom: 30px\"></div>\n              <label style=\"color: #cf9236;\">产品描述</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                耳机小巧轻便，音质高清嗨到爆\n              </div>\n              <div style=\"margin-bottom: 30px\"></div>\n              <label style=\"color: #13ce66\">产品图片</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                <img src=\"http://127.0.0.1:8080/MyOrder/103.jpg\" style=\"width: 100%;height: 100%\" />\n              </div>','299.00',150),(5,'128G程序员必备U盘','104.jpg',' <label style=\"color: #3a8ee6\">产品名称</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                128G程序员必备U盘\n              </div>\n              <div style=\"margin-bottom: 30px\"></div>\n              <label style=\"color: #cf9236;\">产品描述</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                本U盘内存大，价格实惠，适合存储部分重要文件\n              </div>\n              <div style=\"margin-bottom: 30px\"></div>\n              <label style=\"color: #13ce66\">产品图片</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                <img src=\"http://127.0.0.1:8080/MyOrder/104.jpg\" style=\"width: 100%;height: 100%\" />\n              </div>','99.00',210),(6,'运动蓝牙','105.jpg',' <label style=\"color: #3a8ee6\">产品名称</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                运动蓝牙\n              </div>\n              <div style=\"margin-bottom: 30px\"></div>\n              <label style=\"color: #cf9236;\">产品描述</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                如果你是运动爱好者，可以尝试这部蓝牙。跑步摇晃不掉落，音质清晰给你完美体验\n              </div>\n              <div style=\"margin-bottom: 30px\"></div>\n              <label style=\"color: #13ce66\">产品图片</label>\n              <div style=\"border: 1px solid #ccc;padding: 7px 0px;border-radius: 3px;padding-left:5px;\">\n                <img src=\"http://127.0.0.1:8080/MyOrder/105.jpg\" style=\"width: 100%;height: 100%\" />\n              </div>','79.00',110);

/*Table structure for table `opr` */

DROP TABLE IF EXISTS `opr`;

CREATE TABLE `opr` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `opr_type` varchar(100) NOT NULL COMMENT '操作方法',
  `opr_name` varchar(100) NOT NULL COMMENT '操作人名称',
  `opr_time` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `opr` */

insert  into `opr`(`id`,`opr_type`,`opr_name`,`opr_time`) values (1,'登录','游客','2020-02-10 14:18:23'),(2,'登录','admin','2020-02-10 14:19:36'),(3,'登录','admin','2020-02-10 14:20:07'),(4,'下单','admin','2020-02-10 14:48:17'),(5,'登录','admin','2020-02-10 14:51:44'),(6,'下单','admin','2020-02-10 14:51:54'),(7,'支付','admin','2020-02-10 14:52:08'),(8,'登录','chb','2020-02-10 15:01:22'),(9,'下单','chb','2020-02-10 15:01:37'),(10,'支付','chb','2020-02-10 15:01:53'),(11,'登录','admin','2020-02-12 11:43:50'),(12,'下单','admin','2020-02-12 11:43:56'),(13,'下单','admin','2020-02-12 11:44:08'),(14,'下单','admin','2020-02-12 11:45:05'),(15,'下单','admin','2020-02-12 11:47:31'),(16,'下单','admin','2020-02-12 11:48:22'),(17,'支付','admin','2020-02-12 11:50:51'),(18,'下单','admin','2020-02-12 11:53:35'),(19,'登录','admin','2020-02-12 12:12:38'),(20,'下单','admin','2020-02-12 12:14:01');

/*Table structure for table `pay` */

DROP TABLE IF EXISTS `pay`;

CREATE TABLE `pay` (
  `pay_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '支付记录编号',
  `user_id` int(4) NOT NULL COMMENT '用户编号',
  `order_id` int(4) NOT NULL COMMENT '订单编号',
  `pay_before` decimal(20,2) NOT NULL COMMENT '支付前余额',
  `pay_after` decimal(20,2) NOT NULL COMMENT '支付后余额',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`pay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='支付信息表';

/*Data for the table `pay` */

insert  into `pay`(`pay_id`,`user_id`,`order_id`,`pay_before`,`pay_after`,`pay_time`) values (1,5,4,'1000.00','802.00','2020-01-24 16:16:26'),(2,5,5,'1000.00','901.00','2020-01-24 16:17:09'),(3,6,7,'1000.00','921.00','2020-01-24 16:23:49'),(4,5,6,'1000.00','901.00','2020-01-25 16:23:49'),(5,5,8,'901.00','602.00','2020-01-25 18:58:16'),(6,5,11,'602.00','602.00','2020-02-06 12:55:01'),(7,7,12,'1000.00','901.00','2020-02-06 13:05:28'),(8,5,14,'502.00','304.00','2020-02-10 14:52:08'),(9,6,15,'921.00','822.00','2020-02-10 15:01:53'),(10,5,16,'304.00','106.00','2020-02-12 11:50:51');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` varchar(60) NOT NULL COMMENT '用户名称',
  `user_pwd` varchar(100) NOT NULL COMMENT '用户密码',
  `user_balance` decimal(20,2) NOT NULL COMMENT '用户余额',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`user_pwd`,`user_balance`) values (4,'md5test','e10adc3949ba59abbe56e057f20f883e','802.00'),(5,'admin','e10adc3949ba59abbe56e057f20f883e','106.00'),(6,'chb','27c0b6c07b0d46b9f3af0e9894e7f6aa','822.00'),(7,'libai','e10adc3949ba59abbe56e057f20f883e','901.00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
