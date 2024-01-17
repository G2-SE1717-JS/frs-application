package com.frs.application.dto;

import com.frs.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentRecipeDTO extends BaseDTO {
    private Long recipeId;
    private Long accountId;
    private Long parentId;
    private String description;
}
