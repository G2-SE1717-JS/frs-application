package com.frs.application.logic.impl;

import com.frs.application.constants.enums.RecipeStatus;
import com.frs.application.domain.Recipe;
import com.frs.application.dto.RecipeDTO;
import com.frs.application.logic.IRecipeLogic;
import com.frs.application.mapper.RecipeMapper;
import com.frs.application.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeLogicImpl implements IRecipeLogic {
    private final RecipeRepository repository;
    private final RecipeMapper mapper;

    @Override
    public RecipeDTO save(RecipeDTO recipeDTO) {
        Recipe recipe = mapper.toEntity(recipeDTO);
        recipe = repository.save(recipe);
        return mapper.toDto(recipe);
    }

    @Override
    public RecipeDTO getById(Long aLong) {
        Recipe recipe = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("id"), aLong),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        ).orElse(null);
        return mapper.toDto(recipe);
    }

    @Override
    public RecipeDTO findByName(String name) {
        Recipe recipe = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("name"), name),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        ).orElse(null);
        return mapper.toDto(recipe);
    }

    @Override
    public List<RecipeDTO> findAll() {
        List<Recipe> recipeDTOS = repository.findAll(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get("isDeleted"), false)
        );
        return recipeDTOS.stream().map(mapper::toDto).toList();
    }

    @Override
    public List<RecipeDTO> getAllByAccountId(Long accountId) {
        List<Recipe> recipeDTOS = repository.findAll(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("accountId"), accountId),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        );
        return recipeDTOS.stream().map(mapper::toDto).toList();
    }

    @Override
    public List<RecipeDTO> getAllByRecipeStatus(Long accountId, RecipeStatus status) {
        List<Recipe> recipeDTOS = repository.findAll(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("accountId"), accountId),
                        criteriaBuilder.equal(root.get(("status")), status),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        );
        return recipeDTOS.stream().map(mapper::toDto).toList();
    }

    public List<RecipeDTO> findByTitle(String title) {
        List<Recipe> recipe = repository.findByName(title);
        return recipe.stream().map(mapper::toDto).toList();
    }

}
