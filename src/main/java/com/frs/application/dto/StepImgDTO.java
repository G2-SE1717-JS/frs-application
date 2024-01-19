package com.frs.application.dto;

import com.frs.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class StepImgDTO extends BaseDTO {
    private Long stepId;
    private String image;
}
