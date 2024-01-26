package com.frs.application.payload.request.tool;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToolUpdateRequest {
    private String name;
    private String image;
}
