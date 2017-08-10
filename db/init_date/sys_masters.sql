-- Init data for table `sys_masters`
TRUNCATE TABLE `sys_masters`;

INSERT INTO `sys_masters`(`id`, `code`, `value`, `label`, `remark`) 
	VALUES('1', 'user.sex.status', '0', 'user.sex.male', '性别：男');

INSERT INTO `sys_masters`(`id`, `code`, `value`, `label`, `remark`) 
	VALUES('2', 'user.sex.status', '1', 'user.sex.female', '性别：女');