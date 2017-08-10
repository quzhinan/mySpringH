-- Table structure for table `sys_masters`
DROP TABLE IF EXISTS `sys_masters`;
CREATE TABLE `sys_masters` (
  `id` BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
  `code` VARCHAR(128) NOT NULL COMMENT '关联值',
  `value` VARCHAR(128) NOT NULL COMMENT '值',
  `label` VARCHAR(1024) NOT NULL COMMENT '显示名称',
  `remark` VARCHAR(1024) NOT NULL COMMENT '详细',
  PRIMARY KEY  (`id`)
) COMMENT='基本定义' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
