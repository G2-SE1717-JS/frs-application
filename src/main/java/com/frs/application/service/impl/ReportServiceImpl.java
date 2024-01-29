package com.frs.application.service.impl;

import com.frs.application.constants.enums.ReportStatus;
import com.frs.application.dto.*;
import com.frs.application.logic.IAccountLogic;
import com.frs.application.logic.IReportLogic;
import com.frs.application.payload.request.report.AdminCommentRequest;
import com.frs.application.payload.request.report.ReportCreateRequest;
import com.frs.application.payload.response.ReportResponse;
import com.frs.application.service.IRecipeService;
import com.frs.application.service.IReportService;
import com.frs.core.constants.enums.UserRole;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements IReportService {
    private final IReportLogic reportLogic;
    private final IAccountLogic accountLogic;
    private final IRecipeService recipeService;

    @Override
    public List<ReportResponse> getAllReport(String remoteUser) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        if (Objects.isNull(accountDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.account.not-existed"));
        }
        List<ReportDTO> reportDTOs = accountDTO.getRole().equals(UserRole.ROLE_ADMIN) ?
                reportLogic.getAllReportByAdmin() : reportLogic.getAllReportByUser(accountDTO.getId());

        return reportDTOs.stream().map(
                        reportDTO -> ReportResponse.builder()
                                .id(reportDTO.getId())
                                .accountId(reportDTO.getAccountId())
                                .recipeId(reportDTO.getRecipeId())
                                .description(reportDTO.getDescription())
                                .createdDate(reportDTO.getCreatedDate())
                                .lastModifiedDate(reportDTO.getLastModifiedDate())
                                .reportStatus(reportDTO.getReportStatus())
                                .adminResponse(reportDTO.getAdminResponse())
                                .adminResponseDate(reportDTO.getAdminResponseDate())
                                .build()
                ).sorted(Comparator.comparing(ReportResponse::getReportStatus, Comparator.comparingInt(ReportStatus::ordinal))
                        .thenComparing(ReportResponse::getCreatedDate))
                .collect(Collectors.toList());
        }

    @Override
    public ReportResponse create(String remoteUser, ReportCreateRequest request) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        if (Objects.isNull(accountDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.account.not-existed"));
        }
        ReportDTO reportDTO = ReportDTO.builder()
                .accountId(accountDTO.getId())
                .recipeId(request.getRecipeId())
                .description(request.getDescription())
                .reportStatus(ReportStatus.PROCESSING)
                .build();
        reportDTO = reportLogic.save(reportDTO);
        return ReportResponse.builder()
                .id(reportDTO.getId())
                .accountId(reportDTO.getAccountId())
                .recipeId(reportDTO.getRecipeId())
                .description(reportDTO.getDescription())
                .createdDate(reportDTO.getCreatedDate())
                .lastModifiedDate(reportDTO.getLastModifiedDate())
                .reportStatus(reportDTO.getReportStatus())
                .adminResponse(reportDTO.getAdminResponse())
                .adminResponseDate(reportDTO.getAdminResponseDate())
                .build();
    }

    @Override
    public ReportResponse update(Long id, String description) {
        ReportDTO reportDTO = reportLogic.getById(id);
        if (Objects.isNull(reportDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.report.not.found"));
        }
        reportDTO.setDescription(description);
        reportDTO = reportLogic.save(reportDTO);
        return ReportResponse.builder()
                .id(reportDTO.getId())
                .accountId(reportDTO.getAccountId())
                .recipeId(reportDTO.getRecipeId())
                .description(reportDTO.getDescription())
                .createdDate(reportDTO.getCreatedDate())
                .lastModifiedDate(reportDTO.getLastModifiedDate())
                .reportStatus(reportDTO.getReportStatus())
                .adminResponse(reportDTO.getAdminResponse())
                .adminResponseDate(reportDTO.getAdminResponseDate())
                .build();
    }



    @Override
    public ReportResponse addComment(Long reportId, AdminCommentRequest request) {
        ReportDTO reportDTO = reportLogic.getById(reportId);
        if (Objects.isNull(reportDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.report.not.found"));
        }

        reportDTO.setReportStatus(request.getReportStatus());
        reportDTO.setAdminResponse(request.getAdminResponse());
        reportDTO.setAdminResponseDate(LocalDateTime.now());
        reportLogic.save(reportDTO);

        if (request.getReportStatus().equals(ReportStatus.APPROVED)) {
            recipeService.delete(reportDTO.getRecipeId());
        }
        return ReportResponse.builder()
                .id(reportDTO.getId())
                .accountId(reportDTO.getAccountId())
                .recipeId(reportDTO.getRecipeId())
                .description(reportDTO.getDescription())
                .createdDate(reportDTO.getCreatedDate())
                .lastModifiedDate(reportDTO.getLastModifiedDate())
                .reportStatus(reportDTO.getReportStatus())
                .adminResponse(reportDTO.getAdminResponse())
                .adminResponseDate(reportDTO.getAdminResponseDate())
                .build();
    }
    @Override
    public void delete (Long id){
        ReportDTO reportDTO = reportLogic.getById(id);
        if (Objects.isNull(reportDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.report.not.found"));
        }
        reportDTO.setDeleted(true);
        reportLogic.save(reportDTO);
    }
}
