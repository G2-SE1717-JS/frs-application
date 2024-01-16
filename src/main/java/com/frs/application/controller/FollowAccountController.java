package com.frs.application.controller;

import com.frs.application.logic.IFollowAccountLogic;
import com.frs.application.payload.request.followaccount.FollowAccountCreateRequest;
import com.frs.application.payload.request.ingredients.IngredientsCreateRequest;
import com.frs.application.payload.request.ingredients.IngredientsUpdateRequest;
import com.frs.application.payload.response.FollowAccountResponse;
import com.frs.application.payload.response.IngredientsResponse;
import com.frs.application.service.IFollowAccountService;
import com.frs.application.service.IIngredientsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/follow-account")
@RequiredArgsConstructor
public class FollowAccountController {

    private final IFollowAccountService followAccountService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<FollowAccountResponse> create(@RequestBody @Valid FollowAccountCreateRequest request) {
        return ResponseEntity.ok(followAccountService.create(request));
    }
//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<IngredientsResponse> update(@PathVariable Long id, @RequestBody @Valid IngredientsUpdateRequest request) {
//        return ResponseEntity.ok(iIngredientsService.update(id, request));
//    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> unfollow(@PathVariable Long id) {
//        followAccountService.delete(ád);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<FollowAccountResponse>> getAll() {
        return ResponseEntity.ok(followAccountService.getAll());
    }
}
