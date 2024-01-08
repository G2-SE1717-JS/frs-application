package com.frs.application.mapper;

import com.frs.application.domain.Account;
import com.frs.application.dto.AccountDTO;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {
    public AccountDTO toDto(Account entity) {
        if (entity == null) return null;
        return AccountDTO.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .role(entity.getRole())
                .verified(entity.isVerified())
                .status(entity.isStatus())
                .isDeleted(entity.isDeleted())
                .createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();
    }
    public Account toEntity(AccountDTO dto) {
        if (dto == null) return null;
        Account entity = new Account();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        entity.setVerified(dto.isVerified());
        entity.setStatus(dto.isStatus());
        entity.setDeleted(dto.isDeleted());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        return entity;
    }
}
