package com.frs.application.dto;

import com.frs.application.constants.enums.RecipeStatus;
import com.frs.core.base.BaseDTO;
import com.google.api.client.util.DateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RecipeDTO extends BaseDTO {

    private Long accountId;
    private String title;
    private String description;
    private Long ration;
    private LocalTime cookingTime;
    private RecipeStatus status;
}
