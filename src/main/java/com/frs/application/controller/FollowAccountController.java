package com.frs.application.controller;

import com.frs.application.payload.request.followaccount.FollowAccountRequest;
import com.frs.application.payload.response.FollowAccountResponse;
import com.frs.application.service.IFollowAccountService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow-account")
@RequiredArgsConstructor
public class FollowAccountController {
    private final IFollowAccountService followAccountService;

    @PostMapping("follow")
    public ResponseEntity<FollowAccountResponse> create(@RequestBody FollowAccountRequest request, HttpServletRequest req) {
        return ResponseEntity.ok(followAccountService.create(req.getRemoteUser(), request));
    }

    @DeleteMapping("unfollow")
    public ResponseEntity<Void> delete(@RequestBody FollowAccountRequest request, HttpServletRequest req) {
        followAccountService.delete(req.getRemoteUser(), request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FollowAccountResponse>> getAll(HttpServletRequest req) {
        return ResponseEntity.ok(followAccountService.getAll(req.getRemoteUser()));
    }
}
