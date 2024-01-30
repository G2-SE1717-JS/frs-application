package com.frs.application.payload.request.report;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportUpdateRequest {
    private String description;
    private AdminCommentRequest adminCommentRequest;
}
