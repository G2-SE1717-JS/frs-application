CREATE TABLE `followAccount` (
                               `id`                 BIGINT      NOT NULL AUTO_INCREMENT,
                               `accountID`          BIGINT      NOT NULL,
                               `followedAccountID`  BIGINT      NOT NULL,
                               `is_deleted`         BOOLEAN     NULL DEFAULT FALSE,
                               `created_date`       DATETIME    NOT NULL,
                               `last_modified_date` DATETIME    NOT NULL,
                               PRIMARY KEY (`id`)
);