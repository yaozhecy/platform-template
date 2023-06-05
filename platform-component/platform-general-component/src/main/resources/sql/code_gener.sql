/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.200.74
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 192.168.200.74:3306
 Source Schema         : code_gener

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 31/03/2022 16:17:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for TB_DB_KBXX
-- ----------------------------
DROP TABLE IF EXISTS `TB_DB_KBXX`;
CREATE TABLE `TB_DB_KBXX`  (
  `CID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ISORT` int(255) NULL DEFAULT NULL COMMENT '序号',
  `CSJYID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据源ID',
  `CKBMC` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库表名称',
  `CYWMC` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `CKBMS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库表描述',
  `CSTMC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实体名称',
  `CCJSJ` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CXGSJ` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `ISJZT` int(11) NULL DEFAULT NULL COMMENT '数据状态',
  `CZWMC` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文名称',
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库表信息';

-- ----------------------------
-- Records of TB_DB_KBXX
-- ----------------------------
BEGIN;
INSERT INTO `TB_DB_KBXX` VALUES ('12d5833d35395a8e9258e4f089ba2cd6', 1, 'd4fb72ac33af879de3b0f88919fbebf1', 'TB_JK_JKJL', 'InterfaceInfo', '接口记录信息', 'JkJkjl', NULL, NULL, 0, '接口记录表'), ('48e1771af02b10fc36a93b81112968f0', 1, '194a041cf4a557ba50c25a27295c9a31', 'TB_JK_JKJL', 'InterfaceInfo', '接口记录信息', 'JkJkjl', NULL, NULL, 0, '接口记录表');
COMMIT;

-- ----------------------------
-- Table structure for TB_DB_MBPZ
-- ----------------------------
DROP TABLE IF EXISTS `TB_DB_MBPZ`;
CREATE TABLE `TB_DB_MBPZ`  (
  `CID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CDSID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据源ID',
  `CGJBM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工具包路径',
  `CBM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '包名',
  `CMKMC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '模板配置';

-- ----------------------------
-- Records of TB_DB_MBPZ
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for TB_DB_SJY
-- ----------------------------
DROP TABLE IF EXISTS `TB_DB_SJY`;
CREATE TABLE `TB_DB_SJY`  (
  `CID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `IBBH` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `IDBTYPE` int(11) NULL DEFAULT NULL COMMENT '数据库类型：1.MySql，2.SqlServer',
  `CSJYMC` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据源名称',
  `CSJKMC` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库名称',
  `CURL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库链接地址',
  `CUSER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库用户',
  `CPSSWD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库密码',
  `CDK` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库端口',
  `CWJMC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `CWJLJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `DCJSJ` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据源';

-- ----------------------------
-- Records of TB_DB_SJY
-- ----------------------------
BEGIN;
INSERT INTO `TB_DB_SJY` VALUES ('194a041cf4a557ba50c25a27295c9a31', NULL, 0, '默认数据.xlsx', '测试数据', '', '', '', NULL, NULL, 'tempfile\\1509443135225245698_默认数据.xlsx', '2022-03-31 16:14:02');
COMMIT;

-- ----------------------------
-- Table structure for TB_DB_ZDXX
-- ----------------------------
DROP TABLE IF EXISTS `TB_DB_ZDXX`;
CREATE TABLE `TB_DB_ZDXX`  (
  `CID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IZJ` int(11) NULL DEFAULT NULL COMMENT '主键：0',
  `CSJYID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据源ID',
  `CKBID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库表ID',
  `CZDMC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段名称',
  `CZDLX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'SQL字段类型',
  `ISFBC` int(11) NULL DEFAULT NULL COMMENT '是否必传',
  `CSJCD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据长度',
  `CZWHY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文含义',
  `CYWMC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `CZDMS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段描述',
  `CSXMC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Java属性名称',
  `CSJLX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Java类型',
  `ISJZT` int(11) NULL DEFAULT NULL COMMENT '数据状态',
  `ISORT` int(255) NULL DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字段信息表示';

-- ----------------------------
-- Records of TB_DB_ZDXX
-- ----------------------------
BEGIN;
INSERT INTO `TB_DB_ZDXX` VALUES ('208f72a721f0ca8dee847d2989eb41ca', 1, '194a041cf4a557ba50c25a27295c9a31', '48e1771af02b10fc36a93b81112968f0', 'IJKSYZ', 'int', 0, '', '接口使用者', 'useful', '', 'IJKSYZ', 'Integer', 0, 5), ('301f3ff287f2e2ac2beb7ec309b94010', 0, '194a041cf4a557ba50c25a27295c9a31', '48e1771af02b10fc36a93b81112968f0', 'CID', 'nvarchar', 0, '22', '主键', 'id', '', 'CID', 'String', 0, 1), ('64439794006cbfd1dc5b7fa56b2b480d', 1, '194a041cf4a557ba50c25a27295c9a31', '48e1771af02b10fc36a93b81112968f0', 'IJKZT', 'int', 0, '', '接口状态', 'status', '', 'IJKZT', 'Integer', 0, 8), ('6e8e43fa0e3e4c7d3f155fc198e6252d', 1, '194a041cf4a557ba50c25a27295c9a31', '48e1771af02b10fc36a93b81112968f0', 'CJKTGF', 'nvarchar', 0, '200', '接口提供方', 'approve', '', 'CJKTGF', 'String', 0, 11), ('ad2543b7ae7c7ea8295a6a835867fc98', 1, '194a041cf4a557ba50c25a27295c9a31', '48e1771af02b10fc36a93b81112968f0', 'CJKSYF', 'nvarchar', 0, '200', '接口使用方', 'users', '', 'CJKSYF', 'String', 0, 12), ('b41c210d2d0c9a4e0ccd2f9896e03504', 1, '194a041cf4a557ba50c25a27295c9a31', '48e1771af02b10fc36a93b81112968f0', 'IJKLX', 'int', 0, '', '接口类型', 'type', '类型字段：1 POST，2 GET', 'IJKLX', 'Integer', 0, 4), ('bf7e891c2ffe254eadf554663c7a7087', 1, '194a041cf4a557ba50c25a27295c9a31', '48e1771af02b10fc36a93b81112968f0', 'IJKMS', 'nvarchar', 0, '200', '接口描述', 'desc', '', 'IJKMS', 'String', 0, 10), ('cb3ef7a70ab0b118435c71b9bbbab003', 1, '194a041cf4a557ba50c25a27295c9a31', '48e1771af02b10fc36a93b81112968f0', 'IJKYWM', 'nvarchar', 0, '200', '接口英文名', 'english', '', 'IJKYWM', 'String', 0, 6), ('cbbc472e9ddd5179a7361ecb6821b5c8', 1, '194a041cf4a557ba50c25a27295c9a31', '48e1771af02b10fc36a93b81112968f0', 'IJKDZ', 'nvarchar', 0, '500', '接口地址', 'link', '', 'IJKDZ', 'String', 0, 9), ('d3a5969cb0bc0be6bb3651579cfdae7a', 1, '194a041cf4a557ba50c25a27295c9a31', '48e1771af02b10fc36a93b81112968f0', 'IQQFS', 'int', 0, '', '请求方式', 'method', '', 'IQQFS', 'Integer', 0, 7), ('d4bd9502bfef46575331ee4d19fa21e8', 1, '194a041cf4a557ba50c25a27295c9a31', '48e1771af02b10fc36a93b81112968f0', 'CJKBM', 'nvarchar', 0, '50', '接口编码', 'code', '', 'CJKBM', 'String', 0, 2), ('fa83b7f118fa5e5acb1beddf76f8651d', 1, '194a041cf4a557ba50c25a27295c9a31', '48e1771af02b10fc36a93b81112968f0', 'CJKMC', 'nvarchar', 0, '50', '接口名称', 'name', '', 'CJKMC', 'String', 0, 3);
COMMIT;

-- ----------------------------
-- Table structure for TB_GC_GCXX
-- ----------------------------
DROP TABLE IF EXISTS `TB_GC_GCXX`;
CREATE TABLE `TB_GC_GCXX`  (
  `CID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 主键',
  `CGCMC` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程名称',
  `CGCZZ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程作者',
  `CGCMS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程描述',
  `DCJSJ` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of TB_GC_GCXX
-- ----------------------------
BEGIN;
INSERT INTO `TB_GC_GCXX` VALUES ('1', '测试工程', 'YAOZHE', '这是一个测试', '2022-02-25 21:08:20');
COMMIT;

-- ----------------------------
-- Table structure for TB_KZ_SJBB
-- ----------------------------
DROP TABLE IF EXISTS `TB_KZ_SJBB`;
CREATE TABLE `TB_KZ_SJBB`  (
  `CBM` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主键',
  `CYWBM` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务编码',
  `IBBH` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `IDQBB` int(11) NULL DEFAULT NULL COMMENT '当前版本',
  `CYWID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '扩展-数据版本';

-- ----------------------------
-- Records of TB_KZ_SJBB
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for TB_KZ_XZLS
-- ----------------------------
DROP TABLE IF EXISTS `TB_KZ_XZLS`;
CREATE TABLE `TB_KZ_XZLS`  (
  `CID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CWDBM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CKEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CWDBB` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CYHID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CYHMC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CCJSJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of TB_KZ_XZLS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for TB_MB_MBFZ
-- ----------------------------
DROP TABLE IF EXISTS `TB_MB_MBFZ`;
CREATE TABLE `TB_MB_MBFZ`  (
  `CID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 主键',
  `CFZMC` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组名称',
  `ISFMR` int(11) NULL DEFAULT NULL COMMENT '是否默认',
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of TB_MB_MBFZ
-- ----------------------------
BEGIN;
INSERT INTO `TB_MB_MBFZ` VALUES ('821074001c7a3a61e7a5c863e9921ebb', '基础模板', 1);
COMMIT;

-- ----------------------------
-- Table structure for TB_MB_MBXX
-- ----------------------------
DROP TABLE IF EXISTS `TB_MB_MBXX`;
CREATE TABLE `TB_MB_MBXX`  (
  `CID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CFZID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CMBMC` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CMBLJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ISFMR` int(11) NULL DEFAULT NULL COMMENT '是否默认：0 默认，1 非默认',
  `IMBZT` int(11) NULL DEFAULT NULL,
  `DCJRQ` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of TB_MB_MBXX
-- ----------------------------
BEGIN;
INSERT INTO `TB_MB_MBXX` VALUES ('383e895dc873489a861db6851b042356', '821074001c7a3a61e7a5c863e9921ebb', 'VO', 'tempfile\\template\\1497204589902991362.vm', 1, 0, '2022-02-25 21:39:26');
COMMIT;

-- ----------------------------
-- Table structure for TB_MB_ZDXX
-- ----------------------------
DROP TABLE IF EXISTS `TB_MB_ZDXX`;
CREATE TABLE `TB_MB_ZDXX`  (
  `CID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `CZDZT` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段主题',
  `CZDBM` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段编码',
  `CZDMC` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段名称',
  `CZDDJ` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段级别',
  `CYSZD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原始字段',
  `IZHLX` int(11) NULL DEFAULT NULL COMMENT '转换类型',
  `CZHQ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转换器',
  `CZDJB` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转换脚本',
  `DCJSJ` datetime(0) NULL DEFAULT NULL COMMENT '转换时间',
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of TB_MB_ZDXX
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for TB_WD_WDJD
-- ----------------------------
DROP TABLE IF EXISTS `TB_WD_WDJD`;
CREATE TABLE `TB_WD_WDJD`  (
  `CID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CFJD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CJDBQ` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IJDLX` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文档节点';

-- ----------------------------
-- Records of TB_WD_WDJD
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for TB_WD_WDSJ
-- ----------------------------
DROP TABLE IF EXISTS `TB_WD_WDSJ`;
CREATE TABLE `TB_WD_WDSJ`  (
  `CID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `CMLID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CMLMC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CWJDZ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ISJZT` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of TB_WD_WDSJ
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for TB_XZ_LSJL
-- ----------------------------
DROP TABLE IF EXISTS `TB_XZ_LSJL`;
CREATE TABLE `TB_XZ_LSJL`  (
  `CID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `CWDBM` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档编码',
  `CKEY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档Key',
  `CYHID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `CYHMC` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `CZT` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档状态',
  `CWJLX` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `CWDDZ` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档文件路径',
  `CWDURL` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档URL',
  `CXGDZ` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改记录文件地址',
  `CXGURL` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改记录URL',
  `CXGRQ` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改日期',
  `IBB` int(11) NULL DEFAULT NULL COMMENT '版本',
  `CFWBB` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务版本',
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of TB_XZ_LSJL
-- ----------------------------
BEGIN;
INSERT INTO `TB_XZ_LSJL` VALUES ('01be9fa4ee0723ac2f289f740eaa8749', '58c0babf04de57e45b1cacf04127e594', '1500661767817170945', 'A1', 'A1', '2', 'docx', 'tempfile/58c0babf04de57e45b1cacf04127e594/1500661767817170945/temp.bak', 'http://192.168.200.64:6234/codegeneration/cooperation/download?type=doc&key=1500661767817170945', 'tempfile/58c0babf04de57e45b1cacf04127e594/1500661767817170945/tempHis.zip', 'http://192.168.200.64:6234/codegeneration/cooperation/download?type=changes&key=1500661767817170945', NULL, 1, '7.0.1'), ('802a38aea0f1903b4f8af84a465e40b1', '58c0babf04de57e45b1cacf04127e594', '1500655734042431489', NULL, NULL, '-1', 'docx', 'tempfile/58c0babf04de57e45b1cacf04127e594/协同平台V2.2接入接口标准(医疗机构端).docx', 'http://192.168.200.64:6234/codegeneration/cooperation/download?type=doc&key=1500655734042431489', NULL, NULL, NULL, 0, NULL), ('8946f445203992fb170fcba74692abab', 'e17c1a9991512301a7b585d7316968c6', '1504376252293746689', 'A1', 'A1', '2', 'docx', 'tempfile/e17c1a9991512301a7b585d7316968c6/1504376252293746689/temp.bak', 'http://192.168.200.64:6234/codegeneration/cooperation/download?type=doc&key=1504376252293746689', 'tempfile/e17c1a9991512301a7b585d7316968c6/1504376252293746689/tempHis.zip', 'http://192.168.200.64:6234/codegeneration/cooperation/download?type=changes&key=1504376252293746689', NULL, 1, '7.0.1'), ('ca89eab6fa8ec0beb6bb0473b980330b', 'e17c1a9991512301a7b585d7316968c6', '1501475901622226946', NULL, NULL, '-1', 'docx', 'tempfile/e17c1a9991512301a7b585d7316968c6/协同平台V2.2交互接口标准(第三方端).docx', 'http://192.168.200.64:6234/codegeneration/cooperation/download?type=doc&key=1501475901622226946', NULL, NULL, NULL, 0, NULL), ('de7a29edfe9887153eb7759b28d931fb', '58c0babf04de57e45b1cacf04127e594', '1504424988713717761', 'A1', 'A1', '2', 'docx', 'tempfile/58c0babf04de57e45b1cacf04127e594/1504424988713717761/temp.bak', 'http://192.168.200.64:6234/codegeneration/cooperation/download?type=doc&key=1504424988713717761', 'tempfile/58c0babf04de57e45b1cacf04127e594/1504424988713717761/tempHis.zip', 'http://192.168.200.64:6234/codegeneration/cooperation/download?type=changes&key=1504424988713717761', NULL, 2, '7.0.1'), ('f07f5657ae96c95e415c5dabe91f5c1f', 'e17c1a9991512301a7b585d7316968c6', '1508641781036650497', 'A1', 'A1', '2', 'docx', 'tempfile/e17c1a9991512301a7b585d7316968c6/1508641781036650497/temp.bak', 'http://192.168.200.64:6234/codegeneration/cooperation/download?type=doc&key=1508641781036650497', 'tempfile/e17c1a9991512301a7b585d7316968c6/1508641781036650497/tempHis.zip', 'http://192.168.200.64:6234/codegeneration/cooperation/download?type=changes&key=1508641781036650497', NULL, 2, '7.0.1');
COMMIT;

-- ----------------------------
-- Table structure for TB_XZ_LSJL2
-- ----------------------------
DROP TABLE IF EXISTS `TB_XZ_LSJL2`;
CREATE TABLE `TB_XZ_LSJL2`  (
  `CID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `CZJLID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主记录ID',
  `CYHID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `CYHMC` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `CXGRQ` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of TB_XZ_LSJL2
-- ----------------------------
BEGIN;
INSERT INTO `TB_XZ_LSJL2` VALUES ('5ec1ccdb41c42c44656ae34220e02b81', '01be9fa4ee0723ac2f289f740eaa8749', 'A1', 'A1', '2022-03-07 02:16:35'), ('a87b282b8c6652866e28bdd51469c6f3', 'f07f5657ae96c95e415c5dabe91f5c1f', 'A1', 'A1', '2022-03-28 06:07:49'), ('ce126869a58944da0f86128d8085f6d7', 'de7a29edfe9887153eb7759b28d931fb', 'A1', 'A1', '2022-03-17 08:39:01'), ('f80faf085681db5ec7643d27d0caa145', '8946f445203992fb170fcba74692abab', 'A1', 'A1', '2022-03-17 08:31:40');
COMMIT;

-- ----------------------------
-- Table structure for TB_XZ_WDJL
-- ----------------------------
DROP TABLE IF EXISTS `TB_XZ_WDJL`;
CREATE TABLE `TB_XZ_WDJL`  (
  `CID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `CWDMC` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档名称',
  `CKEY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'key',
  `CCJSJ` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `IZT` int(11) NULL DEFAULT NULL COMMENT '文档状态',
  PRIMARY KEY (`CID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of TB_XZ_WDJL
-- ----------------------------
BEGIN;
INSERT INTO `TB_XZ_WDJL` VALUES ('58c0babf04de57e45b1cacf04127e594', '协同平台V2.2接入接口标准(医疗机构端).docx', '1504424988713717761', '2022-03-07 10:13:02', 0), ('e17c1a9991512301a7b585d7316968c6', '协同平台V2.2交互接口标准(第三方端).docx', '1508641781036650497', '2022-03-09 16:32:05', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
