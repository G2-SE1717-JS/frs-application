package com.frs.application.logic;

import com.frs.application.dto.StepDTO;
import com.frs.core.base.BaseLogic;

public interface IStepLogic extends BaseLogic<StepDTO, Long> {

    StepDTO findByName(String name);

}
