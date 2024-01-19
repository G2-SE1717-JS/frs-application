package com.frs.application.logic.impl;
import com.frs.application.domain.CommentRecipe;
import com.frs.application.dto.CommentRecipeDTO;
import com.frs.application.logic.ICommentRecipeLogic;
import com.frs.application.mapper.CommentRecipeMapper;
import com.frs.application.repository.CommentRecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsImpl implements ICommentRecipeLogic {
    private final CommentRecipeMapper mapper;
    private final CommentRecipeRepository repository;
    @Override
    public CommentRecipeDTO save(CommentRecipeDTO commentsDTO) {
        CommentRecipe comments = mapper.toEntity(commentsDTO);
        comments = repository.save(comments);
        return mapper.toDto(comments);
    }
    @Override
    public CommentRecipeDTO getById(Long aLong) {
        CommentRecipe comments = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("id"), aLong),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        ).orElse(null);
        return mapper.toDto(comments);
    }

    @Override
    public List<CommentRecipeDTO> findAll() {
        List<CommentRecipe> commentsDTOS = repository.findAll(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get("isDeleted"), false)
        );
        return commentsDTOS.stream().map(mapper::toDto).toList();
    }

    @Override
    public List<CommentRecipeDTO> getAllByRecipeId(Long id) {
    List<CommentRecipe> commentsDTOS = repository.findAll(
                    (root, query, criteriaBuilder)
                            -> criteriaBuilder.and(
                            criteriaBuilder.equal(root.get("recipeId"), id),
                            criteriaBuilder.equal(root.get("isDeleted"), false)
                    )
            );
            return commentsDTOS.stream().map(mapper::toDto).toList();
    }
}
