package com.frs.application.mapper;

import com.frs.application.domain.Token;
import com.frs.application.dto.TokenDTO;
import org.springframework.stereotype.Service;

@Service
public class TokenMapper {
    public TokenDTO toDto(Token entity) {
        if (entity == null) return null;
        return TokenDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .refreshToken(entity.getRefreshToken())
                .expiresAt(entity.getExpiresAt())
                .version(entity.getVersion())
                .isDeleted(entity.isDeleted())
                .createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();
    }
    public Token toEntity(TokenDTO dto) {
        if (dto == null) return null;
        Token entity = new Token();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setRefreshToken(dto.getRefreshToken());
        entity.setExpiresAt(dto.getExpiresAt());
        entity.setVersion(dto.getVersion());
        entity.setDeleted(dto.isDeleted());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        return entity;
    }
}
