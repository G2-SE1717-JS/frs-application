package com.frs.application.service.impl;

import com.frs.application.dto.AccountDTO;
import com.frs.application.dto.CommentRecipeDTO;
import com.frs.application.logic.IAccountLogic;
import com.frs.application.logic.ICommentRecipeLogic;
import com.frs.application.payload.request.comments.CommentRecipeCreateRequest;
import com.frs.application.payload.response.CommentRecipeResponse;
import com.frs.application.service.ICommentRecipeService;

import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentsServiceImpl implements ICommentRecipeService {
    private final ICommentRecipeLogic commentsLogic;
    private final IAccountLogic accountLogic;
    @Override
    public CommentRecipeResponse create(CommentRecipeCreateRequest request, String remoteUser) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        CommentRecipeDTO commentsDTO = CommentRecipeDTO.builder()
                .accountId(accountDTO.getId())
                .parentId(request.getParentId())
                .recipeId(request.getRecipeId())
                .description(request.getDescription())
                .build();
        commentsDTO = commentsLogic.save(commentsDTO);
        return CommentRecipeResponse.builder()
                .id(commentsDTO.getId())
                .accountId(commentsDTO.getAccountId())
                .parentId(commentsDTO.getParentId())
                .recipeId(commentsDTO.getRecipeId())
                .description(commentsDTO.getDescription())
                .createdDate(commentsDTO.getCreatedDate())
                .lastModifiedDate(commentsDTO.getLastModifiedDate())
                .build();
    }

//update này khó vãi , chắc nà méo cho update nữa cho xóa thôi, huyennt chịu chếc , nỏ biết truyền vào cái gì nữa
    @Override
    public CommentRecipeResponse update(Long id, CommentRecipeCreateRequest request) {
        return null;
    }

    @Override
    public List<CommentRecipeResponse> getAllByRecipeId(Long id) {
        List<CommentRecipeDTO> commentsDTOS = commentsLogic.getAllByRecipeId(id);
        if(commentsDTOS.isEmpty()){
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.comments.not-existed"));
        }
        return commentsDTOS.stream().map(
                commentsDTO -> CommentRecipeResponse.builder()
                        .id(commentsDTO.getId())
                        .accountId(commentsDTO.getAccountId())
                        .parentId(commentsDTO.getParentId())
                        .recipeId(commentsDTO.getRecipeId())
                        .description(commentsDTO.getDescription())
                        .createdDate(commentsDTO.getCreatedDate())
                        .lastModifiedDate(commentsDTO.getLastModifiedDate())
                        .build()
        ).toList();
    }


    @Override
    public void delete(Long id) {
    }


}
