package com.frs.application.payload.request.step;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StepCreateRequest {

    private Long orderValue;
    private String description;
    private List<String> images;
}
