package com.frs.application.service;

import com.frs.application.payload.request.followaccount.FollowAccountRequest;
import com.frs.application.payload.response.FollowAccountResponse;

import java.util.List;

public interface IFollowAccountService {
    List<FollowAccountResponse> getAll();
    FollowAccountResponse create(FollowAccountRequest request);
    void delete(FollowAccountRequest request);
}
