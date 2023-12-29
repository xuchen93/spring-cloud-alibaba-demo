```
-- db1
CREATE DATABASE `s1` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
CREATE TABLE `s1`.`seata_t1`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `var1` varchar(255) NULL,
  `int1` int(11) NULL,
  PRIMARY KEY (`id`)
);

-- db2
CREATE DATABASE `s2` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
CREATE TABLE `s2`.`seata_t1`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `var1` varchar(255) NULL,
  `int1` int(11) NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint(20) NOT NULL,
    `xid`           varchar(100) NOT NULL,
    `context`       varchar(128) NOT NULL,
    `rollback_info` longblob     NOT NULL,
    `log_status`    int(11) NOT NULL,
    `log_created`   datetime     NOT NULL,
    `log_modified`  datetime     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```
