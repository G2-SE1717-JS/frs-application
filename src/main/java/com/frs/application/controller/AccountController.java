package com.frs.application.controller;

import com.frs.application.payload.request.AccountCreateRequest;
import com.frs.application.payload.request.AccountUpdateRequest;
import com.frs.application.payload.response.AccountResponse;
import com.frs.application.service.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final IAccountService accountService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AccountResponse> create(@RequestBody @Valid AccountCreateRequest request) {
        return ResponseEntity.ok(accountService.create(request));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AccountResponse> update(@PathVariable Long id, @RequestBody @Valid AccountUpdateRequest request) {
        return ResponseEntity.ok(accountService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AccountResponse>>getAll() {
            return ResponseEntity.ok(accountService.getAll());
        }

    @GetMapping("/latestUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AccountResponse>>findByCreatedDateBetween(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(accountService.findByCreatedDateBetween(startDate, endDate));
    }
}
