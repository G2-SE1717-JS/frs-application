CREATE TABLE `report` (
                                  `id`                     BIGINT       NOT NULL AUTO_INCREMENT,
                                  `account_id`             BIGINT       NOT NULL,
                                  `recipe_id`              BIGINT       NOT NULL,
                                  `description`            LONGTEXT     NOT NULL,
                                  `is_deleted`             BOOLEAN      NULL DEFAULT FALSE,
                                  `created_date`           DATETIME     NOT NULL,
                                  `last_modified_date`     DATETIME     NOT NULL,
                                  `report_status`          VARCHAR(150) NOT NULL,
                                  `admin_response`         LONGTEXT     NULL,
                                  `admin_response_date`    DATETIME     NULL,
                                  PRIMARY KEY (`id`)
);