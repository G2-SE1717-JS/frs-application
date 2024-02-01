package com.frs.application.service.impl;

import com.frs.application.dto.AccountDTO;
import com.frs.application.dto.ReportDTO;
import com.frs.application.constants.enums.ReportStatus;
import com.frs.application.dto.*;
import com.frs.application.logic.IAccountLogic;
import com.frs.application.logic.IReportLogic;
import com.frs.application.payload.request.report.AdminCommentRequest;
import com.frs.application.payload.request.report.ReportCreateRequest;
import com.frs.application.constants.enums.ReportStatus;
import com.frs.application.payload.request.report.ReportUpdateRequest;
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
        List<ReportDTO> reportDTOs = UserRole.ROLE_ADMIN.equals(accountDTO.getRole()) ?
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

    public ReportResponse updateAndAddComment(Long id, ReportUpdateRequest request, String remoteUser) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        ReportDTO reportDTO = reportLogic.getById(id);
        if (Objects.isNull(reportDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.report.not.found"));
        }

        if (ReportStatus.APPROVED.equals(reportDTO.getReportStatus()) || ReportStatus.REJECTED.equals(reportDTO.getReportStatus())) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.report.status.not.processing"));
        }
        if (UserRole.ROLE_USER.equals(accountDTO.getRole())) {
            reportDTO.setDescription(request.getDescription());
            reportDTO = reportLogic.save(reportDTO);
        } else if (UserRole.ROLE_ADMIN.equals(accountDTO.getRole())) {
            reportDTO.setReportStatus(request.getAdminCommentRequest().getReportStatus());
            reportDTO.setAdminResponse(request.getAdminCommentRequest().getAdminResponse());
            reportDTO.setAdminResponseDate(LocalDateTime.now());

            if (ReportStatus.APPROVED.equals(request.getAdminCommentRequest().getReportStatus())) {
                recipeService.delete(reportDTO.getRecipeId());
            }
        }

        reportLogic.save(reportDTO);
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
