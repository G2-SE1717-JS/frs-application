package com.frs.application.service;

import com.frs.application.payload.request.AccountSignInRequest;
import com.frs.application.payload.request.TokenRefreshRequest;
import com.frs.application.payload.request.ingredients.IngredientsCreateRequest;
import com.frs.application.payload.request.ingredients.IngredientsUpdateRequest;
import com.frs.application.payload.response.IngredientsResponse;
import com.frs.application.payload.response.TokenResponse;

import java.util.List;

public interface IAuthService {
    TokenResponse login(AccountSignInRequest loginRequest);

    TokenResponse refresh(TokenRefreshRequest refreshRequest);
}
