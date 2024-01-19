package com.frs.application.domain;

import com.frs.core.base.BaseDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment_recipe")
public class CommentRecipe extends BaseDomain {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private Long recipeId;
    private Long accountId;
    private Long parentId;
    private String description;
}
