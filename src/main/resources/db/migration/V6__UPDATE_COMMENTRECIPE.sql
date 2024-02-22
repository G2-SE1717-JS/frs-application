CREATE TABLE `comment_recipe` (
                        `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
                        `recipe_id`          BIGINT       NOT NULL,
                        `account_id`         BIGINT       NOT NULL,
                        `parent_id`          BIGINT       NOT NULL,
                        `description`        LONGTEXT     NOT NULL,
                        `is_deleted`         BOOLEAN      NULL DEFAULT FALSE,
                        `created_date`       DATETIME     NOT NULL,
                        `last_modified_date` DATETIME     NOT NULL,
                        PRIMARY KEY (`id`)
);