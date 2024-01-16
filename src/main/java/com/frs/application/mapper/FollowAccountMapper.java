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
                .accountID(entity.getAccountID())
                .followedAccountID(entity.getFollowedAccountID())
                .isDeleted(entity.isDeleted())
                .createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();
    }
    public FollowAccount toEntity(FollowAccountDTO dto) {
        if (dto == null) return null;
        FollowAccount entity = new FollowAccount();
        entity.setId(dto.getId());
        entity.setAccountID(dto.getAccountID());
        entity.setFollowedAccountID(dto.getFollowedAccountID());
        entity.setDeleted(dto.isDeleted());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        return entity;
    }
}
