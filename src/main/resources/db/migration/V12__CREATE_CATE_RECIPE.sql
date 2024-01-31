CREATE TABLE `cate_recipe` (
                               `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
                               `recipe_id`          BIGINT       NOT NULL ,
                               `tool_id`            BIGINT       NOT NULL,
                               `is_deleted`         BOOLEAN      NULL DEFAULT FALSE,
                               `created_date`       DATETIME     NOT NULL,
                               `last_modified_date` DATETIME     NOT NULL,
                               PRIMARY KEY (`id`)
);