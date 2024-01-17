package com.frs.application.service.impl;
import com.frs.application.dto.AccountDTO;
import com.frs.application.logic.IAccountLogic;
import com.frs.application.payload.request.AccountCreateRequest;
import com.frs.application.payload.request.AccountUpdateRequest;
import com.frs.application.payload.response.AccountResponse;
import com.frs.application.service.IAccountService;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements IAccountService {
    private final IAccountLogic accountLogic;
    @Override

    public List<AccountResponse> getAll() {
        List<AccountDTO> accountDTOS = accountLogic.findAll();
        return accountDTOS.stream().map(
                accountDTO -> AccountResponse.builder()
                        .id(accountDTO.getId())
                        .email(accountDTO.getEmail())
                        .username(accountDTO.getUsername())
                        .createdDate(accountDTO.getCreatedDate())
                        .lastModifiedDate(accountDTO.getLastModifiedDate())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public AccountResponse create(AccountCreateRequest request) {
        if(accountLogic.findByEmail(request.getEmail()) != null){
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.account.email-existed"));
        }
        if(accountLogic.findByUsername(request.getUsername()) != null){
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.account.username-existed"));
        }
        AccountDTO accountDTO = AccountDTO.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .role(request.getRole())
                .verified(request.isVerified())
                .status(request.isStatus())
                .build();
        accountDTO = accountLogic.save(accountDTO);
        return AccountResponse.builder()
                .id(accountDTO.getId())
                .email(accountDTO.getEmail())
                .role(accountDTO.getRole())
                .username(accountDTO.getUsername())
                .verified(accountDTO.isVerified())
                .status(accountDTO.isStatus())
                .lastModifiedDate(accountDTO.getLastModifiedDate())
                .createdDate(accountDTO.getCreatedDate())
                .build();
    }

    @Override
    public AccountResponse update(Long id, AccountUpdateRequest request) {
        AccountDTO accountDTO = accountLogic.getById(id);
        if(Objects.isNull(accountDTO)){
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.account.not-existed"));
        }
        accountDTO.setUsername(request.getUsername());
        accountDTO.setPassword(request.getPassword());
        accountDTO.setRole(request.getRole());
        accountDTO.setStatus(request.isStatus());
        accountDTO.setVerified(request.isVerified());
        accountDTO = accountLogic.save(accountDTO);
        return AccountResponse.builder()
                .id(accountDTO.getId())
                .email(accountDTO.getEmail())
                .username(accountDTO.getUsername())
                .lastModifiedDate(accountDTO.getLastModifiedDate())
                .createdDate(accountDTO.getCreatedDate())
                .build();
    }

    @Override
    public AccountResponse getById(Long id) {
        AccountDTO accountDTO = accountLogic.getById(id);
        if(Objects.isNull(accountDTO)){
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.account.not-existed"));
        }
        return AccountResponse.builder()
                .id(accountDTO.getId())
                .email(accountDTO.getEmail())
                .username(accountDTO.getUsername())
                .lastModifiedDate(accountDTO.getLastModifiedDate())
                .createdDate(accountDTO.getCreatedDate())
                .build();
    }

    @Override
    public void delete(Long id) {
    AccountDTO accountDTO = accountLogic.getById(id);
        if(Objects.isNull(accountDTO)){
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.account.not-existed"));
        }
        accountDTO.setDeleted(true);
        accountLogic.save(accountDTO);
    }
}
