package com.frs.application.logic;

import com.frs.application.dto.IngredientsDTO;
import com.frs.core.base.BaseLogic;

import java.util.List;

public interface IIngredientsLogic extends BaseLogic<IngredientsDTO, Long> {

    IngredientsDTO findByName(String name);

    List<IngredientsDTO> findAll();

    List<IngredientsDTO> getAllByRecipeId(Long id);
}
