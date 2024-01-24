package com.frs.application.logic.impl;

import com.frs.application.domain.Report;
import com.frs.application.dto.ReportDTO;
import com.frs.application.logic.IReportLogic;
import com.frs.application.mapper.ReportMapper;
import com.frs.application.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportLogicImpl implements IReportLogic {
    private final ReportRepository repository;
    private final ReportMapper mapper;

    @Override
    public List<ReportDTO> findByAccountId(Long accountId) {
        List<Report> reportDTOS = repository.findAll(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("accountId"), accountId),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        );
        return reportDTOS.stream().map(mapper::toDto).toList();
    }



    @Override
    public ReportDTO save(ReportDTO blockAccountDTO) {
        Report report = mapper.toEntity(blockAccountDTO);
        report = repository.save(report);
        return mapper.toDto(report);
    }

    @Override
    public ReportDTO getById(Long id) {
        Report report = repository.findById(id).orElse(null);
        return mapper.toDto(report);
    }
}
