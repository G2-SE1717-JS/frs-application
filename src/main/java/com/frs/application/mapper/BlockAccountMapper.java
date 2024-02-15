package com.frs.application.mapper;

import com.frs.application.domain.BlockAccount;
import com.frs.application.dto.BlockAccountDTO;
import org.springframework.stereotype.Service;

@Service
public class BlockAccountMapper {
    public BlockAccountDTO toDto(BlockAccount entity) {
        if (entity == null) return null;
        return BlockAccountDTO.builder()
                .id(entity.getId())
                .blockAccountId(entity.getBlockAccountId())
                .accountId(entity.getAccountId())
                .isDeleted(entity.isDeleted())
                .createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();
    }
    public BlockAccount toEntity(BlockAccountDTO dto) {
        if (dto == null) return null;
        BlockAccount entity = new BlockAccount();
        entity.setId(dto.getId());
        entity.setBlockAccountId(dto.getBlockAccountId());
        entity.setAccountId(dto.getAccountId());
        entity.setDeleted(dto.isDeleted());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        return entity;
    }
}
