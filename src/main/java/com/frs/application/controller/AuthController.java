package com.frs.application.controller;

import com.frs.application.payload.request.AccountSignInRequest;
import com.frs.application.payload.request.TokenRefreshRequest;
import com.frs.application.payload.response.TokenResponse;
import com.frs.application.payload.response.UserProfileResponse;
import com.frs.application.service.IAuthService;
import com.frs.application.service.IUserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;

    private final IUserProfileService iUserProfileService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid AccountSignInRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody TokenRefreshRequest refreshRequest) {
        return ResponseEntity.ok(authService.refresh(refreshRequest));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> refresh(HttpServletRequest req) {
        return ResponseEntity.ok(iUserProfileService.getMe(req.getRemoteUser()));
    }

}
