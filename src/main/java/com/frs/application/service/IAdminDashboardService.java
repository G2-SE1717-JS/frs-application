package com.frs.application.service;

import com.frs.application.payload.request.admindashboard.CountRecipesGetRequest;
import com.frs.application.payload.response.CountRecipesResponse;

import java.util.List;

public interface IAdminDashboardService {
    List<CountRecipesResponse> countRecipesByDay(CountRecipesGetRequest request);
}
