package com.frs.application.logic.impl;

import com.frs.application.domain.Step;
import com.frs.application.dto.StepDTO;
import com.frs.application.logic.IStepLogic;
import com.frs.application.mapper.StepMapper;
import com.frs.application.repository.StepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StepLogicImpl implements IStepLogic {
    private final StepMapper mapper;
    private final StepRepository repository;

    @Override
    public StepDTO findByName(String name) {
        Step step = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("name"), name),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        ).orElse(null);
        return mapper.toDto(step);
    }

    @Override
    public StepDTO save(StepDTO stepDTO) {
        Step step = mapper.toEntity(stepDTO);
        step = repository.save(step);
        return mapper.toDto(step);
    }

    @Override
    public StepDTO getById(Long aLong) {
        Step step = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("id"), aLong),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        ).orElse(null);
        return mapper.toDto(step);
    }
}
