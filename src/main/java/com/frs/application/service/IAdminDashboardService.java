package com.frs.application.service;

import com.frs.application.payload.request.admindashboard.CountNewRecipesGetRequest;
import com.frs.application.payload.response.CountfNewRecipesResponse;

import java.util.List;

public interface IAdminDashboardService {
    List<CountfNewRecipesResponse> getNumberOfNewRecipesByDay(CountNewRecipesGetRequest request);

    Long countAllRecipes();
}
