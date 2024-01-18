package com.frs.application.service.impl;

import com.frs.application.dto.CategoryDTO;
import com.frs.application.logic.ICategoryLogic;
import com.frs.application.service.ICategoryService;
import com.frs.application.payload.request.category.CategoryCreateRequest;
import com.frs.application.payload.request.category.CategoryUpdateRequest;
import com.frs.application.payload.response.CategoryResponse;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService implements ICategoryService {
    private final ICategoryLogic categoryLogic;

    @Override
    public List<CategoryResponse> getAll() {
        List<CategoryDTO> categoryDTOS = categoryLogic.findAll();
        return categoryDTOS.stream().map(
                categoryDTO -> CategoryResponse.builder()
                        .id(categoryDTO.getId())
                        .name(categoryDTO.getName())
                        .createdDate(categoryDTO.getCreatedDate())
                        .lastModifiedDate(categoryDTO.getLastModifiedDate())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public CategoryResponse create(CategoryCreateRequest request) {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name(request.getName())
                .image(request.getImage())
                .build();
        categoryDTO = categoryLogic.save(categoryDTO);
        return CategoryResponse.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .image(categoryDTO.getImage())
                .createdDate(categoryDTO.getCreatedDate())
                .lastModifiedDate(categoryDTO.getLastModifiedDate())
                .build();
    }

    @Override
    public CategoryResponse getById(Long id) {
        CategoryDTO categoryDTO = categoryLogic.getById(id);
        if (Objects.isNull(categoryDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.category.not-existed"));
        }
        return CategoryResponse.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .image(categoryDTO.getImage())
                .createdDate(categoryDTO.getCreatedDate())
                .lastModifiedDate(categoryDTO.getLastModifiedDate())
                .build();
    }

    @Override
    public CategoryResponse update(Long id, CategoryUpdateRequest request) {
        CategoryDTO categoryDTO = categoryLogic.getById(id);
        if (Objects.isNull(categoryDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.category.not-existed"));
        }
        categoryDTO.setName(request.getName());
        categoryDTO.setImage(request.getImage());
        categoryDTO = categoryLogic.save(categoryDTO);
        return CategoryResponse.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .image(categoryDTO.getImage())
                .createdDate(categoryDTO.getCreatedDate())
                .lastModifiedDate(categoryDTO.getLastModifiedDate())
                .build();
    }

    @Override
    public void delete(Long id) {
        CategoryDTO categoryDTO = categoryLogic.getById(id);
        if (Objects.isNull(categoryDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.category.not-existed"));
        }
        categoryDTO.setDeleted(true);
        categoryLogic.save(categoryDTO);
    }
}
