package com.frs.application.service.impl;

import com.frs.application.constants.enums.ReportStatus;
import com.frs.application.dto.*;
import com.frs.application.logic.IFollowAccountLogic;
import com.frs.application.logic.IRecipeLogic;
import com.frs.application.logic.IReportLogic;
import com.frs.application.logic.impl.AccountLogicImpl;
import com.frs.application.payload.request.AccountCreateRequest;
import com.frs.application.payload.request.followaccount.FollowAccountRequest;
import com.frs.application.payload.request.ingredients.IngredientsCreateRequest;
import com.frs.application.payload.request.recipe.RecipeUpdateRequest;
import com.frs.application.payload.request.report.AdminCommentRequest;
import com.frs.application.payload.response.AccountResponse;
import com.frs.application.payload.response.FollowAccountResponse;
import com.frs.application.payload.response.IngredientsResponse;
import com.frs.application.payload.response.RecipeResponse;
import com.frs.application.payload.response.report.AdminReportResponse;
import com.frs.application.payload.response.report.ReportResponse;
import com.frs.application.service.IRecipeService;
import com.frs.application.service.IReportService;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements IReportService {
    private final IReportLogic reportLogic;
    private final IRecipeService recipeService;

    @Override
    public List<AdminReportResponse> getAllReportByAdmin() {
        List<ReportDTO> reportDTOs = reportLogic.getAllReportByAdmin();
        return reportDTOs.stream().map(
                        reportDTO -> AdminReportResponse.builder()
                                .id(reportDTO.getId())
                                .accountId(reportDTO.getAccountId())
                                .recipeId(reportDTO.getRecipeId())
                                .description(reportDTO.getDescription())
                                .createdDate(reportDTO.getCreatedDate())
                                .lastModifiedDate(reportDTO.getLastModifiedDate())
                                .status(reportDTO.getStatus())
                                .adminResponse(reportDTO.getAdminResponse())
                                .adminResponseDate(reportDTO.getAdminResponseDate())
                                .build()
                ).sorted(Comparator.comparing(AdminReportResponse::getStatus, Comparator.comparingInt(this::statusOrder))
                        .thenComparing(AdminReportResponse::getCreatedDate))
                .collect(Collectors.toList());
    }

    @Override
    public AdminReportResponse updateComment(Long reportId, AdminCommentRequest request) {
        ReportDTO reportDTO = reportLogic.getById(reportId);
        if (Objects.isNull(reportDTO)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("report.not.found"));
        }
        reportDTO.setStatus(request.getStatus());
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
                .status(reportDTO.getStatus())
                .adminResponse(reportDTO.getAdminResponse())
                .adminResponseDate(reportDTO.getAdminResponseDate())
                .build();
    }

    private int statusOrder(ReportStatus status) {
        switch (status) {
            case PROCESSING:
                return 1;
            case REJECTED:
                return 2;
            case APPROVED:
                return 3;
            default:
                throw new IllegalArgumentException("Unexpected status: " + status);
        }
    }
}
