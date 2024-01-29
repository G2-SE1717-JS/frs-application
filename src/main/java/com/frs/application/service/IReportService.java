package com.frs.application.service;

import com.frs.application.payload.request.report.AdminCommentRequest;
import com.frs.application.payload.request.report.ReportCreateRequest;
import com.frs.application.payload.response.ReportResponse;

import java.util.List;

public interface IReportService {
    ReportResponse create(String remoteUser, ReportCreateRequest request);

    ReportResponse update(Long id, String description);

    List<ReportResponse> getAllReport(String remoteUser);

    ReportResponse addComment(Long reportId, AdminCommentRequest request);

    void delete(Long id);
}
