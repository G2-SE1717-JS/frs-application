package com.frs.application.service.impl;

import com.frs.application.dto.AccountDTO;
import com.frs.application.dto.TokenDTO;
import com.frs.application.logic.IAccountLogic;
import com.frs.application.logic.ITokenLogic;
import com.frs.application.payload.request.AccountSignInRequest;
import com.frs.application.payload.request.TokenRefreshRequest;
import com.frs.application.payload.response.TokenResponse;
import com.frs.application.securiry.JwtProviderImpl;
import com.frs.application.service.IAuthService;
import com.frs.core.constants.enums.UserRole;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements IAuthService {
    private final IAccountLogic accountLogic;
    private final JwtProviderImpl tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final ITokenLogic tokenLogic;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TokenResponse login(AccountSignInRequest loginRequest) {
        if (isEmail(loginRequest.getIdentifier())) {
            AccountDTO accountDTO = accountLogic.findByEmail(loginRequest.getIdentifier());
            loginRequest.setIdentifier(accountDTO != null ? accountDTO.getUsername() : null);
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getIdentifier(), loginRequest.getPassword())
            );

            final var accessToken = tokenProvider.createToken(loginRequest.getIdentifier(),
                    UserRole.valueOf(authentication.getAuthorities().iterator().next().getAuthority()));
            final var refreshToken = UUID.randomUUID().toString();
            tokenLogic.save(TokenDTO.builder()
                    .username(authentication.getName())
                    .refreshToken(refreshToken)
                    .expiresAt(tokenProvider.getExpireTime())
                    .build());
            return new TokenResponse(accessToken, refreshToken);
        } catch (Exception e) {
            log.error("{} - {}", e.getClass().getSimpleName(), e.getMessage());
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.account.account_not_found"));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TokenResponse refresh(TokenRefreshRequest refreshRequest) {
        TokenDTO tokenDTO = tokenLogic.findByToken(refreshRequest.getToken());
        if (Objects.isNull(tokenDTO) || tokenProvider.isTimeRefreshTokenExpired(tokenDTO.getExpiresAt())) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.account.token_not_found"));
        }

        var accountDTO = accountLogic.findByUsername(tokenDTO.getUsername());

        final var accessToken = tokenProvider.createToken(tokenDTO.getUsername(), accountDTO.getRole());
        return new TokenResponse(accessToken, refreshRequest.getToken());
    }

    private boolean isEmail(String identifier) {
        return identifier.contains("@");
    }
}
