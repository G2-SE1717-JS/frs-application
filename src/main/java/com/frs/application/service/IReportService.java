package com.frs.application.service;

import com.frs.application.payload.request.report.ReportCreateRequest;
import com.frs.application.payload.request.report.ReportCreateRequest;
import com.frs.application.payload.request.report.AdminCommentRequest;
import com.frs.application.payload.request.report.ReportUpdateRequest;
import com.frs.application.payload.response.ReportResponse;

import java.util.List;

public interface IReportService {

    ReportResponse create(String remoteUser, ReportCreateRequest request);

    ReportResponse updateAndAddComment(Long id, ReportUpdateRequest request);

    List<ReportResponse> getAllReport(String remoteUser);

    void delete(Long id);
}
