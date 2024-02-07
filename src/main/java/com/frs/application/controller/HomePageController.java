package com.frs.application.controller;

import com.frs.application.payload.response.IngredientsResponse;
import com.frs.application.service.IIngredientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RestController
@RequestMapping("/homePage")
@RequiredArgsConstructor
public class HomePageController {


    private final IIngredientsService iIngredientsService;

    @GetMapping("/randomIngredients")
    public List<IngredientsResponse>getRandomIngredients() {
        return iIngredientsService.getRandomIngredients();
    }

}
