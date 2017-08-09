-- Table structure for table `sys_sequences`
DROP TABLE IF EXISTS `sys_sequences`;
CREATE TABLE `sys_sequences` (
  `seq_key` VARCHAR(128) NOT NULL COMMENT 'IDKEY',
  `seq_value` BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID值',
  PRIMARY KEY  (`seq_key`)
) COMMENT='ID序列' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
