package com.frs.application.service.impl;

import com.frs.application.dto.AccountDTO;
import com.frs.application.dto.ReportDTO;
import com.frs.application.logic.IAccountLogic;
import com.frs.application.logic.IReportLogic;
import com.frs.application.payload.request.report.AdminCommentRequest;
import com.frs.application.payload.request.report.ReportCreateRequest;
import com.frs.application.constants.enums.ReportStatus;
import com.frs.application.payload.response.report.AdminReportResponse;
import com.frs.application.payload.response.report.ReportResponse;
import com.frs.application.service.IRecipeService;
import com.frs.application.service.IReportService;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements IReportService {
    private final IReportLogic reportLogic;
    private final IAccountLogic accountLogic;
    private final IRecipeService recipeService;

    @Override
    public List<ReportResponse> getByAccountID(String remoteUser) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        List<ReportDTO> reportDTOS = reportLogic.findByAccountId(accountDTO.getId());
        return reportDTOS.stream().map(
                reportDTO -> ReportResponse.builder()
                        .id(reportDTO.getId())
                        .accountId(reportDTO.getAccountId())
                        .recipeId(reportDTO.getRecipeId())
                        .description(reportDTO.getDescription())
                        .isDeleted(reportDTO.isDeleted())
                        .createdDate(reportDTO.getCreatedDate())
                        .lastModifiedDate(reportDTO.getLastModifiedDate())
                        .reportStatus(reportDTO.getReportStatus())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public ReportResponse create(String remoteUser, ReportCreateRequest request) {
        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);
        ReportDTO reportDTO = ReportDTO.builder()
                .accountId(accountDTO.getId())
                .recipeId(request.getRecipeId())
                .description(request.getDescription())
                .reportStatus(request.getStatus())
                .build();
        reportDTO = reportLogic.save(reportDTO);
        return ReportResponse.builder()
                .id(reportDTO.getId())
                .accountId(reportDTO.getAccountId())
                .recipeId(reportDTO.getRecipeId())
                .description(reportDTO.getDescription())
                .isDeleted(reportDTO.isDeleted())
                .createdDate(reportDTO.getCreatedDate())
                .lastModifiedDate(reportDTO.getLastModifiedDate())
                .reportStatus(reportDTO.getReportStatus())
                .build();
    }

    @Override
    public ReportResponse update(Long id, String description) {
        ReportDTO reportDTO = reportLogic.getById(id);
        if (Objects.isNull(reportDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.account.not-existed"));
        }
        reportDTO.setDescription(description);
        reportDTO = reportLogic.save(reportDTO);
        return ReportResponse.builder()
                .id(reportDTO.getId())
                .accountId(reportDTO.getAccountId())
                .recipeId(reportDTO.getRecipeId())
                .description(reportDTO.getDescription())
                .isDeleted(reportDTO.isDeleted())
                .createdDate(reportDTO.getCreatedDate())
                .lastModifiedDate(reportDTO.getLastModifiedDate())
                .reportStatus(reportDTO.getReportStatus())
                .build();
    }
        @Override
        public List<AdminReportResponse> getAllReportByAdmin () {
            List<ReportDTO> reportDTOs = reportLogic.getAllReportByAdmin();
            return reportDTOs.stream().map(
                            reportDTO -> AdminReportResponse.builder()
                                    .id(reportDTO.getId())
                                    .accountId(reportDTO.getAccountId())
                                    .recipeId(reportDTO.getRecipeId())
                                    .description(reportDTO.getDescription())
                                    .createdDate(reportDTO.getCreatedDate())
                                    .lastModifiedDate(reportDTO.getLastModifiedDate())
                                    .status(reportDTO.getReportStatus())
                                    .adminResponse(reportDTO.getAdminResponse())
                                    .adminResponseDate(reportDTO.getAdminResponseDate())
                                    .build()
                    ).sorted(Comparator.comparing(AdminReportResponse::getStatus, Comparator.comparingInt(ReportStatus::ordinal))
                            .thenComparing(AdminReportResponse::getCreatedDate))
                    .collect(Collectors.toList());
        }

        @Override
        public AdminReportResponse updateComment (Long reportId, AdminCommentRequest request){
            ReportDTO reportDTO = reportLogic.getById(reportId);
            if (Objects.isNull(reportDTO)) {
                throw new SystemBadRequestException(MessageHelper.getMessage("report.not.found"));
            }
            reportDTO.setReportStatus(request.getStatus());
            reportDTO.setAdminResponse(request.getAdminResponse());
            reportDTO.setAdminResponseDate(request.getAdminResponseDate());
            reportLogic.save(reportDTO);

            if (request.getStatus().equals(ReportStatus.APPROVED)) {
                recipeService.delete(reportDTO.getRecipeId());
            }
            return AdminReportResponse.builder()
                    .id(reportDTO.getId())
                    .accountId(reportDTO.getAccountId())
                    .recipeId(reportDTO.getRecipeId())
                    .description(reportDTO.getDescription())
                    .createdDate(reportDTO.getCreatedDate())
                    .lastModifiedDate(reportDTO.getLastModifiedDate())
                    .status(reportDTO.getReportStatus())
                    .adminResponse(reportDTO.getAdminResponse())
                    .adminResponseDate(reportDTO.getAdminResponseDate())
                    .build();
        }

        @Override
        public void delete (Long id){
            ReportDTO reportDTO = reportLogic.getById(id);
            if (Objects.isNull(reportDTO))
                throw new SystemBadRequestException(MessageHelper.getMessage("validation.service.not-existed"));
            reportDTO.setDeleted(true);
            reportLogic.save(reportDTO);
        }
}
