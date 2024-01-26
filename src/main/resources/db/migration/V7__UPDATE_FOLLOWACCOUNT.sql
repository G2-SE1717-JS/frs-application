CREATE TABLE `follow_account` (
                               `id`                     BIGINT      NOT NULL AUTO_INCREMENT,
                               `account_id`             BIGINT      NOT NULL,
                               `followed_account_id`    BIGINT      NOT NULL,
                               `is_deleted`             BOOLEAN     NULL DEFAULT FALSE,
                               `created_date`           DATETIME    NOT NULL,
                               `last_modified_date`     DATETIME    NOT NULL,
                               PRIMARY KEY (`id`)
);