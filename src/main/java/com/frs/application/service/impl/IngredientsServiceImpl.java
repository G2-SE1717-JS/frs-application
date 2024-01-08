package com.frs.application.service.impl;

import com.frs.application.domain.Ingredients;
import com.frs.application.dto.IngredientsDTO;
import com.frs.application.logic.IIngredientsLogic;
import com.frs.application.payload.request.ingredients.IngredientsCreateRequest;
import com.frs.application.payload.request.ingredients.IngredientsUpdateRequest;
import com.frs.application.payload.response.IngredientsResponse;
import com.frs.application.service.IIngredientsService;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import com.frs.core.utils.VersionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientsServiceImpl implements IIngredientsService {
    private final IIngredientsLogic ingredientsLogic;
    @Override
    public List<IngredientsResponse> getAll() {
        List<IngredientsDTO> ingredientsDTOS = ingredientsLogic.findAll();
        return ingredientsDTOS.stream().map(
                ingredientsDTO -> IngredientsResponse.builder()
                        .id(ingredientsDTO.getId())
                        .name(ingredientsDTO.getName())
                        .createdDate(ingredientsDTO.getCreatedDate())
                        .lastModifiedDate(ingredientsDTO.getLastModifiedDate())
                        .build()
        ).collect(Collectors.toList());
    }
    @Override
    public IngredientsResponse create(IngredientsCreateRequest request) {
        IngredientsDTO ingredientsDTO = IngredientsDTO.builder()
                .name(request.getName())
                .image(request.getImage())
                .build();
        ingredientsDTO = ingredientsLogic.save(ingredientsDTO);
        return IngredientsResponse.builder()
                .id(ingredientsDTO.getId())
                .name(ingredientsDTO.getName())
                .image(ingredientsDTO.getImage())
                .createdDate(ingredientsDTO.getCreatedDate())
                .lastModifiedDate(ingredientsDTO.getLastModifiedDate())
                .build();
    }

    @Override
    public IngredientsResponse getById(Long id) {
        IngredientsDTO ingredientsDTO = ingredientsLogic.getById(id);
        if (Objects.isNull(ingredientsDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.ingredients.not-existed"));
        }
        return IngredientsResponse.builder()
                .id(ingredientsDTO.getId())
                .name(ingredientsDTO.getName())
                .createdDate(ingredientsDTO.getCreatedDate())
                .lastModifiedDate(ingredientsDTO.getLastModifiedDate())
                .build();
    }

    @Override
    public IngredientsResponse update(Long id, IngredientsUpdateRequest request) {
        IngredientsDTO ingredientsDTO = ingredientsLogic.getById(id);
        if (Objects.isNull(ingredientsDTO))
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.ingredients.not-existed"));
        ingredientsDTO = IngredientsDTO.builder()
                .id(ingredientsDTO.getId())
                .name(request.getName())
                .image(request.getImage())
                .createdDate(ingredientsDTO.getCreatedDate())
                .build();
        ingredientsDTO=ingredientsLogic.save(ingredientsDTO);
        return IngredientsResponse.builder()
                .id(ingredientsDTO.getId())
                .name(ingredientsDTO.getName())
                .image(ingredientsDTO.getImage())
                .createdDate(ingredientsDTO.getCreatedDate())
                .lastModifiedDate(ingredientsDTO.getLastModifiedDate())
                .build();
    }

    @Override
    public void delete(Long id) {
        IngredientsDTO ingredientsDTO = ingredientsLogic.getById(id);
        if (Objects.isNull(ingredientsDTO ))
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.service.not-existed"));

        ingredientsDTO.setDeleted(true);
        ingredientsLogic.save(ingredientsDTO);
    }
}
