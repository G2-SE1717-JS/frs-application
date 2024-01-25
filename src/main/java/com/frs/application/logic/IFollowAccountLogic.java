package com.frs.application.logic;

import com.frs.application.dto.FollowAccountDTO;
import com.frs.core.base.BaseLogic;

import java.util.List;

public interface IFollowAccountLogic extends BaseLogic<FollowAccountDTO, Long> {

    List<FollowAccountDTO> getAllFollow(Long accountId);

    FollowAccountDTO getByIdAndFollowedId(Long accountId, Long followedAccountId);

}
