-- Table structure for table `sys_masters`
DROP TABLE IF EXISTS `sys_masters`;
CREATE TABLE `sys_masters` (
  `id` BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
  `code` VARCHAR(128) NOT NULL COMMENT 'コード',
  `value` VARCHAR(128) NOT NULL COMMENT '値',
  `label` VARCHAR(1024) NOT NULL COMMENT '表示名',
  `remark` VARCHAR(1024) NOT NULL COMMENT '備考',
  PRIMARY KEY  (`id`)
) COMMENT='マスタ' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
