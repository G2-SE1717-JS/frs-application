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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowAccountServiceImpl implements IFollowAccountService {
    private final IFollowAccountLogic followAccountLogic;
    private final AccountLogicImpl accountLogic;
    private static final Logger logger = LoggerFactory.getLogger(FollowAccountServiceImpl.class);

    @Override
    public List<FollowAccountResponse> getAll() {
        String currentUser = accountLogic.getCurrentUserName();
        AccountDTO accountDTO = accountLogic.findByUsername(currentUser);
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
    public FollowAccountResponse create(FollowAccountRequest request) {
        String currentUser = accountLogic.getCurrentUserName();
        AccountDTO accountDTO = accountLogic.findByUsername(currentUser);
        if (accountDTO.getId() == request.getFollowedAccountId()) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.service.follow-yourself"));
        } else {
            FollowAccountDTO followAccountDTO = followAccountLogic.getByIdAndFollowedId(accountDTO.getId(), request.getFollowedAccountId(), true);
            logger.info("Current record in follow account table: {}", followAccountDTO.toString());
            if (followAccountDTO != null) {
                followAccountDTO.setDeleted(false);
                logger.info("Current status of the record: {}", followAccountDTO.isDeleted());
                followAccountLogic.save(followAccountDTO);
                return null;
            } else {
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
        }

    }

    @Override
    public void delete(FollowAccountRequest request) {
        String currentUser = accountLogic.getCurrentUserName();
        AccountDTO accountDTO = accountLogic.findByUsername(currentUser);
        FollowAccountDTO followAccountDTO = followAccountLogic.getByIdAndFollowedId(accountDTO.getId(), request.getFollowedAccountId(), false);
        if (Objects.isNull(followAccountDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.service.not-existed"));
        } else {
            followAccountDTO.setDeleted(true);
            followAccountLogic.save(followAccountDTO);
        }
    }
}
