package com.frs.application.mapper;

import com.frs.application.domain.UserProfile;
import com.frs.application.dto.UserProfileDTO;
import org.springframework.stereotype.Service;

@Service
public class UserProfileMapper {
    public UserProfileDTO toDto(UserProfile entity) {
        if (entity == null) return null;
        return UserProfileDTO.builder()
                .id(entity.getId())
                .accountId(entity.getAccountId())
                .fullName(entity.getFullName())
                .biography(entity.getBiography())
                .origin(entity.getOrigin())
                .profilePicture(entity.getProfilePicture())
                .isDeleted(entity.isDeleted())
                .createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();
    }

    public UserProfile toEntity(UserProfileDTO dto) {
        if (dto == null) return null;
        UserProfile entity = new UserProfile();
        entity.setId(dto.getId());
        entity.setAccountId(dto.getAccountId());
        entity.setFullName(dto.getFullName());
        entity.setBiography(dto.getBiography());
        entity.setOrigin(dto.getOrigin());
        entity.setProfilePicture(dto.getProfilePicture());
        entity.setDeleted(dto.isDeleted());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        return entity;
    }
}
