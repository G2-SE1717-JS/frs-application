package com.frs.application.mapper;

import com.frs.application.domain.FollowAccount;
import com.frs.application.dto.FollowAccountDTO;
import org.springframework.stereotype.Service;

@Service
public class FollowAccountMapper {
    public FollowAccountDTO toDto(FollowAccount entity) {
        if (entity == null) return null;
        return FollowAccountDTO.builder()
                .id(entity.getId())
                .accountId(entity.getAccountId())
                .followedAccountId(entity.getFollowedAccountId())
                .isDeleted(entity.isDeleted())
                .createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();
    }
    public FollowAccount toEntity(FollowAccountDTO dto) {
        if (dto == null) return null;
        FollowAccount entity = new FollowAccount();
        entity.setId(dto.getId());
        entity.setAccountId(dto.getAccountId());
        entity.setFollowedAccountId(dto.getFollowedAccountId());
        entity.setDeleted(dto.isDeleted());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        return entity;
    }
}
