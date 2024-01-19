package com.frs.application.service.impl;

import com.frs.application.dto.AccountDTO;
import com.frs.application.dto.FollowAccountDTO;
import com.frs.application.logic.IFollowAccountLogic;
import com.frs.application.logic.impl.AccountLogicImpl;
import com.frs.application.payload.response.FollowAccountResponse;
import com.frs.application.securiry.AccountDetails;
import com.frs.application.service.IFollowAccountService;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import com.frs.core.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowAccountServiceImpl implements IFollowAccountService {
    private final IFollowAccountLogic followAccountLogic;
    private final AccountLogicImpl accountLogic;

    @Override
    public List<FollowAccountResponse> getAll() {
        String currentUser = followAccountLogic.getUserName();
        AccountDTO accountDTO = accountLogic.findByUsername(currentUser);
        List<FollowAccountDTO> followAccountDTOS = followAccountLogic.getAllFollow(accountDTO.getId());
        return followAccountDTOS.stream().map(
                FollowAccountDTO -> FollowAccountResponse.builder()
                        .id(FollowAccountDTO.getId())
                        .accountId(FollowAccountDTO.getAccountId())
                        .followedAccountId(FollowAccountDTO.getFollowedAccountId())
                        .createdDate(FollowAccountDTO.getCreatedDate())
                        .lastModifiedDate(FollowAccountDTO.getLastModifiedDate())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public FollowAccountResponse create(Long followedAccountId) {
        String currentUser = followAccountLogic.getUserName();
        AccountDTO accountDTO = accountLogic.findByUsername(currentUser);
        FollowAccountDTO followAccountDTO = followAccountLogic.getById(accountDTO.getId(), followedAccountId);
        if (followAccountDTO != null && followAccountDTO.isDeleted() == true) {
            followAccountDTO.setDeleted(false);
            followAccountLogic.save(followAccountDTO);
        } else {
            followAccountDTO = FollowAccountDTO.builder()
                    .accountId(accountDTO.getId())
                    .followedAccountId(followedAccountId)
                    .build();
            followAccountLogic.save(followAccountDTO);
        }
        return FollowAccountResponse.builder()
                .id(followAccountDTO.getId())
                .accountId(followAccountDTO.getAccountId())
                .followedAccountId(followAccountDTO.getFollowedAccountId())
                .createdDate(followAccountDTO.getCreatedDate())
                .lastModifiedDate(followAccountDTO.getLastModifiedDate())
                .build();
    }

    @Override
    public FollowAccountResponse getById(Long followedAccountId) {
        String currentUser = followAccountLogic.getUserName();
        AccountDTO accountDTO = accountLogic.findByUsername(currentUser);
        FollowAccountDTO followAccountDTOS = followAccountLogic.getById(accountDTO.getId(), followedAccountId);
        if (Objects.isNull(followAccountDTOS)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.followAccount.not-existed"));
        }
        return FollowAccountResponse.builder()
                .id(followAccountDTOS.getId())
                .accountId(followAccountDTOS.getAccountId())
                .followedAccountId(followAccountDTOS.getFollowedAccountId())
                .createdDate(followAccountDTOS.getCreatedDate())
                .lastModifiedDate(followAccountDTOS.getLastModifiedDate())
                .build();
    }

    @Override
    public void delete(Long followedAccountId) {
        String currentUser = followAccountLogic.getUserName();
        AccountDTO accountDTO = accountLogic.findByUsername(currentUser);
        FollowAccountDTO followAccountDTO = followAccountLogic.getById(accountDTO.getId(), followedAccountId);
        if (Objects.isNull(followAccountDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.service.not-existed"));
        } else {
            followAccountDTO.setDeleted(true);
            followAccountLogic.save(followAccountDTO);
        }
    }
}
