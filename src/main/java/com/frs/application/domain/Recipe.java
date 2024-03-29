package com.frs.application.domain;

import com.frs.application.constants.enums.RecipeStatus;
import com.frs.core.base.BaseDomain;
import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "recipe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private String title;
    private String description;
    private Long ration;
    private LocalTime cookingTime;
    @Enumerated(EnumType.STRING)
    private RecipeStatus status;

}
