package com.frs.application.logic.impl;

import com.frs.application.domain.StepImg;
import com.frs.application.dto.StepImgDTO;
import com.frs.application.logic.IStepImgLogic;
import com.frs.application.mapper.StepImgMapper;
import com.frs.application.repository.StepImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StepImgLogicIpml implements IStepImgLogic {
    private final StepImgRepository repository;
    private final StepImgMapper mapper;
    @Override
    public StepImgDTO save(StepImgDTO stepImgDTO) {
        StepImg stepImg = mapper.toEntity(stepImgDTO);
        stepImg = repository.save(stepImg);
        return mapper.toDto(stepImg);
    }

    @Override
    public StepImgDTO getById(Long aLong) {
        StepImg stepImg = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("id"), aLong),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        ).orElse(null);
        return mapper.toDto(stepImg);
    }
}
