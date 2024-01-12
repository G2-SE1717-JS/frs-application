CREATE TABLE `accounts`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT,
    `username`    VARCHAR(150) NOT NULL,
    `email`       VARCHAR(150) NOT NULL,
    `password`    VARCHAR(450) NOT NULL,
    `verified`    BOOLEAN      NULL DEFAULT FALSE,
    `status`      BOOLEAN      NULL DEFAULT TRUE,
    `role`        VARCHAR(150) NOT NULL,
    `version`     BIGINT       NULL DEFAULT 0,
    `is_deleted`  BOOLEAN      NULL DEFAULT FALSE,
    `created_date`  DATETIME     NOT NULL,
    `last_modified_date` DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `tokens`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL UNIQUE,
    `refresh_token` VARCHAR(255) NOT NULL UNIQUE,
    `expires_at` DATETIME NOT NULL,
    `version`     BIGINT       NULL DEFAULT 0,
    `is_deleted`  BOOLEAN      NULL DEFAULT FALSE,
    `created_date`  DATETIME     NOT NULL,
    `last_modified_date` DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `accounts` (`username`, `email`, `password`, `verified`, `status`, `role`, `version`, `is_deleted`,
                        `created_date`, `last_modified_date`)
VALUES ('system_admin', 'system_admin@example.com', '$2a$12$lQ8HumOLCiTeWTmHurJgVONPS9e9bskBNldFL8LzeH14OJtjd0HQu',
        TRUE, TRUE, 'ROLE_ADMIN', 0, FALSE, NOW(), NOW());