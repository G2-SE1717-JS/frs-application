package com.frs.application.service;

import com.frs.application.payload.request.ingredients.IngredientsCreateRequest;
import com.frs.application.payload.request.ingredients.IngredientsUpdateRequest;
import com.frs.application.payload.response.IngredientsResponse;

import java.util.List;

public interface IIngredientsService {
    List<IngredientsResponse> getAll();
    IngredientsResponse create(IngredientsCreateRequest request);
    IngredientsResponse getById(Long id);
    IngredientsResponse update(Long id, IngredientsUpdateRequest request);
    void delete(Long id);
}
