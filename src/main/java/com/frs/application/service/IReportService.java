package com.frs.application.service;

import com.frs.application.payload.request.report.AdminCommentRequest;
import com.frs.application.payload.response.report.AdminReportResponse;

import java.util.List;

public interface IReportService {
    List<AdminReportResponse> getAllReportByAdmin();

    AdminReportResponse updateComment(Long reportId, AdminCommentRequest request);

}
