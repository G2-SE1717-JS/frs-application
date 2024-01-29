package com.frs.application.controller;

import com.frs.application.payload.request.report.AdminCommentRequest;
import com.frs.application.payload.request.report.ReportCreateRequest;
import com.frs.application.payload.response.report.AdminReportResponse;
import com.frs.application.payload.response.report.ReportResponse;
import com.frs.application.service.IReportService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
    private final IReportService reportService;

    @GetMapping("user/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<ReportResponse>> getByAccountID(HttpServletRequest req) {
        return ResponseEntity.ok(reportService.getByAccountID(req.getRemoteUser()));
    }

    @PostMapping("user/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReportResponse> create(@RequestBody @Valid ReportCreateRequest request, HttpServletRequest req) {
        return ResponseEntity.ok(reportService.create(req.getRemoteUser(), request));
    }

    @PostMapping("user/update/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReportResponse> update(@PathVariable Long id, @RequestBody String description) {
        return ResponseEntity.ok(reportService.update(id, description));
    }

    @DeleteMapping("user/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reportService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("admin/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AdminReportResponse>> getAllByAdmin() {
        return ResponseEntity.ok(reportService.getAllReportByAdmin());
    }

    @PutMapping("admin/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AdminReportResponse> updateComment(@PathVariable Long id, @RequestBody AdminCommentRequest request) {
        return ResponseEntity.ok(reportService.updateComment(id, request));
    }


}
