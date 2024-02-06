package com.frs.application.controller;

import com.frs.application.payload.request.admindashboard.CountRecipesGetRequest;
import com.frs.application.payload.response.CountRecipesResponse;
import com.frs.application.service.IAdminDashboardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final IAdminDashboardService adminDashboardService;

    @GetMapping("/total-recipes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CountRecipesResponse>> getNewRecipesByDay(@RequestBody @Valid CountRecipesGetRequest request) {
        return ResponseEntity.ok(adminDashboardService.countRecipesByDay(request));
    }

}
