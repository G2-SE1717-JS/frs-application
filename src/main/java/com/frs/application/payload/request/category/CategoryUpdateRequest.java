package com.frs.application.payload.request.category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryUpdateRequest {
    private String name;
    private String image;
}
