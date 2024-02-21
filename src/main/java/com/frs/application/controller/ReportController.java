package com.frs.application.controller;

import com.frs.application.payload.request.report.ReportCreateRequest;
import com.frs.application.payload.request.report.AdminCommentRequest;
import com.frs.application.payload.request.report.ReportCreateRequest;
import com.frs.application.payload.request.report.ReportUpdateRequest;
import com.frs.application.payload.response.ReportResponse;
import com.frs.application.service.IReportService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import com.frs.application.payload.request.report.AdminCommentRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final IReportService reportService;

    @GetMapping
    public ResponseEntity<List<ReportResponse>> getAllReport(HttpServletRequest req) {
        return ResponseEntity.ok(reportService.getAllReport(req.getRemoteUser()));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReportResponse> create(HttpServletRequest req, @RequestBody @Valid ReportCreateRequest request) {
        return ResponseEntity.ok(reportService.create(req.getRemoteUser(), request));
    }

    @PutMapping("{id}")
    public ResponseEntity<ReportResponse> update(@PathVariable Long id, @RequestBody @Valid ReportUpdateRequest request, HttpServletRequest req) {
        return ResponseEntity.ok(reportService.updateAndAddComment(id, request, req.getRemoteUser()));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reportService.delete(id);
        return ResponseEntity.ok().build();
    }
}
