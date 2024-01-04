package com.frs.application.service;

import com.frs.application.payload.request.AccountSignInRequest;
import com.frs.application.payload.response.TokenResponse;

public interface IAuthService {
    TokenResponse login(AccountSignInRequest loginRequest);
}
