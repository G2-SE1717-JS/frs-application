package com.frs.application.controller;

import com.frs.application.payload.request.report.ReportCreateRequest;
import com.frs.application.service.IReportService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import com.frs.application.payload.request.report.AdminCommentRequest;
import com.frs.application.payload.response.report.AdminReportResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final IReportService iReportService;

    @GetMapping("user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<ReportResponse>> getByAccountID(HttpServletRequest req) {
        return ResponseEntity.ok(iReportService.getByAccountID(req.getRemoteUser()));
    }

    @PostMapping("user/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReportResponse> create(@RequestBody @Valid ReportCreateRequest request, HttpServletRequest req) {
        return ResponseEntity.ok(iReportService.create(req.getRemoteUser(), request));
    }

    @PostMapping("user/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReportResponse> update(@PathVariable Long id, @RequestBody String description) {
        return ResponseEntity.ok(iReportService.update(id, description));
    }

    @DeleteMapping("user/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        iReportService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("admin/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AdminReportResponse>> getAllByAdmin() {
        return ResponseEntity.ok(iReportService.getAllReportByAdmin());
    }

    @PutMapping("admin/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AdminReportResponse> updateComment(@PathVariable Long id, @RequestBody AdminCommentRequest request) {
        return ResponseEntity.ok(iReportService.updateComment(id, request));
    }
}
