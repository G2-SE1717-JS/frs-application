package com.frs.application.service;

import com.frs.application.payload.request.category.CategoryCreateRequest;
import com.frs.application.payload.request.category.CategoryUpdateRequest;
import com.frs.application.payload.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponse> getAll();
    CategoryResponse create(CategoryCreateRequest request);
    CategoryResponse getById(Long id);
    CategoryResponse update(Long id, CategoryUpdateRequest request);
    void delete(Long id);
}
