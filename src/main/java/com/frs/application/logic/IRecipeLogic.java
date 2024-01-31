package com.frs.application.logic;


import com.frs.application.constants.enums.RecipeStatus;
import com.frs.application.dto.IngredientsDTO;
import com.frs.application.dto.RecipeDTO;
import com.frs.core.base.BaseLogic;

import java.util.List;

public interface IRecipeLogic extends BaseLogic<RecipeDTO, Long> {

    RecipeDTO findByName(String name);
    List<RecipeDTO> findAll();

    List<RecipeDTO> getAllByAccountId(Long accountId);

    List<RecipeDTO> getAllByRecipeStatus(Long accountId, RecipeStatus status);
}
