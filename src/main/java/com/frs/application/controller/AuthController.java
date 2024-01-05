package com.frs.application.controller;

import com.frs.application.payload.request.AccountSignInRequest;
import com.frs.application.payload.response.TokenResponse;
import com.frs.application.service.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid AccountSignInRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
