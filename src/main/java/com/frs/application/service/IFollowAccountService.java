package com.frs.application.service;

import com.frs.application.payload.response.FollowAccountResponse;

import java.util.List;

public interface IFollowAccountService {
    List<FollowAccountResponse> getAll();
    FollowAccountResponse create(Long followedAccountId);
    FollowAccountResponse getById(Long followedAccountId);
    void delete(Long followedAccountId);
}
