/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : medicine

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 03/07/2023 14:41:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address_info
-- ----------------------------
DROP TABLE IF EXISTS `address_info`;
CREATE TABLE `address_info`  (
                                 `AddressID` int NOT NULL AUTO_INCREMENT,
                                 `CountryforAddress` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                 `ProvinceforAddress` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                 `TownforAddress` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                 `DistrictforAddress` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                 `StreetforAddress` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                 `DetailAddress` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                 `ReceiveName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                 `ReceiveTel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                 PRIMARY KEY (`AddressID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address_info
-- ----------------------------

-- ----------------------------
-- Table structure for administrator_info
-- ----------------------------
DROP TABLE IF EXISTS `administrator_info`;
CREATE TABLE `administrator_info`  (
                                       `AdminID` int NOT NULL AUTO_INCREMENT,
                                       `ALoginID` int NULL DEFAULT NULL,
                                       `AdminAccount` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                       `AdminPassword` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                       `AdminName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                       `AdminLimit` tinyint(1) NOT NULL,
                                       PRIMARY KEY (`AdminID`) USING BTREE,
                                       INDEX `FK_Reference_4`(`ALoginID` ASC) USING BTREE,
                                       CONSTRAINT `FK_Reference_4` FOREIGN KEY (`ALoginID`) REFERENCES `administrator_login_log` (`ALoginID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator_info
-- ----------------------------

-- ----------------------------
-- Table structure for administrator_login_log
-- ----------------------------
DROP TABLE IF EXISTS `administrator_login_log`;
CREATE TABLE `administrator_login_log`  (
                                            `ALoginID` int NOT NULL AUTO_INCREMENT,
                                            `ALoginAdaID` int NOT NULL,
                                            `ALoginTime` datetime NOT NULL,
                                            `ALogoutTime` datetime NOT NULL,
                                            PRIMARY KEY (`ALoginID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for aftersale_log
-- ----------------------------
DROP TABLE IF EXISTS `aftersale_log`;
CREATE TABLE `aftersale_log`  (
                                  `LogIDforAfterSale` int NOT NULL AUTO_INCREMENT,
                                  `OrderID` int NULL DEFAULT NULL,
                                  `OrderIDforAfterSale` int NOT NULL,
                                  `OrderStatusforAfterSale` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                  `AfterSaleComments` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
                                  `AfterSaleCommentLevel` int NULL DEFAULT NULL,
                                  `AfterSaleCommentTime` datetime NOT NULL,
                                  PRIMARY KEY (`LogIDforAfterSale`) USING BTREE,
                                  INDEX `FK_Reference_8`(`OrderID` ASC) USING BTREE,
                                  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`OrderID`) REFERENCES `order_info` (`OrderID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aftersale_log
-- ----------------------------

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
                         `goods_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                         `goods_number` int NULL DEFAULT NULL,
                         `isSelected` tinyint NULL DEFAULT 0
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('欧阳小姐在成都', 1, 1);

-- ----------------------------
-- Table structure for comment_info
-- ----------------------------
DROP TABLE IF EXISTS `comment_info`;
CREATE TABLE `comment_info`  (
                                 `CommentID` int NOT NULL AUTO_INCREMENT,
                                 `CommodityID` int NULL DEFAULT NULL,
                                 `PhotosID` int NULL DEFAULT NULL,
                                 `CommentContent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                 PRIMARY KEY (`CommentID`) USING BTREE,
                                 INDEX `FK_Reference_2`(`CommodityID` ASC) USING BTREE,
                                 INDEX `FK_Reference_3`(`PhotosID` ASC) USING BTREE,
                                 CONSTRAINT `FK_Reference_2` FOREIGN KEY (`CommodityID`) REFERENCES `commodity_info` (`CommodityID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                 CONSTRAINT `FK_Reference_3` FOREIGN KEY (`PhotosID`) REFERENCES `photo` (`PhotosID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_info
-- ----------------------------

-- ----------------------------
-- Table structure for commodity_info
-- ----------------------------
DROP TABLE IF EXISTS `commodity_info`;
CREATE TABLE `commodity_info`  (
                                   `CommodityID` int NOT NULL AUTO_INCREMENT,
                                   `PhotosID` int NULL DEFAULT NULL,
                                   `MercchantID` int NULL DEFAULT NULL,
                                   `CommodityName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                   `CommodityType` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                   `CommodityDsec` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                   `CommodityPrice` float NOT NULL,
                                   `CommodityNumber` int NOT NULL,
                                   PRIMARY KEY (`CommodityID`) USING BTREE,
                                   INDEX `FK_Reference_1`(`PhotosID` ASC) USING BTREE,
                                   INDEX `FK_Reference_11`(`MercchantID` ASC) USING BTREE,
                                   CONSTRAINT `FK_Reference_1` FOREIGN KEY (`PhotosID`) REFERENCES `photo` (`PhotosID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                   CONSTRAINT `FK_Reference_11` FOREIGN KEY (`MercchantID`) REFERENCES `merchant_info` (`MercchantID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_info
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
                          `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                          `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                          `price` decimal(10, 2) NULL DEFAULT NULL,
                          `location` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                          `id` int NULL DEFAULT NULL,
                          `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                          PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('ELEPHANT小象西班牙餐厅&红酒吧', '2人Elephant玻璃屋下午茶套餐', 99.99, '香港东区香港柴湾环翠道750号', 10, 'goods_image_10');
INSERT INTO `goods` VALUES ('Magnet磁食', '2人新品牛排&汉堡套餐', 89.99, '香港东区香港柴湾环翠道750号', 13, 'goods_image_13');
INSERT INTO `goods` VALUES ('一冉料社', '2人元气樱花套餐', 45.50, '香港东区香港柴湾环翠道750号', 7, 'goods_image_7');
INSERT INTO `goods` VALUES ('一饭takara定食屋', '3人家庭超值套餐', 89.99, '香港东区香港柴湾环翠道750号', 12, 'goods_image_12');
INSERT INTO `goods` VALUES ('佩姐老火锅', '佩姐4人餐', 199.99, '香港东区香港柴湾环翠道750号', 5, 'goods_image_5');
INSERT INTO `goods` VALUES ('古京·臻致料理', '臻致单人日料自助', 70.00, '香港东区香港柴湾环翠道750号', 18, 'goods_image_18');
INSERT INTO `goods` VALUES ('味千拉面', '单人熊本火山拉面套餐', 29.99, '香港东区香港柴湾环翠道750号', 15, 'goods_image_15');
INSERT INTO `goods` VALUES ('咖藤花园意大利餐厅', '2人情意绵绵套餐', 67.00, '香港东区香港柴湾环翠道750号', 14, 'goods_image_14');
INSERT INTO `goods` VALUES ('在云端·Star Light高空西餐吧', '怦然心动·牛排双人套餐', 99.99, '香港东区香港柴湾环翠道750号', 11, 'goods_image_11');
INSERT INTO `goods` VALUES ('小杨生煎', '单人小炒牛肉生煎套餐D', 9.90, '香港东区香港柴湾环翠道750号', 1, 'goods_image_1');
INSERT INTO `goods` VALUES ('東盛碳烤自助料理', '安格斯工作日午市自助餐', 199.99, '香港东区香港柴湾环翠道750号', 16, 'goods_image_16');
INSERT INTO `goods` VALUES ('欧阳小姐在成都', '2人撸串拔草套餐', 152.99, '香港东区香港柴湾环翠道750号', 3, 'goods_image_3');
INSERT INTO `goods` VALUES ('牛排家', '尊享单人套餐', 40.00, '香港东区香港柴湾环翠道750号', 9, 'goods_image_9');
INSERT INTO `goods` VALUES ('肖小姐的厨房', '2-3人家庭欢乐战斧套餐', 45.00, '香港东区香港柴湾环翠道750号', 19, 'goods_image_19');
INSERT INTO `goods` VALUES ('花潮料理艺食馆', '工作日豪华全天单人自助餐', 56.00, '香港东区香港柴湾环翠道750号', 20, 'goods_image_20');
INSERT INTO `goods` VALUES ('蜜雪冰城', '2份蜜雪冰城666满杯百香果', 9.99, '香港东区香港柴湾环翠道750号', 8, 'goods_image_8');
INSERT INTO `goods` VALUES ('豪渝火锅', '豪吃十八碗2人餐', 49.90, '香港东区香港柴湾环翠道750号', 2, 'goods_image_2');
INSERT INTO `goods` VALUES ('韩国料理烤肉', '3-4人劲头满满套餐', 99.99, '香港东区香港柴湾环翠道750号', 4, 'goods_image_4');
INSERT INTO `goods` VALUES ('鳗御亭', '2人精致鳗鱼饭套餐', 56.00, '香港东区香港柴湾环翠道750号', 17, 'goods_image_17');
INSERT INTO `goods` VALUES ('麦当劳', '经典原味四件套', 29.99, '香港东区香港柴湾环翠道750号', 6, 'goods_image_6');

-- ----------------------------
-- Table structure for logistics_info
-- ----------------------------
DROP TABLE IF EXISTS `logistics_info`;
CREATE TABLE `logistics_info`  (
                                   `LogisticsID` int NOT NULL AUTO_INCREMENT,
                                   `LogisticsOrd` int NOT NULL,
                                   `LogisticComID` int NOT NULL,
                                   `LogisticAdiminID` int NOT NULL,
                                   `LogisticTrackingNum` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                   `LogisticCompany` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                   `LogisticDAddress` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                   `LogisticRAddress` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                   `LogisticDTime` datetime NOT NULL,
                                   `LogisticRTime` datetime NULL DEFAULT NULL,
                                   `LogisticType` int NOT NULL,
                                   `LogisticCause` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
                                   `LogisticOpTime` datetime NOT NULL,
                                   PRIMARY KEY (`LogisticsID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of logistics_info
-- ----------------------------

-- ----------------------------
-- Table structure for merchant_info
-- ----------------------------
DROP TABLE IF EXISTS `merchant_info`;
CREATE TABLE `merchant_info`  (
                                  `MercchantID` int NOT NULL AUTO_INCREMENT,
                                  `MercchantName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                  `MercchantDesc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                  `MercchantEvalue` int NOT NULL,
                                  PRIMARY KEY (`MercchantID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of merchant_info
-- ----------------------------

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
                               `OrderID` int NOT NULL AUTO_INCREMENT,
                               `LogisticsID` int NULL DEFAULT NULL,
                               `OrLogID` int NULL DEFAULT NULL,
                               `CommodityID` int NULL DEFAULT NULL,
                               `UserID` int NULL DEFAULT NULL,
                               `OrderTime` time NOT NULL,
                               `OrderPayState` tinyint(1) NOT NULL,
                               `OrderFullAmount` float NOT NULL,
                               PRIMARY KEY (`OrderID`) USING BTREE,
                               INDEX `FK_Reference_5`(`LogisticsID` ASC) USING BTREE,
                               INDEX `FK_Reference_6`(`OrLogID` ASC) USING BTREE,
                               INDEX `FK_Reference_7`(`CommodityID` ASC) USING BTREE,
                               INDEX `FK_Reference_9`(`UserID` ASC) USING BTREE,
                               CONSTRAINT `FK_Reference_5` FOREIGN KEY (`LogisticsID`) REFERENCES `logistics_info` (`LogisticsID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                               CONSTRAINT `FK_Reference_6` FOREIGN KEY (`OrLogID`) REFERENCES `order_log` (`OrLogID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                               CONSTRAINT `FK_Reference_7` FOREIGN KEY (`CommodityID`) REFERENCES `commodity_info` (`CommodityID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                               CONSTRAINT `FK_Reference_9` FOREIGN KEY (`UserID`) REFERENCES `user_info` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------

-- ----------------------------
-- Table structure for order_log
-- ----------------------------
DROP TABLE IF EXISTS `order_log`;
CREATE TABLE `order_log`  (
                              `OrLogID` int NOT NULL AUTO_INCREMENT,
                              `OrID` int NOT NULL,
                              `OrAdminID` int NOT NULL,
                              `OrLogTime` datetime NOT NULL,
                              PRIMARY KEY (`OrLogID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_log
-- ----------------------------

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo`  (
                          `PhotosID` int NOT NULL AUTO_INCREMENT,
                          `PhotoAddress` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                          PRIMARY KEY (`PhotosID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of photo
-- ----------------------------

-- ----------------------------
-- Table structure for shoppingcart_info
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart_info`;
CREATE TABLE `shoppingcart_info`  (
                                      `ShoppingCartID` int NOT NULL AUTO_INCREMENT,
                                      `UserID` int NULL DEFAULT NULL,
                                      `ShoppingCartAdd` int NULL DEFAULT NULL,
                                      `ShoppingCartAddTime` datetime NULL DEFAULT NULL,
                                      `ShoppingCartDelete` int NULL DEFAULT NULL,
                                      `ShoppingCartDeleteTime` datetime NULL DEFAULT NULL,
                                      `ShoppingCartAmount` int NOT NULL,
                                      PRIMARY KEY (`ShoppingCartID`) USING BTREE,
                                      INDEX `FK_Reference_12`(`UserID` ASC) USING BTREE,
                                      CONSTRAINT `FK_Reference_12` FOREIGN KEY (`UserID`) REFERENCES `user_info` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoppingcart_info
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `uid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                         `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                         `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                         `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '账号信息',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2126725123 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (-1853652990, 'AA1', '123456', NULL, 'ni');
INSERT INTO `user` VALUES (-1660731390, 'dwadawda', 'dasdasd', 'asdasd', 'dsdasda');
INSERT INTO `user` VALUES (-1379713023, 'zj', '22', 'dd246', '123456');
INSERT INTO `user` VALUES (-1090306047, 'wd', '1223', 'dd1234', '123456');
INSERT INTO `user` VALUES (-381452287, 'AA1', '123456', NULL, 'ni');
INSERT INTO `user` VALUES (-175947775, 'wwq2eq', 'weqw', 'ewqe', 'eqe');
INSERT INTO `user` VALUES (-71090174, 'hello', 'nihao', 'dd123456', '123456');
INSERT INTO `user` VALUES (1, 'AA', '123456', 'AA测试账号', '');
INSERT INTO `user` VALUES (2, 'adc', '123456', 'nidie', '');
INSERT INTO `user` VALUES (3, 'BB', '123456', 'BB测试账号', '');
INSERT INTO `user` VALUES (4, 'CC', '123456', 'CC测试数据', '');
INSERT INTO `user` VALUES (5, 'DD', '123456', '测试展账号', '');
INSERT INTO `user` VALUES (6, '东北硬汉', '123456', '懂不懂东北硬汉的含金量啊', '');
INSERT INTO `user` VALUES (7, 'AAAA', '123456', 'salfkads', '');
INSERT INTO `user` VALUES (8, 'AA1', '123456', NULL, '');
INSERT INTO `user` VALUES (9, 'dh', '123456', 'hello', '');
INSERT INTO `user` VALUES (10, 'ddd', '123456', 'wwww', '');
INSERT INTO `user` VALUES (155402242, 'ZhouJie', '123456', 'dd1357', '123456');
INSERT INTO `user` VALUES (373506049, 'dwadada', 'dasdasd', 'asdad', 'dasdasds');
INSERT INTO `user` VALUES (683900929, 'AA1', '123456', NULL, 'ni');
INSERT INTO `user` VALUES (1258504194, 'dwadaw', 'dasdasas', 'dasd', 'dadwadas');
INSERT INTO `user` VALUES (1321418754, 'AA12', '123456', NULL, 'ni');
INSERT INTO `user` VALUES (1812152322, '111', '111', '11', '1');
INSERT INTO `user` VALUES (2026061826, 'dd2345', '123456', '123', 'zhou');
INSERT INTO `user` VALUES (2126725122, 'dd12312313', '12312312', '313123', '21313');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
                              `UserID` int NOT NULL AUTO_INCREMENT,
                              `AddressID` int NULL DEFAULT NULL,
                              `ULoginID` int NULL DEFAULT NULL,
                              `UserAccount` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                              `UserPassword` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                              `UserSex` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                              `UserExtendInfo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
                              `UserName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                              `UserAge` int NULL DEFAULT NULL,
                              PRIMARY KEY (`UserID`) USING BTREE,
                              INDEX `FK_Reference_10`(`AddressID` ASC) USING BTREE,
                              INDEX `FK_Reference_13`(`ULoginID` ASC) USING BTREE,
                              CONSTRAINT `FK_Reference_10` FOREIGN KEY (`AddressID`) REFERENCES `address_info` (`AddressID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                              CONSTRAINT `FK_Reference_13` FOREIGN KEY (`ULoginID`) REFERENCES `user_login_log` (`ULoginID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------

-- ----------------------------
-- Table structure for user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `user_login_log`;
CREATE TABLE `user_login_log`  (
                                   `ULoginID` int NOT NULL AUTO_INCREMENT,
                                   `ULoginTime` datetime NOT NULL,
                                   `ULogoutTime` datetime NOT NULL,
                                   PRIMARY KEY (`ULoginID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_login_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
