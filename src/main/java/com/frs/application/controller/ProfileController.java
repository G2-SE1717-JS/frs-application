package com.frs.application.controller;

import com.frs.application.payload.request.userprofile.ProfileCreateRequest;
import com.frs.application.payload.request.userprofile.ProfileUpdateRequest;
import com.frs.application.payload.response.UserProfileResponse;
import com.frs.application.service.IUserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-profile")
@RequiredArgsConstructor
public class ProfileController {

    private final IUserProfileService iUserProfileService;

    @PostMapping
    public ResponseEntity<UserProfileResponse> create(@RequestBody @Valid ProfileCreateRequest request) {
        return ResponseEntity.ok(iUserProfileService.create(request));
    }

    @PutMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserProfileResponse> update(HttpServletRequest req, @RequestBody @Valid ProfileUpdateRequest request) {
        return ResponseEntity.ok(iUserProfileService.update(req.getRemoteUser(), request));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> delete(HttpServletRequest req) {
        iUserProfileService.delete(req.getRemoteUser());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/id")
    public ResponseEntity<UserProfileResponse> getAnUser(@PathVariable Long id) {
        return ResponseEntity.ok(iUserProfileService.getById(id));
    }
}
