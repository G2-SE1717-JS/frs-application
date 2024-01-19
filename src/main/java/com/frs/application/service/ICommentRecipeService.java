package com.frs.application.service;

import com.frs.application.payload.request.comments.CommentRecipeCreateRequest;
import com.frs.application.payload.response.CommentRecipeResponse;

import java.util.List;

public interface ICommentRecipeService {
    CommentRecipeResponse create(CommentRecipeCreateRequest request, String remoteUser);
    CommentRecipeResponse update(Long id, CommentRecipeCreateRequest request);
    List<CommentRecipeResponse> getAllByRecipeId(Long id);

    void delete(Long id);
}
