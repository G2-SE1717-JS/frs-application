package com.frs.application.payload.request.comments;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentRecipeCreateRequest {
    private Long recipeId;
    private Long parentId;
    private String description;

}
