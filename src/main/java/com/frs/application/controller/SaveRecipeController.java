package com.frs.application.controller;

import com.frs.application.payload.request.saveRecipe.SaveRecipeCreateRequest;
import com.frs.application.payload.response.SaveRecipeResponse;
import com.frs.application.service.ISaveRecipeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/save")
@RequiredArgsConstructor
public class SaveRecipeController {
    private final ISaveRecipeService iSaveRecipeService;

    @PostMapping
    public ResponseEntity<SaveRecipeResponse> save(@RequestBody @Valid SaveRecipeCreateRequest request, HttpServletRequest req) {
        SaveRecipeResponse saveRecipeResponse = iSaveRecipeService.save(request.getRecipeId(), req.getRemoteUser());
        return ResponseEntity.ok(saveRecipeResponse);
    }
    @GetMapping
    public ResponseEntity<List<SaveRecipeResponse>> getByAccountID(@RequestBody @Valid Long accountId){
        return ResponseEntity.ok(iSaveRecipeService.getByAccountID(accountId));
    }

    @DeleteMapping
    public ResponseEntity <Void> delete(@PathVariable Long id){
        iSaveRecipeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
