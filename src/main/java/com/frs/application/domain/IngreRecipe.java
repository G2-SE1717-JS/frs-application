package com.frs.application.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "ingre_recipe")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class IngreRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipe_id;
    private Long ingre_id;
}
