package com.frs.application.logic.impl;

import com.frs.application.constants.SystemConstant;
import com.frs.application.domain.Account;
import com.frs.application.dto.AccountDTO;
import com.frs.application.logic.IAccountLogic;
import com.frs.application.mapper.AccountMapper;
import com.frs.application.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountLogicImpl implements IAccountLogic {
    private final AccountRepository repository;
    private final AccountMapper mapper;
    @Override
    public AccountDTO findByEmail(String email) {
        Account account = repository.findOne(
                        (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(SystemConstant.Entity.EMAIL), email))
                .orElse(null);
        return mapper.toDto(account);
    }

    @Override
    public AccountDTO findByUsername(String username) {
        Account account = repository.findOne(
                        (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(SystemConstant.Entity.USERNAME), username))
                .orElse(null);
        return mapper.toDto(account);
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        Account account = mapper.toEntity(accountDTO);
        account = repository.save(account);
        return mapper.toDto(account);
    }

    @Override
    public AccountDTO getById(Long id) {
        Account account = repository.findById(id).orElse(null);
        return mapper.toDto(account);
    }
}
