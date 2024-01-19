package com.frs.application.logic;

import com.frs.application.dto.CommentRecipeDTO;
import com.frs.core.base.BaseLogic;

import java.util.List;

public interface ICommentRecipeLogic extends BaseLogic<CommentRecipeDTO, Long> {
    List<CommentRecipeDTO> findAll();
    List<CommentRecipeDTO>getAllByRecipeId(Long id);
}
