/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : 127.0.0.1:3306
Source Database       : pengadaan_barang

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-09-03 00:36:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mst_barang
-- ----------------------------
DROP TABLE IF EXISTS `mst_barang`;
CREATE TABLE `mst_barang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kd_barang` varchar(255) DEFAULT NULL,
  `nm_barang` varchar(255) DEFAULT NULL,
  `satuan_barang` varchar(20) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `ctg_barang_id` int(11) DEFAULT NULL,
  `hrg_jual_barang` double(255,0) DEFAULT NULL,
  `hrg_beli_barang` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_barang
-- ----------------------------
INSERT INTO `mst_barang` VALUES ('1', '0001', 'BANGKU', null, null, '1', '0', '0');
INSERT INTO `mst_barang` VALUES ('3', '0002', 'VACUUM CLEANER', 'BUAH', 'A', '2', '0', '0');
INSERT INTO `mst_barang` VALUES ('4', '0004', 'PULPEN', 'PCS', 'A', '1', '3000', '3000');
INSERT INTO `mst_barang` VALUES ('5', '0005', 'PENSIL', 'PCS', 'A', '1', '2500', '2500');
INSERT INTO `mst_barang` VALUES ('6', 'AL001', 'Lampu TL Philip 36 Watt', 'BUAH', 'A', '3', '7000', '7000');
INSERT INTO `mst_barang` VALUES ('7', 'AL002', 'Lampu Philip Biasa', 'BUAH', 'A', '3', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('8', 'AL003', 'Philip Starter', 'BUAH', 'A', '3', '6000', '6000');
INSERT INTO `mst_barang` VALUES ('9', 'AL004', 'Alkalin A3', 'BUAH', 'A', '3', '10000', '10000');
INSERT INTO `mst_barang` VALUES ('11', 'AK001', 'Sapu Lantai', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('12', 'AK002', 'Sapu Lidi Gagang', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('13', 'AK003', 'Pel Dorong', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('14', 'AK004', 'Refill Kain Pel', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('15', 'AK005', 'Casabella Pembersih Kaca', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('16', 'AK006', 'Pengki Plastik', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('17', 'AK007', 'Plastik Sampah', 'Pak', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('18', 'AK008', 'Kanebo', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('19', 'AK009', '3M Pad', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('20', 'AK010', 'Bellinzoni poles lantai', 'Btl', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('21', 'AK011', 'Cling Pembersih Kaca', 'Btl', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('22', 'AK012', 'Ember Plastik', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('23', 'AK013', 'Pel Karet', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('24', 'AK014', 'Kamper', 'Bks', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('25', 'AK015', 'Kamper Gantung', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('26', 'AK016', 'Karbol Pembersih Lantai', 'Btl', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('27', 'AK017', 'SOS Pembersih Lantai', 'Btl', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('28', 'AK018', 'Pembersih Toilet', 'Tube', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('29', 'AK019', 'Glade Refill', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('30', 'AK020', 'Sabun Mandi', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('31', 'AK021', 'Sunlight', 'Btl', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('32', 'AK022', 'Sabun Cuci Tangan', 'Btl', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('33', 'AK023', 'Tissue', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('34', 'AK024', 'Sikat Plastik', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('35', 'AK025', 'Sikat Kloset', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('36', 'AK026', 'Lap Kain', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('37', 'AK027', 'Lap Tangan', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('38', 'AK028', 'Sponge', 'Pcs', 'A', '2', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('39', 'AL005', 'Alkaline A2', 'BUAH', 'A', '3', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('40', 'AL006', 'Saklar Seri Broco', 'BUAH', 'A', '3', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('41', 'AL007', 'Stop Kontak Broco', 'BUAH', 'A', '3', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('42', 'AL008', 'Extension Uticon', 'BUAH', 'A', '3', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('43', 'AL009', 'Steker Broco', 'BUAH', 'A', '3', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('44', 'AL010', 'Eterna Kabel 2 x 2,5', 'BUAH', 'A', '3', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('45', 'AL011', 'Kabel Roll', 'BUAH', 'A', '3', '5000', '5000');
INSERT INTO `mst_barang` VALUES ('46', 'AL012', 'Klem Uticon', 'BUAH', 'A', '3', '5000', '5000');

-- ----------------------------
-- Table structure for mst_category_barang
-- ----------------------------
DROP TABLE IF EXISTS `mst_category_barang`;
CREATE TABLE `mst_category_barang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kd_ctg` varchar(255) DEFAULT NULL,
  `nm_ctg` varchar(255) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `type_barang_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_category_barang
-- ----------------------------
INSERT INTO `mst_category_barang` VALUES ('1', '001', 'ALAT TULIS KANTOR', 'A', '2');
INSERT INTO `mst_category_barang` VALUES ('2', '002', 'ALAT KEBERSIHAN', 'A', '2');
INSERT INTO `mst_category_barang` VALUES ('3', '003', 'ALAT LISTRIK', 'A', '2');
INSERT INTO `mst_category_barang` VALUES ('4', '004', 'PERALATAN DAN MESIN ', 'A', '2');
INSERT INTO `mst_category_barang` VALUES ('5', '005', 'ASSET', 'A', '1');

-- ----------------------------
-- Table structure for mst_divisi
-- ----------------------------
DROP TABLE IF EXISTS `mst_divisi`;
CREATE TABLE `mst_divisi` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_divisi
-- ----------------------------
INSERT INTO `mst_divisi` VALUES ('1', 'DIVISI A');
INSERT INTO `mst_divisi` VALUES ('2', 'DIVISI B');

-- ----------------------------
-- Table structure for mst_satuan
-- ----------------------------
DROP TABLE IF EXISTS `mst_satuan`;
CREATE TABLE `mst_satuan` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category_barang_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_satuan
-- ----------------------------
INSERT INTO `mst_satuan` VALUES ('1', 'PCS', '1');
INSERT INTO `mst_satuan` VALUES ('2', 'PCS', '2');
INSERT INTO `mst_satuan` VALUES ('3', 'PCS', '3');
INSERT INTO `mst_satuan` VALUES ('4', 'PCS', '4');
INSERT INTO `mst_satuan` VALUES ('5', 'PCS', '5');
INSERT INTO `mst_satuan` VALUES ('6', 'BUAH', '1');
INSERT INTO `mst_satuan` VALUES ('7', 'BUAH', '2');
INSERT INTO `mst_satuan` VALUES ('8', 'BUAH', '3');
INSERT INTO `mst_satuan` VALUES ('9', 'BUAH', '4');
INSERT INTO `mst_satuan` VALUES ('10', 'BUAH', '5');
INSERT INTO `mst_satuan` VALUES ('11', 'UNIT', '1');
INSERT INTO `mst_satuan` VALUES ('12', 'UNIT', '2');
INSERT INTO `mst_satuan` VALUES ('13', 'UNIT', '3');
INSERT INTO `mst_satuan` VALUES ('14', 'UNIT', '4');
INSERT INTO `mst_satuan` VALUES ('15', 'UNIT', '5');
INSERT INTO `mst_satuan` VALUES ('16', 'BTL', '1');
INSERT INTO `mst_satuan` VALUES ('17', 'BTL', '2');
INSERT INTO `mst_satuan` VALUES ('18', 'BTL', '3');
INSERT INTO `mst_satuan` VALUES ('19', 'BTL', '4');
INSERT INTO `mst_satuan` VALUES ('20', 'BTL', '5');
INSERT INTO `mst_satuan` VALUES ('21', 'PACK', '1');
INSERT INTO `mst_satuan` VALUES ('22', 'PACK', '2');
INSERT INTO `mst_satuan` VALUES ('23', 'PACK', '3');
INSERT INTO `mst_satuan` VALUES ('24', 'PACK', '4');
INSERT INTO `mst_satuan` VALUES ('25', 'PACK', '5');

-- ----------------------------
-- Table structure for mst_type_barang
-- ----------------------------
DROP TABLE IF EXISTS `mst_type_barang`;
CREATE TABLE `mst_type_barang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_type_barang
-- ----------------------------
INSERT INTO `mst_type_barang` VALUES ('1', '001', 'ASSET');
INSERT INTO `mst_type_barang` VALUES ('2', '002', 'HABIS PAKAI');

-- ----------------------------
-- Table structure for mst_user
-- ----------------------------
DROP TABLE IF EXISTS `mst_user`;
CREATE TABLE `mst_user` (
  `NIK` varchar(15) NOT NULL,
  `NM_KRYWN` varchar(30) NOT NULL,
  `JABATAN` varchar(30) NOT NULL,
  `EMAIL` varchar(30) NOT NULL,
  `PASS` varchar(15) NOT NULL,
  PRIMARY KEY (`NIK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_user
-- ----------------------------
INSERT INTO `mst_user` VALUES ('1', 'symphony', 'Administrator', 'symphony@rocketmail.co.au', '1');
INSERT INTO `mst_user` VALUES ('2012001', 'cilik', 'Teknisi', 'cilik@super.com', 'tes');
INSERT INTO `mst_user` VALUES ('2012002', 'anthem', 'Admin', 'anthem@ymail.co.id', 'mimin');

-- ----------------------------
-- Table structure for trx_pemasukan
-- ----------------------------
DROP TABLE IF EXISTS `trx_pemasukan`;
CREATE TABLE `trx_pemasukan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trx_no_pemasukan` varchar(255) DEFAULT NULL,
  `trx_date_pemasukan` date DEFAULT NULL,
  `divisi_id` int(11) DEFAULT NULL,
  `trx_desc_pemasukan` varchar(255) DEFAULT NULL,
  `trx_total_pemasukan` double(255,0) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of trx_pemasukan
-- ----------------------------
INSERT INTO `trx_pemasukan` VALUES ('24', '0001/SO/08/2015', '2015-08-31', '1', '-', '0', null, null);

-- ----------------------------
-- Table structure for trx_pemasukan_item
-- ----------------------------
DROP TABLE IF EXISTS `trx_pemasukan_item`;
CREATE TABLE `trx_pemasukan_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `barang_id` int(11) DEFAULT NULL,
  `barang_name` varchar(255) DEFAULT NULL,
  `trx_qty_pemasukan_item` double(11,0) DEFAULT NULL,
  `trx_price_pemasukan_item` double(255,0) DEFAULT NULL,
  `trx_honor_pemasukan_item` double(255,0) DEFAULT NULL,
  `trx_total_pemasukan_item` double(255,0) DEFAULT NULL,
  `trx_pemasukan_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of trx_pemasukan_item
-- ----------------------------
INSERT INTO `trx_pemasukan_item` VALUES ('26', '11', 'Sapu Lantai', '36', '5000', '0', '180000', '24');
INSERT INTO `trx_pemasukan_item` VALUES ('27', '12', 'Sapu Lidi Gagang', '12', '5000', '0', '60000', '24');

-- ----------------------------
-- Table structure for trx_pengeluaran
-- ----------------------------
DROP TABLE IF EXISTS `trx_pengeluaran`;
CREATE TABLE `trx_pengeluaran` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trx_no_pengeluaran` varchar(255) DEFAULT NULL,
  `trx_date_pengeluaran` date DEFAULT NULL,
  `divisi_id` int(11) DEFAULT NULL,
  `trx_desc_pengeluaran` varchar(255) DEFAULT NULL,
  `trx_total_pengeluaran` double(255,0) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of trx_pengeluaran
-- ----------------------------
INSERT INTO `trx_pengeluaran` VALUES ('33', '0001/INV/09/2015', '2015-09-01', '1', '-', '0', null, null);

-- ----------------------------
-- Table structure for trx_pengeluaran_item
-- ----------------------------
DROP TABLE IF EXISTS `trx_pengeluaran_item`;
CREATE TABLE `trx_pengeluaran_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `barang_id` int(11) DEFAULT NULL,
  `barang_name` varchar(255) DEFAULT NULL,
  `trx_qty_pengeluaran_item` double(11,0) DEFAULT NULL,
  `trx_price_pengeluaran_item` double(255,0) DEFAULT NULL,
  `trx_honor_pengeluaran_item` double(255,0) DEFAULT NULL,
  `trx_total_pengeluaran_item` double(255,0) DEFAULT NULL,
  `trx_pengeluaran_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of trx_pengeluaran_item
-- ----------------------------
INSERT INTO `trx_pengeluaran_item` VALUES ('20', '11', 'Sapu Lantai', '6', '5000', '0', '30000', '33');
INSERT INTO `trx_pengeluaran_item` VALUES ('21', '12', 'Sapu Lidi Gagang', '5', '5000', '0', '25000', '33');

-- ----------------------------
-- Table structure for trx_stock
-- ----------------------------
DROP TABLE IF EXISTS `trx_stock`;
CREATE TABLE `trx_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `barang_id` int(11) DEFAULT NULL,
  `barang_name` varchar(255) DEFAULT NULL,
  `trx_stock_date` date DEFAULT NULL,
  `trx_stock_qty` int(11) DEFAULT NULL,
  `trx_pp_id` int(11) DEFAULT NULL,
  `trx_stock_price` double DEFAULT NULL,
  `trx_stock_honor` double DEFAULT NULL,
  `trx_stock_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of trx_stock
-- ----------------------------
INSERT INTO `trx_stock` VALUES ('2', '11', 'Sapu Lantai', '2015-08-31', '36', '26', '5000', null, '1');
INSERT INTO `trx_stock` VALUES ('3', '12', 'Sapu Lidi Gagang', '2015-08-31', '12', '26', '5000', null, '1');
INSERT INTO `trx_stock` VALUES ('4', '11', 'Sapu Lantai', '2015-09-01', '6', '20', '5000', '0', '2');
INSERT INTO `trx_stock` VALUES ('5', '12', 'Sapu Lidi Gagang', '2015-09-01', '5', '20', '5000', '0', '2');
