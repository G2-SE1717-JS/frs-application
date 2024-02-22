package com.frs.application.logic.impl;

import com.frs.application.domain.IngreRecipe;
import com.frs.application.dto.IngreRecipeDTO;
import com.frs.application.logic.IIngreRecipeLogic;
import com.frs.application.mapper.IngreRecipeMapper;
import com.frs.application.repository.IngreRecipeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class IngreRecipeLogicImpl implements IIngreRecipeLogic {

    private final IngreRecipeRepository repository;
    private final IngreRecipeMapper mapper;

    @Override
    public List<IngreRecipeDTO> getAllByRecipeId(Long id) {
        List<IngreRecipe> ingreRecipeDTOS = repository.findAll(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("recipe_id"), id)
                )
        );
        return ingreRecipeDTOS.stream().map(mapper::toDto).toList();
    }

    @Override
    public IngreRecipeDTO save(IngreRecipeDTO ingreRecipeDTO) {
        return null;
    }

    @Override
    public IngreRecipeDTO getById(Long aLong) {
        return null;
    }
}
