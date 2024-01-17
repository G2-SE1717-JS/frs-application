package com.frs.application.payload.request.tool;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToolCreateRequest {
    private String name;
    private String image;

}
