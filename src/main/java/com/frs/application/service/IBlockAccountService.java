package com.frs.application.service;

import com.frs.application.payload.response.BlockAccountResponse;

import java.util.List;

public interface IBlockAccountService {
    List<BlockAccountResponse> getByAccountID(String remoteUser);
    BlockAccountResponse create(Long blockAccountId, String remoteUser);

    void delete(Long id);
}
