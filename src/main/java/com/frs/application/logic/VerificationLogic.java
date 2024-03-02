package com.frs.application.logic;

import com.frs.application.dto.VerificationDTO;
import com.frs.core.base.BaseLogic;

public interface VerificationLogic extends BaseLogic<VerificationDTO, Long> {
    VerificationDTO findByEmail(String email);
}
