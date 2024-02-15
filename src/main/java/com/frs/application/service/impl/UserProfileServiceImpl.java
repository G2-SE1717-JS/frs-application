package com.frs.application.service.impl;


import com.frs.application.dto.AccountDTO;
import com.frs.application.dto.UserProfileDTO;
import com.frs.application.logic.IUserProfileLogic;
import com.frs.application.logic.impl.AccountLogicImpl;
import com.frs.application.payload.request.userprofile.ProfileCreateRequest;
import com.frs.application.payload.request.userprofile.ProfileUpdateRequest;
import com.frs.application.payload.response.UserProfileResponse;
import com.frs.application.service.IUserProfileService;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements IUserProfileService {
    private final IUserProfileLogic profileLogic;
    private final AccountLogicImpl accountLogic;

    @Override
    public UserProfileResponse getById(Long id) {
        UserProfileDTO userProfileDTO = profileLogic.getByAccountId(id);
        if (Objects.isNull(userProfileDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.user.not-existed"));
        }
        return UserProfileResponse.builder()
                .id(userProfileDTO.getId())
                .accountId(userProfileDTO.getAccountId())
                .fullName(userProfileDTO.getFullName())
                .biography(userProfileDTO.getBiography())
                .origin(userProfileDTO.getOrigin())
                .profilePicture(userProfileDTO.getProfilePicture())
                .createdDate(userProfileDTO.getCreatedDate())
                .lastModifiedDate(userProfileDTO.getLastModifiedDate())
                .build();
    }

    @Override
    public UserProfileResponse update(String remoteUser, ProfileUpdateRequest request) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        UserProfileDTO userProfileDTO = profileLogic.getByAccountId(accountDTO.getId());
        if (Objects.isNull(userProfileDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.user.not-existed"));
        }
        userProfileDTO = UserProfileDTO.builder()
                .id(userProfileDTO.getId())
                .fullName(request.getFullName())
                .biography(request.getBiography())
                .origin(request.getOrigin())
                .profilePicture(request.getProfilePicture())
                .build();
        userProfileDTO = profileLogic.save(userProfileDTO);
        return UserProfileResponse.builder()
                .id(userProfileDTO.getId())
                .accountId(userProfileDTO.getAccountId())
                .fullName(userProfileDTO.getFullName())
                .biography(userProfileDTO.getBiography())
                .origin(userProfileDTO.getOrigin())
                .profilePicture(userProfileDTO.getProfilePicture())
                .createdDate(userProfileDTO.getCreatedDate())
                .lastModifiedDate(userProfileDTO.getLastModifiedDate())
                .build();
    }

    @Override
    public void delete(String remoteUser) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        UserProfileDTO userProfileDTO = profileLogic.getByAccountId(accountDTO.getId());
        if (Objects.isNull(userProfileDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.user.not-existed"));
        }
        userProfileDTO.setDeleted(true);
        profileLogic.save(userProfileDTO);
    }

    @Override
    public UserProfileResponse create(Long accountId, ProfileCreateRequest request) {
        UserProfileDTO userProfileDTO = UserProfileDTO.builder()
                .accountId(accountId)
                .fullName(request.getFullName())
                .build();
        userProfileDTO = profileLogic.save(userProfileDTO);
        return UserProfileResponse.builder()
                .id(userProfileDTO.getId())
                .accountId(userProfileDTO.getAccountId())
                .fullName(userProfileDTO.getFullName())
                .biography(userProfileDTO.getBiography())
                .origin(userProfileDTO.getOrigin())
                .profilePicture(userProfileDTO.getProfilePicture())
                .createdDate(userProfileDTO.getCreatedDate())
                .lastModifiedDate(userProfileDTO.getLastModifiedDate())
                .build();
    }

    @Override
    public UserProfileResponse getMe(String remoteUser) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        if (Objects.isNull(accountDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.account.not-existed"));
        }
        UserProfileDTO userProfileDTO = profileLogic.getByAccountId(accountDTO.getId());
        return UserProfileResponse.builder()
                .id(userProfileDTO.getId())
                .accountId(userProfileDTO.getAccountId())
                .fullName(userProfileDTO.getFullName())
                .biography(userProfileDTO.getBiography())
                .origin(userProfileDTO.getOrigin())
                .profilePicture(userProfileDTO.getProfilePicture())
                .createdDate(userProfileDTO.getCreatedDate())
                .lastModifiedDate(userProfileDTO.getLastModifiedDate())
                .build();
    }
}
