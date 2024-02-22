package com.frs.application.logic;

import com.frs.application.dto.IngreRecipeDTO;
import com.frs.core.base.BaseLogic;

import java.util.List;

public interface IIngreRecipeLogic extends BaseLogic<IngreRecipeDTO, Long> {
    List<IngreRecipeDTO> getAllByRecipeId(Long id);
}
