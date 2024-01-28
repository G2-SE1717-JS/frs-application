package com.frs.application.logic.impl;

import com.frs.application.domain.Category;
import com.frs.application.dto.CategoryDTO;
import com.frs.application.logic.ICategoryLogic;
import com.frs.application.mapper.CategoryMapper;
import com.frs.application.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryImpl implements ICategoryLogic {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public CategoryDTO findByName(String name) {
        Category category = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("name"), name),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        ).orElse(null);
        return mapper.toDto(category);
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = repository.findAll(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get("isDeleted"), false)
        );
        return categories.stream().map(mapper::toDto).toList();
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = mapper.toEntity(categoryDTO);
        category = repository.save(category);
        return mapper.toDto(category);
    }

    @Override
    public CategoryDTO getById(Long aLong) {
        Category category = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("id"), aLong),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        ).orElse(null);
        return mapper.toDto(category);
    }
}
