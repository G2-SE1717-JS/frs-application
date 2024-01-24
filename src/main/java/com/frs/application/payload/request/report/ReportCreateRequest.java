package com.frs.application.payload.request.report;
import com.frs.core.constants.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ReportCreateRequest {
    private Long recipeId;
    private String description;

}

