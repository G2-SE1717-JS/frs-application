package com.frs.application.domain;

import com.frs.core.base.BaseDomain;
import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "save")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveRecipe extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private Long recipeId;

}