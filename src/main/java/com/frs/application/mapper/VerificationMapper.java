package com.frs.application.mapper;

import com.frs.application.domain.Verification;
import com.frs.application.dto.VerificationDTO;
import org.springframework.stereotype.Service;

@Service
public class VerificationMapper {
    public VerificationDTO toDto(Verification entity) {
        if (entity == null) return null;
        return VerificationDTO.builder()
                .id(entity.getId())
                .token(entity.getToken())
                .email(entity.getEmail())
                .expiredTime(entity.getExpiredTime())
                .build();
    }
    public Verification toEntity(VerificationDTO dto) {
        if (dto == null) return null;
        Verification entity = new Verification();
        entity.setId(dto.getId());
        entity.setToken(dto.getToken());
        entity.setEmail(dto.getEmail());
        entity.setExpiredTime(dto.getExpiredTime());
        return entity;
    }
}
