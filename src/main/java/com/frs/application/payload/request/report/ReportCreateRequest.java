package com.frs.application.payload.request.report;
import com.frs.application.constants.enums.ReportStatus;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ReportCreateRequest {
    private Long recipeId;
    private String description;

}