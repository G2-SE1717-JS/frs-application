package com.frs.application.service.impl;

import com.frs.application.logic.IRecipeLogic;
import com.frs.application.payload.request.admindashboard.CountNewRecipesGetRequest;
import com.frs.application.payload.response.CountfNewRecipesResponse;
import com.frs.application.service.IAdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements IAdminDashboardService {
    private final IRecipeLogic recipeLogic;

    @Override
    public List<CountfNewRecipesResponse> getNumberOfNewRecipesByDay(CountNewRecipesGetRequest request) {
        LocalDate startDate = LocalDate.now().minusDays(request.getDays());
        LocalDate endDate = LocalDate.now();
        List<Object[]> countRecipesResponses = recipeLogic.getNumberOfNewRecipesByDay(startDate, endDate);

        return countRecipesResponses.stream().map(
                countRecipesResponse -> {
                    LocalDate localDate = ((Date) countRecipesResponse[0]).toLocalDate();
                    Long count = (Long) countRecipesResponse[1];
                    return CountfNewRecipesResponse.builder()
                            .date(localDate)
                            .count(count)
                            .build();
                }
        ).collect(Collectors.toList());
    }

    @Override
    public Long countAllRecipes() {
        return recipeLogic.countAllRecipes();
    }
}
