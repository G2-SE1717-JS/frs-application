package com.frs.application.logic.impl;

import com.frs.application.domain.Ingredients;
import com.frs.application.domain.SaveRecipe;
import com.frs.application.dto.IngredientsDTO;
import com.frs.application.dto.SaveRecipeDTO;
import com.frs.application.logic.ISaveRecipeLogic;
import com.frs.application.mapper.SaveRecipeMapper;
import com.frs.application.repository.SaveRecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveRecipeLogicImpl implements ISaveRecipeLogic {
    private final SaveRecipeRepository repository;
    private final SaveRecipeMapper mapper;
    @Override
    public List<SaveRecipeDTO> getAllSaveRecipe(Long accountId) {
        List<SaveRecipe> saveRecipeDTOS = repository.findAll(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("accountId"), accountId),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        );
        return saveRecipeDTOS.stream().map(mapper::toDto).toList();

    }

    @Override
    public SaveRecipeDTO isRecipeSaved(Long accountId, Long recipeId){
        SaveRecipe savedRecipe = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("accountId"), accountId),
                        criteriaBuilder.equal(root.get("recipeId"), recipeId),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        ).orElse(null);
        return mapper.toDto(savedRecipe);
    }

    @Override
    public SaveRecipeDTO save(SaveRecipeDTO saveRecipeDTO) {
        SaveRecipe saveRecipe = mapper.toEntity(saveRecipeDTO);
        saveRecipe = repository.save(saveRecipe);
        return mapper.toDto(saveRecipe);
    }

    @Override
    public SaveRecipeDTO getById(Long aLong) {
        SaveRecipe saveRecipe = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("id"), aLong),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        ).orElse(null);
        return mapper.toDto(saveRecipe);
    }
}
