package com.frs.application.service.impl;


import com.frs.application.dto.ToolDTO;
import com.frs.application.logic.IToolLogic;

import com.frs.application.payload.request.tool.ToolCreateRequest;

import com.frs.application.payload.request.tool.ToolUpdateRequest;

import com.frs.application.payload.response.ToolResponse;
import com.frs.application.service.IToolService;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ToolServiceImpl implements IToolService {
    private final IToolLogic toolLogic;
    @Override
    public List<ToolResponse> getAll() {
        List<ToolDTO> toolDTOS = toolLogic.findAll();
        return toolDTOS.stream().map(
                toolDTO -> ToolResponse.builder()
                        .id(toolDTO.getId())
                        .name(toolDTO.getName())
                        .createdDate(toolDTO.getCreatedDate())
                        .lastModifiedDate(toolDTO.getLastModifiedDate())
                        .build()
        ).collect(Collectors.toList());
    }
    @Override
    public ToolResponse create(ToolCreateRequest request) {
        ToolDTO toolDTO = ToolDTO.builder()
                .name(request.getName())
                .image(request.getImage())
                .build();
        toolDTO = toolLogic.save(toolDTO);
        return ToolResponse.builder()
                .id(toolDTO.getId())
                .name(toolDTO.getName())
                .image(toolDTO.getImage())
                .createdDate(toolDTO.getCreatedDate())
                .lastModifiedDate(toolDTO.getLastModifiedDate())
                .build();
    }

    @Override
    public ToolResponse getById(Long id) {
        ToolDTO toolDTO = toolLogic.getById(id);
        if (Objects.isNull(toolLogic)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.tool.not-existed"));
        }
        return ToolResponse.builder()
                .id(toolDTO.getId())
                .name(toolDTO.getName())
                .createdDate(toolDTO.getCreatedDate())
                .lastModifiedDate(toolDTO.getLastModifiedDate())
                .build();
    }

    @Override
    public ToolResponse update(Long id, ToolUpdateRequest request) {
        ToolDTO toolDTO = toolLogic.getById(id);
        if (Objects.isNull(toolDTO))
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.tool.not-existed"));
        toolDTO = ToolDTO.builder()
                .id(toolDTO.getId())
                .name(request.getName())
                .image(request.getImage())
                .createdDate(toolDTO.getCreatedDate())
                .build();
        toolDTO=toolLogic.save(toolDTO);
        return ToolResponse.builder()
                .id(toolDTO.getId())
                .name(toolDTO.getName())
                .image(toolDTO.getImage())
                .createdDate(toolDTO.getCreatedDate())
                .lastModifiedDate(toolDTO.getLastModifiedDate())
                .build();
    }

    @Override
    public void delete(Long id) {
        ToolDTO toolDTO = toolLogic.getById(id);
        if (Objects.isNull(toolDTO ))
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.tool.not-existed"));

        toolDTO.setDeleted(true);
        toolLogic.save(toolDTO);
    }

}
