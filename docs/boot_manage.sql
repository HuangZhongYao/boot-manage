/*
 Navicat Premium Data Transfer

 Source Server         :
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           :
 Source Schema         : boot_manage

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 01/08/2024 16:30:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_template
-- ----------------------------
DROP TABLE IF EXISTS `base_template`;
CREATE TABLE `base_template`  (
                                  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                                  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                                  `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '创建表基础模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_template
-- ----------------------------

-- ----------------------------
-- Table structure for bus_order
-- ----------------------------
DROP TABLE IF EXISTS `bus_order`;
CREATE TABLE `bus_order`  (
                              `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                              `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                              `updated_time` date NULL DEFAULT NULL COMMENT '更新时间',
                              `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                              `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                              `order_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单号',
                              `commodity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品',
                              `order_time` datetime(0) NULL DEFAULT NULL COMMENT '下单时间',
                              `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货地址',
                              `consignee` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人',
                              `consignee_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人电话',
                              `pay_state` tinyint(0) NULL DEFAULT NULL COMMENT '支付状态',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_order
-- ----------------------------
INSERT INTO `bus_order` VALUES (1, '2024-07-28 11:59:49', '2024-07-28', NULL, NULL, '12791823942377777777776666666666666666666666666666666666', '电脑', '2024-08-02 11:56:06', '贵阳市', 'zuuuYao', '18796352356', 0);
INSERT INTO `bus_order` VALUES (2, '2024-07-28 12:00:11', '2024-07-28', NULL, NULL, '453453453434534534534536666666666666666666666666666666', '手机', '2024-08-02 11:56:06', '贵阳市', 'zuuuYao', '18796352356', 0);
INSERT INTO `bus_order` VALUES (4, '2024-07-28 12:00:12', '2024-07-28', NULL, NULL, '44444444444345345345347777777777777777777777777', 'AI机器人', '2024-05-01 11:56:06', '广州', '彭于晏', '9999999999', 4);
INSERT INTO `bus_order` VALUES (6, '2024-07-28 12:00:12', '2024-07-28', NULL, NULL, '66666666666666663453477777777777777777777777777777777777', '笔记本电脑', '2024-08-01 11:56:06', '成都市', '成关系', '19985623214', 2);
INSERT INTO `bus_order` VALUES (7, '2024-07-28 12:10:15', '2024-07-28', NULL, NULL, '852363455555555555555555555555553477777777777777777777777', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (9, '2024-07-28 12:42:17', '2024-07-28', NULL, NULL, '8523622222222222', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (12, '2024-07-28 12:42:18', '2024-07-28', NULL, '1', '85236', '苹果000000000', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 1);
INSERT INTO `bus_order` VALUES (14, '2024-07-28 12:42:18', '2024-07-28', NULL, '1', '85236', '苹果7888', '2024-04-02 11:56:06', '北京朝阳区', 'liYao', '111111111111', 1);
INSERT INTO `bus_order` VALUES (15, '2024-07-28 12:42:18', '2024-07-28', NULL, '1', '888888888888888888', '苹果888888888888', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 1);
INSERT INTO `bus_order` VALUES (17, '2024-07-28 12:42:18', '2024-07-28', NULL, '1', '5455555555555', '苹果6666666666', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 1);
INSERT INTO `bus_order` VALUES (18, '2024-07-28 12:42:19', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (19, '2024-07-28 12:42:19', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (20, '2024-07-28 12:42:19', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (21, '2024-07-28 12:42:19', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (22, '2024-07-28 12:42:19', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (23, '2024-07-28 12:42:19', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (24, '2024-07-28 12:42:20', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (25, '2024-07-28 12:42:20', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (26, '2024-07-28 12:42:20', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (27, '2024-07-28 12:42:20', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (28, '2024-07-28 12:42:20', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (29, '2024-07-28 12:42:20', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (32, '2024-07-28 12:42:21', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (33, '2024-07-28 12:42:21', '2024-07-31', NULL, '22', '陈冠希', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 12);
INSERT INTO `bus_order` VALUES (34, '2024-07-28 12:42:21', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (35, '2024-07-28 12:42:21', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (36, '2024-07-28 12:42:22', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (37, '2024-07-28 12:42:22', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (38, '2024-07-28 12:42:22', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (39, '2024-07-28 12:42:22', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (40, '2024-07-28 12:42:22', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (41, '2024-07-28 12:42:22', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (42, '2024-07-28 12:42:22', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (43, '2024-07-28 12:42:23', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (44, '2024-07-28 12:42:23', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (45, '2024-07-28 12:42:23', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (46, '2024-07-28 12:42:23', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (47, '2024-07-28 12:42:23', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (48, '2024-07-28 12:42:23', '2024-07-28', NULL, NULL, '85236', '苹果', '2024-04-02 11:56:06', '北京', 'liYao', '111111111111', 0);
INSERT INTO `bus_order` VALUES (49, '2024-07-28 18:16:06', '2024-07-31', '1', '1', '2222222222', 'sdf3333333333', '2024-08-01 11:56:06', '534', 'dsfsd', 'sdf', 5);
INSERT INTO `bus_order` VALUES (51, '2024-07-28 18:37:43', '2024-07-28', '1', '1', '22222', '牛肉', '2024-04-02 11:56:06', '4545', '34534', '545', 23);
INSERT INTO `bus_order` VALUES (53, '2024-07-28 20:03:37', '2024-07-28', '1', '1', 'YR455454', '羊肉', '2024-04-02 11:56:06', '重庆市', '78', '878', 2);
INSERT INTO `bus_order` VALUES (54, '2024-07-28 22:12:45', '2024-07-28', '1', '1', 'GR36984554545', '狗肉', '2024-08-01 11:56:06', '云岩区', '王小李', '148563201545', 2);
INSERT INTO `bus_order` VALUES (55, '2024-07-31 00:45:52', '2024-07-31', '1', '1', 'UUUU89999', 'yuuu', '2024-04-02 11:56:06', '天机市', '黄中国农村', '34534534', 2);

-- ----------------------------
-- Table structure for bus_truck
-- ----------------------------
DROP TABLE IF EXISTS `bus_truck`;
CREATE TABLE `bus_truck`  (
                              `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                              `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                              `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                              `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                              `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                              `plate_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车牌号',
                              `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '型号',
                              `vin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'VIN车辆识别代码',
                              `reg_date` datetime(0) NULL DEFAULT NULL COMMENT '注册日期',
                              `issuers` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发证机关',
                              `owner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆所有人',
                              `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品牌',
                              `quality` int(0) NULL DEFAULT NULL COMMENT '总质量kg',
                              `file_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文档编号',
                              `engine_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发动机编号',
                              `vehicle_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车型',
                              `use_character` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '使用性质',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '车辆表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_truck
-- ----------------------------
INSERT INTO `bus_truck` VALUES (1, '2024-07-31 01:05:24', '2024-07-31 01:05:42', '1', '1', '贵AT2P57', 'BEN-Insper-accord', '453452654645', '2024-07-11 12:00:00', '贵阳市公安局', '王老五', '本田', 1563, '522356477', '1243664', '轿车', '非营运');
INSERT INTO `bus_truck` VALUES (3, '2024-07-31 10:20:03', '2024-07-31 10:20:03', '1', '1', '贵A670993', 'Benz-me', 'DFJSDO2390jLKDLF', '2024-09-12 12:34:45', '贵阳市公安局', '赵四', '奔驰', 2230, '520001234', '792349', '两轮摩托', '营运');

-- ----------------------------
-- Table structure for sys_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources`  (
                                  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                                  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '上级资源id',
                                  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                                  `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                                  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源编码',
                                  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源名称',
                                  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源类型',
                                  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由地址',
                                  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
                                  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
                                  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
                                  `layout` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '布局方式',
                                  `is_show` tinyint(0) NULL DEFAULT NULL COMMENT '是否显示',
                                  `enable` tinyint(0) NULL DEFAULT NULL COMMENT '是否启用',
                                  `keep_alive` tinyint(0) NULL DEFAULT NULL COMMENT '是否启用组件之间切换时缓存它们的状态',
                                  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
                                  PRIMARY KEY (`id`, `code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_resources
-- ----------------------------
INSERT INTO `sys_resources` VALUES (1, 2, '2024-07-15 14:54:40', '2024-07-15 14:54:40', '1', '1', 'ResourceMgt', '资源管理', '1', '/pms/resource', '/src/views/pms/resource/index.vue', '系统功能', 'i-fe:list', '', 1, 1, 1, 1);
INSERT INTO `sys_resources` VALUES (2, NULL, '2024-07-15 14:54:40', '2024-07-15 14:54:40', '1', '1', 'SysMgt', '系统管理', '1', NULL, NULL, '系统功能', 'i-fe:grid', '', 1, 1, NULL, 2);
INSERT INTO `sys_resources` VALUES (3, 2, '2024-07-15 14:54:40', '2024-07-15 14:54:40', '1', '1', 'RoleMgt', '角色管理', '1', '/pms/role', '/src/views/pms/role/index.vue', '系统功能', 'i-fe:user-check', '', 1, 1, 1, 2);
INSERT INTO `sys_resources` VALUES (4, 2, '2024-07-15 14:54:40', '2024-07-15 14:54:40', '1', '1', 'UserMgt', '用户管理', '1', '/pms/user', '/src/views/pms/user/index.vue', '系统功能', 'i-fe:user', '', 1, 1, 1, 3);
INSERT INTO `sys_resources` VALUES (6, NULL, '2024-07-15 14:54:40', '2024-07-15 14:54:40', '1', '1', 'Demo', '业务示例', '1', NULL, NULL, '系统功能', 'i-fe:grid', '', 1, 1, NULL, 1);
INSERT INTO `sys_resources` VALUES (7, 6, '2024-07-15 14:54:40', '2024-07-15 14:54:40', '1', '1', 'ImgUpload', '图片上传', '1', '/demo/upload', '/src/views/demo/upload/index.vue', '系统功能', 'i-fe:image', '', 1, 1, 1, 2);
INSERT INTO `sys_resources` VALUES (8, NULL, '2024-07-15 14:54:40', '2024-07-15 14:54:40', '1', '1', 'UserProfile', '个人资料', '1', '/profile', '/src/views/profile/index.vue', '系统功能', 'i-fe:user', '', 0, 1, 1, 99);
INSERT INTO `sys_resources` VALUES (9, NULL, '2024-07-15 14:54:39', '2024-07-15 14:54:39', '1', '1', 'Base', '基础功能', '1', '', NULL, '系统功能', 'i-fe:grid', '', 1, 1, NULL, 0);
INSERT INTO `sys_resources` VALUES (10, 9, '2024-07-15 14:54:40', '2024-07-15 14:54:40', '1', '1', 'BaseComponents', '基础组件', '1', '/base/components', '/src/views/base/index.vue', '系统功能', 'i-me:awesome', '', 1, 1, NULL, 1);
INSERT INTO `sys_resources` VALUES (11, 9, '2024-07-15 14:54:40', '2024-07-15 14:54:40', '1', '1', 'Unocss', 'Unocss', '1', '/base/unocss', '/src/views/base/unocss.vue', '系统功能', 'i-me:awesome', '', 1, 1, NULL, 2);
INSERT INTO `sys_resources` VALUES (12, 9, '2024-07-15 14:54:40', '2024-07-15 14:54:40', '1', '1', 'KeepAlive', 'KeepAlive', '1', '/base/keep-alive', '/src/views/base/keep-alive.vue', '系统功能', 'i-me:awesome', '', 1, 1, 1, 3);
INSERT INTO `sys_resources` VALUES (13, 4, '2024-07-15 14:54:40', '2024-07-25 23:11:23', '1', '1', 'AddUser', '创建用户', '3', NULL, NULL, '系统功能', 'i-me:btn', '', 1, 1, NULL, 1);
INSERT INTO `sys_resources` VALUES (14, 9, '2024-07-15 14:54:40', '2024-07-15 14:54:40', '1', '1', 'Icon', '图标 Icon', '1', '/base/icon', '/src/views/base/unocss-icon.vue', '系统功能', 'i-fe:feather', '', 1, 1, NULL, 0);
INSERT INTO `sys_resources` VALUES (24, 8, '2024-07-25 14:27:01', '2024-07-25 14:43:38', '1', '22', 'EditProfile', '修改资料', '3', NULL, NULL, '编辑个人信息按钮', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (25, 8, '2024-07-25 14:33:23', '2024-07-25 14:33:23', '1', '1', 'ChangePwd', '修改密码', '3', NULL, NULL, '修改密码按钮', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (26, 8, '2024-07-25 14:34:07', '2024-07-25 14:34:07', '1', '1', 'ChangeAvatar', '修改头像', '3', NULL, NULL, '修改头像按钮', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (27, 3, '2024-07-25 14:37:05', '2024-07-25 14:37:05', '1', '1', 'AddRole', '新增角色', '3', NULL, NULL, '新增角色按钮', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (28, 1, '2024-07-25 15:08:00', '2024-07-25 15:08:00', '1', '1', 'AddResources', '新增', '3', NULL, NULL, '资源管理新增按钮', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (29, 1, '2024-07-25 15:08:35', '2024-07-25 15:08:35', '1', '1', 'EditResources', '编辑', '3', NULL, NULL, '资源管理编辑按钮', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (30, 1, '2024-07-25 15:09:11', '2024-07-25 15:09:11', '1', '1', 'DelResources', '删除', '3', NULL, NULL, '资源管理删除按钮', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (31, 4, '2024-07-25 18:24:10', '2024-07-25 18:24:10', '1', '1', 'DelUser', '删除', '3', NULL, NULL, '删除用户按钮', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (32, 4, '2024-07-25 18:30:49', '2024-07-25 18:30:49', '1', '1', 'SetRole', '分配角色', '3', NULL, NULL, '用户管理页码分配角色按钮', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (33, 4, '2024-07-25 18:31:25', '2024-07-25 18:31:25', '1', '1', 'ResetPwd', '重置密码', '3', NULL, NULL, '用户管理页码重置密码', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (34, 4, '2024-07-25 18:34:25', '2024-07-25 18:34:25', '1', '1', 'Enable|DisableUser', '启用|禁用', '3', NULL, NULL, '用户管理页码 启用|禁用 用户按钮', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (35, 3, '2024-07-25 18:38:32', '2024-07-25 18:38:32', '1', '1', 'DelRole', '删除', '3', NULL, NULL, '删除角色', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (36, 3, '2024-07-25 18:42:58', '2024-07-25 18:42:58', '1', '1', 'EditRole', '编辑', '3', NULL, NULL, '角色管理页码编辑角色按钮', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (37, 3, '2024-07-25 18:43:45', '2024-07-25 18:43:45', '1', '1', 'SetUser', '分配用户', '3', NULL, NULL, '角色管理页码分配用户按钮', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (38, 3, '2024-07-25 18:45:25', '2024-07-25 18:45:25', '1', '1', 'Enable|DisableRole', '启用|停用', '3', NULL, NULL, '角色管理页码启用或停用角色按钮', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (39, 1, '2024-07-25 19:06:23', '2024-07-25 19:06:23', '1', '1', 'Enable|DisableResources', '启用|停用', '3', NULL, NULL, '资源管理页面启用或停用’按钮‘按钮', 'i-me:btn', '', 1, 1, NULL, NULL);
INSERT INTO `sys_resources` VALUES (42, NULL, '2024-07-29 18:47:01', '2024-07-29 18:47:01', '1', '1', 'DevTool', '开发工具', '1', NULL, NULL, '开发管理、运维管理、代码生成', 'i-fe:tool', '', 1, 1, NULL, 10);
INSERT INTO `sys_resources` VALUES (43, 42, '2024-07-29 18:47:54', '2024-07-29 18:47:54', '1', '1', 'CodeGenerate', '代码生成', '1', '/dev/code', '/src/views/generate/index.vue', '代码生成页面', 'i-fe:code', '', 1, 1, 1, 1);
INSERT INTO `sys_resources` VALUES (72, 6, '2024-07-31 10:03:06', '2024-07-31 10:03:06', '1', '1', 'BusOrderMgt', '商城订单管理', '1', '/Demo/BusOrderMgt', '/src/views/order/index.vue', '商城订单管理', 'i-fe:menu', '', 1, 1, 1, 0);
INSERT INTO `sys_resources` VALUES (73, 72, '2024-07-31 10:03:06', '2024-07-31 10:03:06', '1', '1', 'AddBusOrder', '创建按钮', '3', NULL, NULL, '创建商城订单按钮', 'i-me:btn', '', 1, 1, 0, 0);
INSERT INTO `sys_resources` VALUES (74, 72, '2024-07-31 10:03:06', '2024-07-31 10:03:06', '1', '1', 'EditBusOrder', '编辑按钮', '3', NULL, NULL, '编辑商城订单按钮', 'i-me:btn', '', 1, 1, 0, 0);
INSERT INTO `sys_resources` VALUES (75, 72, '2024-07-31 10:03:06', '2024-07-31 10:03:06', '1', '1', 'DelBusOrder', '删除按钮', '3', NULL, NULL, '删除商城订单按钮', 'i-me:btn', '', 1, 1, 0, 0);
INSERT INTO `sys_resources` VALUES (76, 6, '2024-07-31 10:03:46', '2024-07-31 10:03:46', '1', '1', 'BusTruckMgt', '车辆管理', '1', '/Demo/BusTruckMgt', '/src/views/truck/index.vue', '车辆管理', 'i-fe:menu', '', 1, 1, 1, 0);
INSERT INTO `sys_resources` VALUES (77, 76, '2024-07-31 10:03:46', '2024-07-31 10:03:46', '1', '1', 'AddBusTruck', '创建按钮', '3', NULL, NULL, '创建车辆按钮', 'i-me:btn', '', 1, 1, 0, 0);
INSERT INTO `sys_resources` VALUES (78, 76, '2024-07-31 10:03:46', '2024-07-31 10:03:46', '1', '1', 'EditBusTruck', '编辑按钮', '3', NULL, NULL, '编辑车辆按钮', 'i-me:btn', '', 1, 1, 0, 0);
INSERT INTO `sys_resources` VALUES (79, 76, '2024-07-31 10:03:46', '2024-07-31 10:03:46', '1', '1', 'DelBusTruck', '删除按钮', '3', NULL, NULL, '删除车辆按钮', 'i-me:btn', '', 1, 1, 0, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                             `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                             `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                             `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                             `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                             `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
                             `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                             `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
                             `enable` tinyint(0) NULL DEFAULT NULL COMMENT '启用状态',
                             `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '2024-07-15 22:28:58', '2024-07-23 01:40:10', NULL, NULL, '超级管理员', '超级管理员拥有所有权限', 'i-me:superadmin', 1, 'SUPER_ADMIN');
INSERT INTO `sys_role` VALUES (2, '2024-07-17 10:43:07', '2024-07-25 23:08:49', NULL, '1', '业务员', '测试实施', 'i-me:role', 1, 'TESt');
INSERT INTO `sys_role` VALUES (3, '2024-07-17 12:10:21', '2024-07-25 23:08:48', NULL, '1', '操作员', '设备操作员', 'i-fe:user', 1, 'ttt');
INSERT INTO `sys_role` VALUES (7, '2024-07-22 10:37:46', '2024-07-31 00:48:33', NULL, '1', '平台管理员', '普通管理员', 'i-me:superadmin', 1, 'PTADMIN');
INSERT INTO `sys_role` VALUES (8, '2024-07-22 16:08:12', '2024-07-23 01:07:39', NULL, NULL, '订单审核员', '进行订单审核的人员', 'i-me:role', 1, 'DDSHENHE');
INSERT INTO `sys_role` VALUES (9, '2024-07-23 01:42:20', '2024-07-25 23:07:43', NULL, '1', '系统功能测试员', '研发部测试人员0000', 'i-me:role', 1, 'TESTgcs');

-- ----------------------------
-- Table structure for sys_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources`  (
                                       `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                                       `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                       `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                       `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                                       `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                                       `role_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
                                       `resources_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源|权限 Id',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 414 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------
INSERT INTO `sys_role_resources` VALUES (66, '2024-07-23 01:07:39', '2024-07-23 01:07:39', NULL, NULL, '8', '2');
INSERT INTO `sys_role_resources` VALUES (67, '2024-07-23 01:07:39', '2024-07-23 01:07:39', NULL, NULL, '8', '1');
INSERT INTO `sys_role_resources` VALUES (68, '2024-07-23 01:07:39', '2024-07-23 01:07:39', NULL, NULL, '8', '4');
INSERT INTO `sys_role_resources` VALUES (69, '2024-07-23 01:07:39', '2024-07-23 01:07:39', NULL, NULL, '8', '13');
INSERT INTO `sys_role_resources` VALUES (97, '2024-07-24 10:49:06', '2024-07-24 10:49:06', '1', '1', '9', '2');
INSERT INTO `sys_role_resources` VALUES (98, '2024-07-24 14:43:07', '2024-07-24 14:43:07', '1', '1', '3', '13');
INSERT INTO `sys_role_resources` VALUES (99, '2024-07-24 14:43:07', '2024-07-24 14:43:07', '1', '1', '3', '9');
INSERT INTO `sys_role_resources` VALUES (100, '2024-07-24 14:43:07', '2024-07-24 14:43:07', '1', '1', '3', '8');
INSERT INTO `sys_role_resources` VALUES (101, '2024-07-24 14:43:07', '2024-07-24 14:43:07', '1', '1', '3', '3');
INSERT INTO `sys_role_resources` VALUES (102, '2024-07-24 14:43:07', '2024-07-24 14:43:07', '1', '1', '3', '1');
INSERT INTO `sys_role_resources` VALUES (388, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '6');
INSERT INTO `sys_role_resources` VALUES (389, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '32');
INSERT INTO `sys_role_resources` VALUES (390, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '13');
INSERT INTO `sys_role_resources` VALUES (391, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '26');
INSERT INTO `sys_role_resources` VALUES (392, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '25');
INSERT INTO `sys_role_resources` VALUES (393, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '24');
INSERT INTO `sys_role_resources` VALUES (394, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '8');
INSERT INTO `sys_role_resources` VALUES (395, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '29');
INSERT INTO `sys_role_resources` VALUES (396, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '39');
INSERT INTO `sys_role_resources` VALUES (397, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '2');
INSERT INTO `sys_role_resources` VALUES (398, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '37');
INSERT INTO `sys_role_resources` VALUES (399, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '38');
INSERT INTO `sys_role_resources` VALUES (400, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '36');
INSERT INTO `sys_role_resources` VALUES (401, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '35');
INSERT INTO `sys_role_resources` VALUES (402, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '27');
INSERT INTO `sys_role_resources` VALUES (403, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '3');
INSERT INTO `sys_role_resources` VALUES (404, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '1');
INSERT INTO `sys_role_resources` VALUES (405, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '30');
INSERT INTO `sys_role_resources` VALUES (406, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '28');
INSERT INTO `sys_role_resources` VALUES (407, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '4');
INSERT INTO `sys_role_resources` VALUES (408, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '31');
INSERT INTO `sys_role_resources` VALUES (409, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '33');
INSERT INTO `sys_role_resources` VALUES (410, '2024-07-31 00:48:33', '2024-07-31 00:48:33', '1', '1', '7', '34');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                             `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                             `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                             `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                             `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                             `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录账号',
                             `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `gender` tinyint(0) NULL DEFAULT NULL,
                             `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `last_login_time` datetime(0) NULL DEFAULT NULL,
                             `enable` tinyint(0) NULL DEFAULT NULL,
                             PRIMARY KEY (`id`, `account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '2024-07-15 21:46:49', '2024-07-28 15:42:58', NULL, '1', '宇宙无敌的高手555', 'admin', 'eb7bf5a85ac5de5de478ad57bda53bbd6f97233546e8c7e8f448e70cde94de54', '2ztkxntw', 1, '17685306042', 'https://avatars.githubusercontent.com/u/46741470?v=4&size=256', '超级管理员拥有系统全部权限', '2024-07-16 16:44:05', 0);
INSERT INTO `sys_user` VALUES (6, '2024-07-22 10:23:30', '2024-07-25 23:05:24', NULL, '1', '王老五', 'wlwu', 'eb7bf5a85ac5de5de478ad57bda53bbd6f97233546e8c7e8f448e70cde94de54', '2ztkxntw', 0, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (7, '2024-07-22 10:24:00', '2024-07-25 23:05:40', NULL, '1', '李四', '9她发给对方', 'eb7bf5a85ac5de5de478ad57bda53bbd6f97233546e8c7e8f448e70cde94de54', '2ztkxntw', 0, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (8, '2024-07-22 10:24:36', '2024-07-25 23:06:01', NULL, '1', 'java高手', 'jwgs12', 'eb7bf5a85ac5de5de478ad57bda53bbd6f97233546e8c7e8f448e70cde94de54', '2ztkxntw', 0, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (9, '2024-07-22 10:25:03', '2024-07-25 23:06:02', NULL, '1', '324', 'hy', 'eb7bf5a85ac5de5de478ad57bda53bbd6f97233546e8c7e8f448e70cde94de54', '2ztkxntw', 0, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (10, '2024-07-22 10:32:13', '2024-07-25 23:06:04', NULL, '1', 'HuangZy最帅', 'hyl8980', 'eb7bf5a85ac5de5de478ad57bda53bbd6f97233546e8c7e8f448e70cde94de54', '2ztkxntw', 0, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (11, '2024-07-22 10:40:39', '2024-07-25 23:06:05', NULL, '1', 'zy无敌帅', '23234230900', 'eb7bf5a85ac5de5de478ad57bda53bbd6f97233546e8c7e8f448e70cde94de54', '2ztkxntw', 1, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (12, '2024-07-22 11:39:00', '2024-07-25 23:06:06', NULL, '1', '韩信', 'hanxin', 'eb7bf5a85ac5de5de478ad57bda53bbd6f97233546e8c7e8f448e70cde94de54', '2ztkxntw', 0, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (13, '2024-07-22 11:42:05', '2024-07-25 23:06:07', NULL, '1', '赵云', 'zhaoyun', 'eb7bf5a85ac5de5de478ad57bda53bbd6f97233546e8c7e8f448e70cde94de54', '2ztkxntw', 1, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (14, '2024-07-22 11:43:37', '2024-07-25 23:06:08', NULL, '1', '赵信', 'zx121212', 'eb7bf5a85ac5de5de478ad57bda53bbd6f97233546e8c7e8f448e70cde94de54', '2ztkxntw', 1, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (15, '2024-07-22 14:56:42', '2024-07-25 23:06:17', NULL, '1', '王二小', 'wex', 'eb7bf5a85ac5de5de478ad57bda53bbd6f97233546e8c7e8f448e70cde94de54', '2ztkxntw', 1, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (16, '2024-07-23 19:13:25', '2024-07-25 23:06:15', NULL, '1', '德莱厄斯', 'delaies', 'eb7bf5a85ac5de5de478ad57bda53bbd6f97233546e8c7e8f448e70cde94de54', '2ztkxntw', 0, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (17, '2024-07-24 14:46:16', '2024-07-25 23:06:14', '1', '1', '起重机操作员2号', 'hzy0318', 'd61488d9883af097dfb0313c95082f91dbad8deaa8d1c60be1fda9cb2a677d45', 'q0lf117d', 0, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (18, '2024-07-25 11:01:24', '2024-07-25 23:06:13', '1', '1', '花木兰', 'huamul', '0971779f8e00c4b59d81097df6eadadcdb08f05fc36efd8d62eab3676eb5a8ff', '5t2tv3zj', 0, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (19, '2024-07-25 11:01:44', '2024-07-25 23:06:13', '1', '1', '悟空', 'wukong', '0415561d60d9cd991dd9c6317f2a67ec53576cd3e01bd51e877882c8c8105628', 'mbbj6j84', 0, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (20, '2024-07-25 11:05:25', '2024-07-25 11:10:42', '1', '1', '钟馗', 'zhongkui', '708dbac3c15824031d7fe1cfd5d15bdea9c359ba6d056d62e603e3a74ce37b45', '0ssyh8y8', 0, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (21, '2024-07-25 11:10:20', '2024-07-25 11:10:20', '1', '1', '李白', 'libai', 'c9feb63af3a94086a7b7d5ccfa828968f805e54a0458b341627e66aa8ebb256e', 'yv6vz1sm', 0, NULL, '/defaultAvatar.png', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (22, '2024-07-25 11:30:23', '2024-07-25 11:52:52', '1', '22', '普通管理员', 'ptadmin', 'd18c63a0924c11224b9dab0c012ffd23ec49d6c40e0d6ff40e0a33dc39681d65', 'jt7pmvh1', 0, '16687590876', '/defaultAvatar.png', '业务管理员', NULL, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                                  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                                  `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                                  `role_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
                                  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (14, '2024-07-22 10:32:13', '2024-07-22 10:32:13', NULL, NULL, '3', '10');
INSERT INTO `sys_user_role` VALUES (15, '2024-07-22 10:32:13', '2024-07-22 10:32:13', NULL, NULL, '2', '10');
INSERT INTO `sys_user_role` VALUES (18, '2024-07-22 11:42:05', '2024-07-22 11:42:05', NULL, NULL, '3', '13');
INSERT INTO `sys_user_role` VALUES (19, '2024-07-22 11:43:37', '2024-07-22 11:43:37', NULL, NULL, '3', '14');
INSERT INTO `sys_user_role` VALUES (22, '2024-07-22 16:23:41', '2024-07-22 16:23:41', NULL, NULL, '8', '6');
INSERT INTO `sys_user_role` VALUES (23, '2024-07-22 16:28:39', '2024-07-22 16:28:39', NULL, NULL, '8', '7');
INSERT INTO `sys_user_role` VALUES (24, '2024-07-22 16:28:39', '2024-07-22 16:28:39', NULL, NULL, '8', '8');
INSERT INTO `sys_user_role` VALUES (25, '2024-07-22 16:28:39', '2024-07-22 16:28:39', NULL, NULL, '8', '9');
INSERT INTO `sys_user_role` VALUES (26, '2024-07-22 16:28:39', '2024-07-22 16:28:39', NULL, NULL, '8', '10');
INSERT INTO `sys_user_role` VALUES (27, '2024-07-22 16:28:39', '2024-07-22 16:28:39', NULL, NULL, '8', '11');
INSERT INTO `sys_user_role` VALUES (28, '2024-07-22 16:28:39', '2024-07-22 16:28:39', NULL, NULL, '8', '12');
INSERT INTO `sys_user_role` VALUES (29, '2024-07-22 16:28:39', '2024-07-22 16:28:39', NULL, NULL, '8', '13');
INSERT INTO `sys_user_role` VALUES (30, '2024-07-22 16:28:39', '2024-07-22 16:28:39', NULL, NULL, '8', '14');
INSERT INTO `sys_user_role` VALUES (31, '2024-07-22 16:28:39', '2024-07-22 16:28:39', NULL, NULL, '8', '15');
INSERT INTO `sys_user_role` VALUES (34, '2024-07-23 19:13:25', '2024-07-23 19:13:25', NULL, NULL, '9', '16');
INSERT INTO `sys_user_role` VALUES (36, '2024-07-24 14:40:35', '2024-07-24 14:40:35', '1', '1', '1', '1');
INSERT INTO `sys_user_role` VALUES (38, '2024-07-24 14:46:37', '2024-07-24 14:46:37', '1', '1', '2', '17');
INSERT INTO `sys_user_role` VALUES (39, '2024-07-25 11:01:24', '2024-07-25 11:01:24', '1', '1', '3', '18');
INSERT INTO `sys_user_role` VALUES (40, '2024-07-25 11:01:44', '2024-07-25 11:01:44', '1', '1', '8', '19');
INSERT INTO `sys_user_role` VALUES (41, '2024-07-25 11:05:25', '2024-07-25 11:05:25', '1', '1', '9', '20');
INSERT INTO `sys_user_role` VALUES (42, '2024-07-25 11:10:21', '2024-07-25 11:10:21', '1', '1', '3', '21');
INSERT INTO `sys_user_role` VALUES (43, '2024-07-25 11:30:23', '2024-07-25 11:30:23', '1', '1', '7', '22');

-- ----------------------------
-- Table structure for sys_user_setting
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_setting`;
CREATE TABLE `sys_user_setting`  (
                                     `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                                     `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                     `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                     `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                                     `layout_mode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '布局方式',
                                     `theme` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主题',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户个人设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_setting
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
