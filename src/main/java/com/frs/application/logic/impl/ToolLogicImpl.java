package com.frs.application.logic.impl;


import com.frs.application.domain.Tool;

import com.frs.application.dto.ToolDTO;
import com.frs.application.logic.IToolLogic;
import com.frs.application.mapper.ToolMapper;
import com.frs.application.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolLogicImpl implements IToolLogic {
    private final ToolMapper mapper;
    private final ToolRepository repository;

@Override
    public ToolDTO findByName(String name){
    Tool tool = repository.findOne(
            (root, query, criteriaBuilder)
                    -> criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("name"), name),
                    criteriaBuilder.equal(root.get("isDeleted"), false)
            )
    ).orElse(null);
    return mapper.toDto(tool);
}
@Override
public List<ToolDTO> findAll() {
    List<Tool> ingredientsDTOS = repository.findAll(
            (root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("isDeleted"), false)
    );
    return ingredientsDTOS.stream().map(mapper::toDto).toList();
}
@Override
public ToolDTO save(ToolDTO ToolDTO){
    Tool tool = mapper.toEntity(ToolDTO);
    tool = repository.save(tool);
    return mapper.toDto(tool);
}
@Override
    public ToolDTO getById(Long aLong){
    Tool tool = repository.findOne(
            (root, query, criteriaBuilder)
                    -> criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("id"), aLong),
                    criteriaBuilder.equal(root.get("isDeleted"), false)
            )
    ).orElse(null);
    return mapper.toDto(tool);
}
}
