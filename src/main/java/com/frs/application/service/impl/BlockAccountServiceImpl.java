package com.frs.application.service.impl;

import com.frs.application.dto.AccountDTO;
import com.frs.application.dto.BlockAccountDTO;
import com.frs.application.logic.IAccountLogic;
import com.frs.application.logic.IBlockAccountLogic;
import com.frs.application.payload.response.BlockAccountResponse;
import com.frs.application.service.IBlockAccountService;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlockAccountServiceImpl implements IBlockAccountService {

    private final IBlockAccountLogic blockAccountLogic;
    private final IAccountLogic accountLogic;

    @Override
    public List<BlockAccountResponse> getByAccountID(String remoteUser) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        List<BlockAccountDTO> blockAccountDTOS = blockAccountLogic.findByAccountId(accountDTO.getId());
        return blockAccountDTOS.stream().map(
                blockAccountDTO -> BlockAccountResponse.builder()
                        .id(blockAccountDTO.getId())
                        .blockAccountId(blockAccountDTO.getBlockAccountId())
                        .accountId(blockAccountDTO.getAccountId())

                        .build()
        ).collect(Collectors.toList());
    }
    @Override
    public BlockAccountResponse create(Long blockAccountId, String remoteUser) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        BlockAccountDTO blockAccountDTO = BlockAccountDTO.builder()
                .accountId(accountDTO.getId())
                .blockAccountId(blockAccountId)
                .build();
        if (blockAccountLogic.isAccountBlocked(blockAccountId, accountDTO.getId())) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.service.block-yourself"));
        }
        blockAccountDTO = blockAccountLogic.save(blockAccountDTO);
        return BlockAccountResponse.builder()
                .id(blockAccountDTO.getId())
                .blockAccountId(blockAccountDTO.getBlockAccountId())
                .accountId(blockAccountDTO.getAccountId())
                .build();
    }

    @Override
    public void delete(Long id) {
        BlockAccountDTO blockAccountDTO = blockAccountLogic.getById(id);
        if (Objects.isNull(blockAccountDTO ))
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.service.not-existed"));
        blockAccountDTO.setDeleted(true);
        blockAccountLogic.save(blockAccountDTO);
    }

}
