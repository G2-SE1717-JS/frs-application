package com.frs.application.service.impl;

import com.frs.application.dto.AccountDTO;
import com.frs.application.dto.FollowAccountDTO;
import com.frs.application.logic.IFollowAccountLogic;
import com.frs.application.logic.impl.AccountLogicImpl;
import com.frs.application.payload.request.followaccount.FollowAccountRequest;
import com.frs.application.payload.response.FollowAccountResponse;
import com.frs.application.service.IFollowAccountService;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowAccountServiceImpl implements IFollowAccountService {
    private final IFollowAccountLogic followAccountLogic;
    private final AccountLogicImpl accountLogic;
    @Override
    public List<FollowAccountResponse> getAll(String remoteUser) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        List<FollowAccountDTO> followAccountDTOS = followAccountLogic.getAllFollow(accountDTO.getId());
        return followAccountDTOS.stream()
                .sorted(Comparator.comparing(FollowAccountDTO::getCreatedDate).reversed())
                .map(
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
    public FollowAccountResponse create(String remoteUser, FollowAccountRequest request) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        FollowAccountDTO followAccountDTO = followAccountLogic.getByIdAndFollowedId(accountDTO.getId(), request.getFollowedAccountId());
        //if the request id is the user id
        if (accountDTO.getId() == request.getFollowedAccountId()) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.service.follow-yourself"));
        }
        //if the record is existed
        if (followAccountDTO != null) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.service.followed-account-existed"));
        }
        //not existed record with the followedAccountId, not follow yourself
        followAccountDTO = followAccountDTO.builder()
                .accountId(accountDTO.getId())
                .followedAccountId(request.getFollowedAccountId())
                .build();

        followAccountLogic.save(followAccountDTO);

        return FollowAccountResponse.builder()
                .id(followAccountDTO.getId())
                .accountId(followAccountDTO.getAccountId())
                .followedAccountId(followAccountDTO.getFollowedAccountId())
                .createdDate(followAccountDTO.getCreatedDate())
                .lastModifiedDate(followAccountDTO.getLastModifiedDate())
                .build();
    }


    @Override
    public void delete(String remoteUser, FollowAccountRequest request) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        FollowAccountDTO followAccountDTO = followAccountLogic.getByIdAndFollowedId(accountDTO.getId(), request.getFollowedAccountId());
        if (Objects.isNull(followAccountDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.service.not-existed"));
        } else {
            followAccountDTO.setDeleted(true);
            followAccountLogic.save(followAccountDTO);
        }
    }
}
