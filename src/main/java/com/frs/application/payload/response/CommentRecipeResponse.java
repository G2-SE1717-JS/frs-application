package com.frs.application.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentRecipeResponse {
    private Long id;
    private Long accountId;
    private Long recipeId;
    private Long parentId;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
