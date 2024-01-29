package com.frs.application.controller;

import com.frs.application.payload.request.report.AdminCommentRequest;
import com.frs.application.payload.request.report.ReportCreateRequest;
import com.frs.application.payload.response.ReportResponse;
import com.frs.application.service.IReportService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReportResponse> update(@PathVariable Long id, @RequestBody String description) {
        return ResponseEntity.ok(reportService.update(id, description));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reportService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("response/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ReportResponse> addComment(@PathVariable Long id, @RequestBody AdminCommentRequest request) {
        return ResponseEntity.ok(reportService.addComment(id, request));
    }


}
