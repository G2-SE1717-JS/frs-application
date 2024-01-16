package com.frs.application.logic;

import com.frs.application.dto.FollowAccountDTO;
import com.frs.core.base.BaseLogic;

import java.util.List;

public interface IFollowAccountLogic extends BaseLogic<FollowAccountDTO, Long> {

    List<FollowAccountDTO> getAllFollow();


}
