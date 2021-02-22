CREATE TABLE `user`
(
    `tid`        bigint                                  NOT NULL AUTO_INCREMENT COMMENT 'Table ID',
    `full_name`  varchar(64) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `nick_name`  varchar(64) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `login_name` char(16) COLLATE utf8mb4_general_ci     NOT NULL,
    `gender`     char(1) COLLATE utf8mb4_general_ci                           DEFAULT NULL COMMENT 'M/F',
    `email`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `password`   varchar(128) COLLATE utf8mb4_general_ci NOT NULL,
    `salt`       varchar(128) COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`tid`),
    UNIQUE KEY `unique_email` (`email`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 101
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `article`
(
    `tid`           bigint                                  NOT NULL,
    `short_link`    char(32) COLLATE utf8mb4_general_ci     NOT NULL COMMENT '短链接',
    `human_link`    varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '易读链接',
    `title`         varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
    `short_content` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文章预览',
    `full_content`  text COLLATE utf8mb4_general_ci         NOT NULL COMMENT '文章内容',
    `update_time`   datetime                                NOT NULL COMMENT '更新时间',
    `create_time`   datetime                                NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`tid`),
    UNIQUE KEY `idx_short_link` (`short_link`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;