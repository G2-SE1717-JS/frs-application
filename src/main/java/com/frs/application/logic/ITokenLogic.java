package com.frs.application.logic;

import com.frs.application.dto.TokenDTO;
import com.frs.core.base.BaseLogic;

public interface ITokenLogic extends BaseLogic<TokenDTO, Long> {
    TokenDTO findByToken(String token);
}
