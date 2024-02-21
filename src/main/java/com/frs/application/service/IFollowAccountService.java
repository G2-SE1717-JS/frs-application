package com.frs.application.service;

import com.frs.application.payload.request.followaccount.FollowAccountRequest;
import com.frs.application.payload.response.FollowAccountResponse;

import java.util.List;

public interface IFollowAccountService {
    List<FollowAccountResponse> getAll(String remoteUser);
    FollowAccountResponse create(String remoteUser, FollowAccountRequest request);
    void delete(String remoteUser, FollowAccountRequest request);
}
