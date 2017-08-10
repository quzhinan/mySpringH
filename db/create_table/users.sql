DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (

  `id` BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
  `username` VARCHAR(128) NOT NULL COMMENT '用户名',
  `fullname` VARCHAR(128) NOT NULL COMMENT '用户昵称',
  `password` VARCHAR(64) NOT NULL COMMENT '密码（MD5加密）',
  `sex` INT COMMENT '性别：0男、1女',
  `age` INT COMMENT '年龄',
  `email` VARCHAR(128) NOT NULL COMMENT '邮箱',
  `power` INT NOT NULL COMMENT '权限',
  `login_lock_status` INT NOT NULL DEFAULT 0 COMMENT '登录状态：０未登录、１登录中', 
  `login_error_count` INT NOT NULL DEFAULT 0 COMMENT '登录错误次数', 
  `password_status` INT NOT NULL DEFAULT 0 COMMENT '密码状态：０初期値、１重新设定', 
  `delete_flag` INT NOT NULL DEFAULT 0 COMMENT '删除状态：０未删除、１已删除',
  `update_datetime` TIMESTAMP NOT NULL default '1971-01-01 00:00:00' COMMENT '更新时间',
  `create_datetime` TIMESTAMP NOT NULL default '1971-01-01 00:00:00' COMMENT '登录时间',
  
  PRIMARY KEY  (`id`), 
  UNIQUE INDEX `username_unique` (`username` ASC),
  UNIQUE INDEX `email_unique` (`email`)
) COMMENT='用户信息' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
