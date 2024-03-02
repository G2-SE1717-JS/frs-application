package com.frs.application.service.impl;

import com.frs.application.domain.mail.Email;
import com.frs.application.domain.mail.Mailer;
import com.frs.application.dto.AccountDTO;
import com.frs.application.dto.TokenDTO;
import com.frs.application.dto.VerificationDTO;
import com.frs.application.logic.IAccountLogic;
import com.frs.application.logic.ITokenLogic;
import com.frs.application.logic.VerificationLogic;
import com.frs.application.payload.request.AccountSignInRequest;
import com.frs.application.payload.request.RegisterRequest;
import com.frs.application.payload.request.TokenRefreshRequest;
import com.frs.application.payload.response.TokenResponse;
import com.frs.application.securiry.JwtProviderImpl;
import com.frs.application.service.IAuthService;
import com.frs.application.service.MailService;
import com.frs.application.service.ThymeleafService;
import com.frs.application.util.MD5Util;
import com.frs.core.constants.enums.UserRole;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
    private final PasswordEncoder passwordEncoder;
    private final VerificationLogic verificationLogic;
    private final MailService mailService;
    private final ThymeleafService thymeleafService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public TokenResponse login(AccountSignInRequest loginRequest) {
        if (isEmail(loginRequest.getIdentifier())) {//check if email mà người dùng nhập vào có tồn tại trong db hay không
            AccountDTO accountDTO = accountLogic.findByEmail(loginRequest.getIdentifier());//nếu có thì lấy ra accountDTO
            loginRequest.setIdentifier(accountDTO != null ? accountDTO.getUsername() : null);//nếu accountDTO khác null thì set lại username cho loginRequest
        }

        try {
            Authentication authentication = authenticationManager.authenticate(//kiểm tra xem username và password có đúng hay không
                    new UsernamePasswordAuthenticationToken(loginRequest.getIdentifier(), loginRequest.getPassword())//nếu đúng thì trả về authentication
            );

            final var accessToken = tokenProvider.createToken(loginRequest.getIdentifier(),//tạo token
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

    @Override
    public void register(RegisterRequest registerRequest) {
        //kiểm tra xem email đã tồn tại trong db chưa
        if(accountLogic.findByEmail(registerRequest.getEmail()) != null) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.account.email_already_exists"));
        }
        //kiểm tra xem password và rePassword có giống nhau không
        AccountDTO accountDTO = AccountDTO.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .username(registerRequest.getEmail().split("@")[0])
                .role(UserRole.ROLE_USER)
                .status(true)
                .verified(false)
                .build();
        //lưu accountDTO vào db
        accountDTO = accountLogic.save(accountDTO);
        //gửi email xác nhận
        String token = MD5Util.encodeToMD5(accountDTO.getEmail() + LocalDateTime.now());
        VerificationDTO verificationDTO = VerificationDTO.builder()
                .email(accountDTO.getEmail())
                .token(token)
                .expiredTime(LocalDateTime.now().plusMinutes(30))
                .build();
        verificationDTO = verificationLogic.save(verificationDTO);
        //tao url
        String url = String.format("%s%s?token=%s","localhost:8080", "/verify", token);

        Map<String, Object> variables = Map.of("url", url);
        try {
            mailService.sendHtml(Email.builder()
                    .to(List.of(Mailer.builder()
                            .email(accountDTO.getEmail())
                            .name(accountDTO.getUsername())
                            .build()))
                    .subject("Verify your email")
                    .htmlPart(thymeleafService.renderHtmlMail(variables, "register"))
                    .build()
            );
        } catch (UnirestException e) {
            log.error("Error sending email", e);
        }
    }

    private boolean isEmail(String identifier) {
        return identifier.contains("@");
    }
}
