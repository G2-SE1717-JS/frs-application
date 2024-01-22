package com.frs.application.logic;

import com.frs.application.dto.AccountDTO;
import com.frs.core.base.BaseLogic;

import java.util.List;

public interface IAccountLogic extends BaseLogic<AccountDTO, Long> {

    AccountDTO findByEmail(String email);
    AccountDTO findByUsername(String username);
    List<AccountDTO> findAll();
    String getCurrentUserName();
}
