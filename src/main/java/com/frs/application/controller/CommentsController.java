package com.frs.application.controller;
import com.azure.core.annotation.Get;
import com.frs.application.payload.request.comments.CommentRecipeCreateRequest;
import com.frs.application.payload.response.CommentRecipeResponse;
import com.frs.application.service.ICommentRecipeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor

public class CommentsController {
   private final ICommentRecipeService ICommentsService;
    @PostMapping("/create")
    public ResponseEntity<CommentRecipeResponse> create(@RequestBody CommentRecipeCreateRequest request, HttpServletRequest req) {
        return ResponseEntity.ok(ICommentsService.create(request, req.getRemoteUser()));
    }
    @GetMapping("/{id}")
    public List<CommentRecipeResponse> getAllByRecipeId(@PathVariable Long id) {
        return ICommentsService.getAllByRecipeId(id);
    }
}
