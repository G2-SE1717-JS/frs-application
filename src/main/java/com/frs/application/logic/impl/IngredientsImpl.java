package com.frs.application.logic.impl;

import com.frs.application.domain.Ingredients;
import com.frs.application.dto.IngredientsDTO;
import com.frs.application.logic.IIngredientsLogic;
import com.frs.application.mapper.IngredientsMapper;
import com.frs.application.repository.IngredientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientsImpl implements IIngredientsLogic {
    private final IngredientsRepository repository;
    private final IngredientsMapper mapper;
    @Override
    public IngredientsDTO findByName(String name) {
        Ingredients ingredients = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("name"), name),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        ).orElse(null);
        return mapper.toDto(ingredients);
    }

    @Override
    public List<IngredientsDTO> findAll() {
        List<Ingredients> ingredientsDTOS = repository.findAll(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get("isDeleted"), false)
        );
        return ingredientsDTOS.stream().map(mapper::toDto).toList();
    }

    @Override
    public IngredientsDTO save(IngredientsDTO ingredientsDTO) {
        Ingredients ingredients = mapper.toEntity(ingredientsDTO);
        ingredients = repository.save(ingredients);
        return mapper.toDto(ingredients);
    }

    @Override
    public IngredientsDTO getById(Long aLong) {
        Ingredients ingredients = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("id"), aLong),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        ).orElse(null);
        return mapper.toDto(ingredients);
    }
    public List<IngredientsDTO> getRandomIngredients() {
        List<Ingredients> ingredientsDTOS = repository.findRandomIngredients();
        return ingredientsDTOS.stream().map(mapper::toDto).toList();
    }
}
