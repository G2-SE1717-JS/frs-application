package com.frs.application.payload.request.report;

import com.frs.application.constants.enums.ReportStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class AdminCommentRequest {
    private Long recipeId;
    private ReportStatus status;
    private String adminResponse;
    private LocalDateTime adminResponseDate;
}
