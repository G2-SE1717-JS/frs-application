CREATE TABLE `verification`
(
    `id`                 BIGINT   NOT NULL AUTO_INCREMENT,
    `token`              LONGTEXT NOT NULL,
    `email`              LONGTEXT,
    `expired_time`       DATETIME NOT NULL,
    `is_deleted`         BOOLEAN NULL DEFAULT FALSE,
    `created_date`       DATETIME NOT NULL,
    `last_modified_date` DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);