CREATE TABLE `block_account` (
                               `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
                               `block_account_id`   BIGINT       NOT NULL,
                               `account_id`         BIGINT       NOT NULL,
                               `is_deleted`         BOOLEAN      NULL DEFAULT FALSE,
                               `created_date`       DATETIME     NOT NULL,
                               `last_modified_date` DATETIME     NOT NULL,
                               PRIMARY KEY (`id`)
);