package com.frs.application.service;

import com.frs.application.payload.request.AccountCreateRequest;
import com.frs.application.payload.request.AccountUpdateRequest;
import com.frs.application.payload.response.AccountResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


public interface IAccountService {
    List<AccountResponse> getAll();

    AccountResponse create(AccountCreateRequest request);
    AccountResponse update(Long id, AccountUpdateRequest request);
    AccountResponse getById(Long id);
    void delete(Long id);


    List<AccountResponse> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
