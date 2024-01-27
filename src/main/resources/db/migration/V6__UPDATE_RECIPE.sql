CREATE TABLE `recipe` (
                               `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
                               `account_id`         BIGINT       NOT NULL,
                               `title`              LONGTEXT,
                               `description`        LONGTEXT,
                               `ration`             BIGINT       NOT NULL,
                               `cooking_time`       TIME         NOT NULL,
                               `is_deleted`         BOOLEAN      NULL DEFAULT FALSE,
                               `created_date`       DATETIME     NOT NULL,
                               `last_modified_date` DATETIME     NOT NULL,
                               PRIMARY KEY (`id`)
);

CREATE TABLE `recipe_img` (
                              `id`                    BIGINT       NOT NULL AUTO_INCREMENT,
                              `recipe_id`             BIGINT       NOT NULL,
                              `image`                 LONGTEXT,
                              `is_deleted`            BOOLEAN      NULL DEFAULT FALSE,
                              `created_date`          DATETIME     NOT NULL,
                              `last_modified_date`    DATETIME     NOT NULL,
                              PRIMARY KEY (`id`)
);