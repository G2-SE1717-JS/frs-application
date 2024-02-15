package com.frs.application.controller;

import com.frs.application.payload.request.admindashboard.CountNewRecipesGetRequest;
import com.frs.application.payload.response.CountfNewRecipesResponse;
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

    @GetMapping("/recipes/count-by-day")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CountfNewRecipesResponse>> getNumberOfRecipesByDay(@RequestBody @Valid CountNewRecipesGetRequest request) {
        return ResponseEntity.ok(adminDashboardService.getNumberOfNewRecipesByDay(request));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> countAllRecipes(){
        return ResponseEntity.ok(adminDashboardService.countAllRecipes());
    }
}
