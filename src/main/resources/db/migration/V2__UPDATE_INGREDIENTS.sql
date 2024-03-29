CREATE TABLE `ingredients` (
                               `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
                               `name`               VARCHAR(350) NOT NULL,
                               `image`              LONGTEXT,
                               `is_deleted`         BOOLEAN      NULL DEFAULT FALSE,
                               `created_date`       DATETIME     NOT NULL,
                               `last_modified_date` DATETIME     NOT NULL,
                               PRIMARY KEY (`id`)
);