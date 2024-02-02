CREATE TABLE `user_profile` (
                          `id`                     BIGINT       NOT NULL AUTO_INCREMENT,
                          `account_id`             BIGINT       NOT NULL,
                          `full_name`              LONGTEXT     ,
                          `biography`              LONGTEXT     ,
                          `origin`                 LONGTEXT     ,
                          `profile_picture`        LONGTEXT     ,
                          `is_deleted`             BOOLEAN      NULL DEFAULT FALSE,
                          `created_date`           DATETIME     NOT NULL,
                          `last_modified_date`     DATETIME     NOT NULL,
                          PRIMARY KEY (`id`)
);