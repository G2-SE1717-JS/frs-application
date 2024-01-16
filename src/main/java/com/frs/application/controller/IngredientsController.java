package com.frs.application.controller;

import com.frs.application.payload.request.ingredients.IngredientsCreateRequest;
import com.frs.application.payload.request.ingredients.IngredientsUpdateRequest;
import com.frs.application.payload.response.IngredientsResponse;
import com.frs.application.service.IIngredientsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientsController {

    private final IIngredientsService iIngredientsService;

    @PostMapping
    public ResponseEntity<IngredientsResponse> create(@RequestBody @Valid IngredientsCreateRequest request) {
        return ResponseEntity.ok(iIngredientsService.create(request));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<IngredientsResponse> update(@PathVariable Long id, @RequestBody @Valid IngredientsUpdateRequest request) {
        return ResponseEntity.ok(iIngredientsService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        iIngredientsService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<IngredientsResponse>> getAll() {
        return ResponseEntity.ok(iIngredientsService.getAll());
    }
}
