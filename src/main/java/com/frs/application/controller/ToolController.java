package com.frs.application.controller;

import com.frs.application.payload.request.tool.ToolCreateRequest;
import com.frs.application.payload.request.tool.ToolUpdateRequest;
import com.frs.application.payload.response.ToolResponse;
import com.frs.application.service.IToolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping ("/tool")
@RequiredArgsConstructor
public class ToolController {
    private final IToolService iToolService;
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ToolResponse> create(@RequestBody @Valid ToolCreateRequest request){
        return ResponseEntity.ok(iToolService.create(request));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ToolResponse> update(@PathVariable Long id, @RequestBody @Valid ToolUpdateRequest request){
        return ResponseEntity.ok(iToolService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        iToolService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping

    public ResponseEntity<List<ToolResponse>> getAll() {
        return ResponseEntity.ok(iToolService.getAll());
    }

}
