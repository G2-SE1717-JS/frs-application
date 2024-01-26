package com.frs.application.controller;

import com.frs.application.payload.request.recipe.RecipeCreateRequest;
import com.frs.application.payload.request.recipe.RecipeUpdateRequest;
import com.frs.application.payload.response.RecipeResponse;
import com.frs.application.service.IRecipeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final IRecipeService recipeService;
    @PostMapping
    public void create(@RequestBody RecipeCreateRequest recipe, HttpServletRequest req){
        recipeService.create(recipe,req.getRemoteUser());
    }

    @GetMapping("/{id}")
    public RecipeResponse getById(@PathVariable Long id){
        return recipeService.getById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody RecipeUpdateRequest recipe){
        recipeService.update(id, recipe);
    }
    @GetMapping
    public List<RecipeResponse> getAll(){
        return recipeService.getAll();
    }

    @GetMapping("/account")
    public List<RecipeResponse> getAllByAccountId(HttpServletRequest req){
        return recipeService.getAllByAccountId(req.getRemoteUser());
    }

    @GetMapping("/getByStatus")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<RecipeResponse> getAllByAccountIdAndStatus(HttpServletRequest req, @RequestBody boolean status){
        return recipeService.getRecipesByAccountIdAndStatus(req.getRemoteUser(), status);
    }

}
