package com.frs.application.logic.impl;

import com.frs.application.domain.RecipeImg;
import com.frs.application.dto.RecipeImgDTO;
import com.frs.application.logic.IRecipeImgLogic;
import com.frs.application.mapper.RecipeImgMapper;
import com.frs.application.repository.RecipeImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeImgLogicImpl implements IRecipeImgLogic {
    private final RecipeImgRepository repository;
    private final RecipeImgMapper mapper;

    @Override
    public RecipeImgDTO save(RecipeImgDTO recipeImgDTO) {
        RecipeImg recipeImg = mapper.toEntity(recipeImgDTO);
        recipeImg = repository.save(recipeImg);
        return mapper.toDto(recipeImg);
    }

    @Override
    public RecipeImgDTO getById(Long aLong) {
        RecipeImg recipeImg = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("id"), aLong),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        ).orElse(null);
        return mapper.toDto(recipeImg);
    }

    @Override
    public List<RecipeImgDTO> getAllByRecipeId(Long recipeId) {
        List<RecipeImg> recipeImgs = repository.findAll(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("recipeId"), recipeId),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        );
        return recipeImgs.stream().map(mapper::toDto).toList();
    }
}
