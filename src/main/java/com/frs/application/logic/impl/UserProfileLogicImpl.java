package com.frs.application.logic.impl;

import com.frs.application.domain.UserProfile;
import com.frs.application.dto.UserProfileDTO;
import com.frs.application.logic.IUserProfileLogic;
import com.frs.application.mapper.UserProfileMapper;
import com.frs.application.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserProfileLogicImpl implements IUserProfileLogic {
    private final UserProfileRepository repository;
    private final UserProfileMapper mapper;

    @Override
    public UserProfileDTO save(UserProfileDTO userProfileDTO) {
        UserProfile userProfile = mapper.toEntity(userProfileDTO);
        userProfile = repository.save(userProfile);
        return mapper.toDto(userProfile);
    }

    @Override
    public UserProfileDTO getById(Long aLong) {
        UserProfile userProfile = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("id"), aLong),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        ).orElse(null);
        return mapper.toDto(userProfile);
    }
}
