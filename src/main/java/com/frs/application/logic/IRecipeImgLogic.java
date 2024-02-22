package com.frs.application.logic;

import com.frs.application.dto.RecipeImgDTO;
import com.frs.core.base.BaseLogic;

import java.util.List;

public interface IRecipeImgLogic extends BaseLogic<RecipeImgDTO, Long>{

    List<RecipeImgDTO> getAllByRecipeId(Long recipeId);
}
