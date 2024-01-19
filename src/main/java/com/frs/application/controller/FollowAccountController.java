package com.frs.application.controller;

import com.frs.application.payload.response.FollowAccountResponse;
import com.frs.application.service.IFollowAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/follow-account")
@RequiredArgsConstructor
public class FollowAccountController {
    private final IFollowAccountService followAccountService;

    @PostMapping("follow/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<FollowAccountResponse> create(@PathVariable Long id) {
        return ResponseEntity.ok(followAccountService.create(id));
    }

    @DeleteMapping("unfollow/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        followAccountService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<FollowAccountResponse>> getAll() {
        return ResponseEntity.ok(followAccountService.getAll());
    }
}
