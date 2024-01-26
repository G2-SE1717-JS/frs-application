package com.frs.application.service;

import com.frs.application.payload.request.report.ReportCreateRequest;
import com.frs.application.payload.request.report.AdminCommentRequest;
import com.frs.application.payload.response.report.AdminReportResponse;
import com.frs.application.payload.response.report.ReportResponse;

import java.util.List;

public interface IReportService {
    List<ReportResponse> getByAccountID(String remoteUser);

    ReportResponse create(String remoteUser, ReportCreateRequest request);

    ReportResponse update(Long id, String description);
    void delete(Long id);
    List<AdminReportResponse> getAllReportByAdmin();
    AdminReportResponse updateComment(Long reportId, AdminCommentRequest request);

}
