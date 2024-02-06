package com.frs.application.logic;

import com.frs.application.dto.AccountDTO;
import com.frs.core.base.BaseLogic;

import java.time.LocalDateTime;
import java.util.List;

public interface IAccountLogic extends BaseLogic<AccountDTO, Long> {

    AccountDTO findByEmail(String email);
    AccountDTO findByUsername(String username);
    List<AccountDTO> findAll();

    List<AccountDTO> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
