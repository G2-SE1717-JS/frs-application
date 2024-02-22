package com.frs.application.logic;

import com.frs.application.dto.SaveRecipeDTO;
import com.frs.core.base.BaseLogic;

import java.util.List;

public interface ISaveRecipeLogic extends BaseLogic<SaveRecipeDTO, Long> {

    List<SaveRecipeDTO> getAllSaveRecipe(Long accountId);

}
