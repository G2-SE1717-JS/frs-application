package com.frs.application.mapper;

import com.frs.application.domain.Report;
import com.frs.application.dto.ReportDTO;
import org.springframework.stereotype.Service;

@Service
public class ReportMapper {
    public ReportDTO toDto(Report entity) {
        if (entity == null) return null;
        return ReportDTO.builder()
                .id(entity.getId())
                .accountId(entity.getAccountId())
                .recipeId(entity.getRecipeId())
                .description(entity.getDescription())
                .reportStatus(entity.getReportStatus())
                .adminResponse(entity.getAdminResponse())
                .isDeleted(entity.isDeleted())
                .createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();
    }
    public Report toEntity(ReportDTO dto) {
        if (dto == null) return null;
        Report entity = new Report();
        entity.setId(dto.getId());
        entity.setAccountId(dto.getAccountId());
        entity.setRecipeId(dto.getRecipeId());
        entity.setDescription(dto.getDescription());
        entity.setReportStatus(dto.getReportStatus());
        entity.setAdminResponse(dto.getAdminResponse());
        entity.setDeleted(dto.isDeleted());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        return entity;
    }
}
