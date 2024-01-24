package com.frs.application.controller;

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
@RequestMapping("/reportList")
@RequiredArgsConstructor
public class ReportController {

    private final IReportService iReportService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<ReportResponse>> getByAccountID(HttpServletRequest req) {
        return ResponseEntity.ok(iReportService.getByAccountID(req.getRemoteUser()));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReportResponse> create(@RequestBody @Valid ReportCreateRequest request, HttpServletRequest req) {
        return ResponseEntity.ok(iReportService.create(req.getRemoteUser(), request));
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReportResponse> update(@PathVariable Long id, @RequestBody String description){
        return ResponseEntity.ok(iReportService.update(id, description));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        iReportService.delete(id);
        return ResponseEntity.ok().build();
    }
}
