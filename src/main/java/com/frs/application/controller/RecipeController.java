package com.frs.application.controller;

import com.frs.application.payload.request.recipe.RecipeCreateRequest;
import com.frs.application.service.IRecipeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final IRecipeService recipeService;
    @PostMapping
    public void create(@RequestBody RecipeCreateRequest recipe, HttpServletRequest req){
        recipeService.create(recipe,req.getRemoteUser());
    }
}
