package com.frs.application.service;

import com.frs.application.payload.request.blockAccount.BlockAccountAddRequest;
import com.frs.application.payload.request.ingredients.IngredientsUpdateRequest;
import com.frs.application.payload.response.BlockAccountResponse;
import com.frs.application.payload.response.IngredientsResponse;

import java.util.List;

public interface IBlockAccountService {
    BlockAccountResponse add(BlockAccountAddRequest request);

    List<BlockAccountResponse> getByAccountID(Long accountId);

    void delete(Long id);
}
