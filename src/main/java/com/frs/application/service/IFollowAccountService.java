package com.frs.application.service;

import com.frs.application.payload.request.followaccount.FollowAccountCreateRequest;
import com.frs.application.payload.request.followaccount.FollowAccountDeleteRequest;
import com.frs.application.payload.response.FollowAccountResponse;

import java.util.List;

public interface IFollowAccountService {
    List<FollowAccountResponse> getAll();

    FollowAccountResponse create(FollowAccountCreateRequest request);
    FollowAccountResponse getById(Long id);
    FollowAccountResponse delete(Long id, FollowAccountDeleteRequest request);
}
