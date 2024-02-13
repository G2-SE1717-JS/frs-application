package com.frs.application.logic;

import com.frs.application.dto.BlockAccountDTO;
import com.frs.core.base.BaseLogic;

import java.util.List;

public interface IBlockAccountLogic extends BaseLogic<BlockAccountDTO, Long> {
    List<BlockAccountDTO> findByAccountId(Long accountId);

    boolean isAccountBlocked(Long blockedAccountId, Long accountId);

}
