/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : pengadaan_barang

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-08-23 22:58:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for karyawan
-- ----------------------------
DROP TABLE IF EXISTS `karyawan`;
CREATE TABLE `karyawan` (
  `NIK` varchar(15) NOT NULL,
  `NM_KRYWN` varchar(30) NOT NULL,
  `JABATAN` varchar(30) NOT NULL,
  `EMAIL` varchar(30) NOT NULL,
  `PASS` varchar(15) NOT NULL,
  PRIMARY KEY (`NIK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of karyawan
-- ----------------------------
INSERT INTO `karyawan` VALUES ('2012001', 'cilik', 'Teknisi', 'cilik@super.com', 'tes');
INSERT INTO `karyawan` VALUES ('2012002', 'anthem', 'Admin', 'anthem@ymail.co.id', 'mimin');
INSERT INTO `karyawan` VALUES ('2012003', 'symphony', 'Administrator', 'symphony@rocketmail.co.au', 'momod');

-- ----------------------------
-- Table structure for mst_barang
-- ----------------------------
DROP TABLE IF EXISTS `mst_barang`;
CREATE TABLE `mst_barang` (
  `id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_barang
-- ----------------------------
INSERT INTO `mst_barang` VALUES ('1', '000001', 'BANGKU');
INSERT INTO `mst_barang` VALUES ('2', '000002', 'GELAS');
INSERT INTO `mst_barang` VALUES ('3', '000002', 'GELAS');

-- ----------------------------
-- Table structure for mst_category_barang
-- ----------------------------
DROP TABLE IF EXISTS `mst_category_barang`;
CREATE TABLE `mst_category_barang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kd_ctg` varchar(255) DEFAULT NULL,
  `nm_ctg` varchar(255) DEFAULT NULL,
  `status_ctg` varchar(2) DEFAULT NULL,
  `type_barang_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_category_barang
-- ----------------------------
INSERT INTO `mst_category_barang` VALUES ('1', '001', 'ALAT TULIS KANTOR', 'A', '2');
INSERT INTO `mst_category_barang` VALUES ('2', '002', 'ALAT KEBERSIHAN', 'A', '2');
INSERT INTO `mst_category_barang` VALUES ('3', '003', 'ALAT LISTRIK', null, null);

-- ----------------------------
-- Table structure for mst_type_barang
-- ----------------------------
DROP TABLE IF EXISTS `mst_type_barang`;
CREATE TABLE `mst_type_barang` (
  `id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_type_barang
-- ----------------------------
INSERT INTO `mst_type_barang` VALUES ('1', '001', 'ASSET');
INSERT INTO `mst_type_barang` VALUES ('2', '002', 'HABIS PAKAI');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userid` int(255) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `ema` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'Daniel', 'Niko', '1986-01-02 00:00:00', null, 'daniel@updated.com');
