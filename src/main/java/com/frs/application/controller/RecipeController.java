package com.frs.application.controller;

import com.frs.application.payload.request.recipe.RecipeCreateRequest;
import com.frs.application.payload.request.recipe.RecipeUpdateRequest;
import com.frs.application.payload.response.RecipeImgRespose;
import com.frs.application.payload.response.RecipeResponse;
import com.frs.application.service.IRecipeImgService;
import com.frs.application.service.IRecipeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final IRecipeService recipeService;
    private final IRecipeImgService recipeImgService;
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
/*//    =========================================GET ALL IMAGES OF 1 RECIPE BY RECIPE ID

    @GetMapping("/images")
    public List<RecipeImgRespose> getAllByRecipeId(@RequestParam Long recipeId){
        return recipeImgService.getAllByRecipeId(recipeId);
    }*/

    /*@GetMapping("/accountByRecipeId")
    public Long getAccountIdByRecipeId(@RequestParam Long recipeId){
        return recipeService.getAccountIdByRecipeId(recipeId);}*/

    @GetMapping("/viewDetails/{id}")
    public RecipeResponse getRecipeDetails(@PathVariable Long id){
        return recipeService.getRecipeDetails(id);
    }
}
