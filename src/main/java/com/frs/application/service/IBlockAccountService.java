package com.frs.application.service;

import com.frs.application.payload.request.blockAccount.BlockAccountCreateRequest;
import com.frs.application.payload.response.BlockAccountResponse;

import java.util.List;

public interface IBlockAccountService {
    List<BlockAccountResponse> getByAccountID(Long accountId);
    BlockAccountResponse create(BlockAccountCreateRequest request, String remoteUser);

    void delete(Long id);
}
