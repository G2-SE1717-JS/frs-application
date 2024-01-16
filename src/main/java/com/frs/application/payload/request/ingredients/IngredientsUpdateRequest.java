package com.frs.application.payload.request.ingredients;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredientsUpdateRequest {
    private String name;
    private String image;
}
