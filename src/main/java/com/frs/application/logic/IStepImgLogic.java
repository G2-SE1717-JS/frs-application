package com.frs.application.logic;

import com.frs.application.dto.StepImgDTO;
import com.frs.core.base.BaseLogic;

import java.util.List;

public interface IStepImgLogic extends BaseLogic<StepImgDTO, Long> {
    List<StepImgDTO> findAllByStepId(Long stepId);
    void deleteImageByStepId(Long stepId);
}
