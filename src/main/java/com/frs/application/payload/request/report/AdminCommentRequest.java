package com.frs.application.payload.request.report;

import com.frs.application.constants.enums.ReportStatus;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AdminCommentRequest {
    private ReportStatus reportStatus;
    private String adminResponse;
}
