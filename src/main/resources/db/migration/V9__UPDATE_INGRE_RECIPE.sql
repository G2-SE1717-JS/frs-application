CREATE TABLE `ingre_recipe` (
                               `ingre_id`           BIGINT       NOT NULL ,
                               `recipe_id`          BIGINT       NOT NULL,
                               PRIMARY KEY (`ingre_id`, `recipe_id`)
);