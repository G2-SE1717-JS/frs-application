package com.frs.application.service.impl;

import com.frs.application.logic.IRecipeLogic;
import com.frs.application.payload.request.admindashboard.CountRecipesGetRequest;
import com.frs.application.payload.response.CountRecipesResponse;
import com.frs.application.service.IAdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements IAdminDashboardService {
    private final IRecipeLogic recipeLogic;

    @Override
    public List<CountRecipesResponse> countRecipesByDay(CountRecipesGetRequest request) {
        LocalDate startDate = LocalDate.now().minusDays(request.getDays());
        LocalDate endDate = LocalDate.now();
        List<Object[]> countRecipesResponses = recipeLogic.countRecipesByDateRange(startDate, endDate);

        return countRecipesResponses.stream().map(
                countRecipesResponse -> {
                    LocalDate localDate = ((Date) countRecipesResponse[0]).toLocalDate();
                    Long count = (Long) countRecipesResponse[1];
                    return CountRecipesResponse.builder()
                            .date(localDate)
                            .count(count)
                            .build();
                }
        ).collect(Collectors.toList());
    }

}
