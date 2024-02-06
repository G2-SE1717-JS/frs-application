package com.frs.application.logic;


import com.frs.application.constants.enums.RecipeStatus;
import com.frs.application.dto.IngredientsDTO;
import com.frs.application.dto.RecipeDTO;
import com.frs.core.base.BaseLogic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IRecipeLogic extends BaseLogic<RecipeDTO, Long> {

    RecipeDTO findByName(String name);
    List<RecipeDTO> findAll();

    List<RecipeDTO> getAllByAccountId(Long accountId);

    List<RecipeDTO> getAllByRecipeStatus(Long accountId, RecipeStatus status);
    List<RecipeDTO> findByTitle(String title);


    List<Object[]> countRecipesByDateRange(LocalDate startDate, LocalDate endDate);
}
