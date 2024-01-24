package com.frs.application.controller;

import com.frs.application.payload.request.report.AdminCommentRequest;
import com.frs.application.payload.response.report.AdminReportResponse;
import com.frs.application.service.IReportService;
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

    @GetMapping("all-by-admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AdminReportResponse>> getAllByAdmin() {
        return ResponseEntity.ok(reportService.getAllReportByAdmin());
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AdminReportResponse> updateComment(@PathVariable Long id, @RequestBody AdminCommentRequest request) {
        return ResponseEntity.ok(reportService.updateComment(id, request));
    }


}
