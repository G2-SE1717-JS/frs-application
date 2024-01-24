package com.frs.application.controller;

import com.frs.application.payload.response.BlockAccountResponse;
import com.frs.application.service.IBlockAccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blockAccount")
@RequiredArgsConstructor
public class BlockAccountController {

    private final IBlockAccountService iBlockAccountService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<BlockAccountResponse>> getByAccountID(HttpServletRequest req) {
        return ResponseEntity.ok(iBlockAccountService.getByAccountID(req.getRemoteUser()));
    }

    @PostMapping("/{blockAccountId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<BlockAccountResponse> create(@PathVariable Long blockAccountId, HttpServletRequest req) {
        return ResponseEntity.ok(iBlockAccountService.create(blockAccountId, req.getRemoteUser()));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        iBlockAccountService.delete(id);
        return ResponseEntity.ok().build();
    }
}
