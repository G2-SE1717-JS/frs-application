CREATE TABLE `step` (
                          `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
                          `recipe_id`          BIGINT       NOT NULL,
                          `order_value`        BIGINT       NOT NULL,
                          `description`        LONGTEXT,
                          `is_deleted`         BOOLEAN      NULL DEFAULT FALSE,
                          `created_date`       DATETIME     NOT NULL,
                          `last_modified_date` DATETIME     NOT NULL,
                          PRIMARY KEY (`id`)
);
CREATE TABLE `step_img` (
                        `id`                    BIGINT       NOT NULL AUTO_INCREMENT,
                        `step_id`               BIGINT       NOT NULL,
                        `image`                 LONGTEXT,
                        `is_deleted`            BOOLEAN      NULL DEFAULT FALSE,
                        `created_date`          DATETIME     NOT NULL,
                        `last_modified_date`    DATETIME     NOT NULL,
                        PRIMARY KEY (`id`)
);