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
@RequestMapping("/block-account")
@RequiredArgsConstructor
public class BlockAccountController {

    private final IBlockAccountService blockAccountService;

    @GetMapping
    public ResponseEntity<List<BlockAccountResponse>> getByAccountID(HttpServletRequest req) {
        return ResponseEntity.ok(blockAccountService.getByAccountID(req.getRemoteUser()));
    }

    @PostMapping("/{id}")
    public ResponseEntity<BlockAccountResponse> create(@PathVariable Long id, HttpServletRequest req) {
        return ResponseEntity.ok(blockAccountService.create(id, req.getRemoteUser()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        blockAccountService.delete(id);
        return ResponseEntity.ok().build();
    }
}
