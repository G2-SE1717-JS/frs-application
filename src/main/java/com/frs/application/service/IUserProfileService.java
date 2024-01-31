package com.frs.application.service;

import com.frs.application.payload.request.userprofile.ProfileCreateRequest;
import com.frs.application.payload.request.userprofile.ProfileUpdateRequest;
import com.frs.application.payload.response.UserProfileResponse;

public interface IUserProfileService {
    UserProfileResponse create(Long accountId, ProfileCreateRequest request);
    UserProfileResponse getById(Long id);
    UserProfileResponse update(String remoteUser, ProfileUpdateRequest request);
    void delete (String remoteUser);
    UserProfileResponse getMe(String remoteUser);
}
