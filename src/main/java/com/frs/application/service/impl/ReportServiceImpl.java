package com.frs.application.service.impl;

import com.frs.application.dto.AccountDTO;
import com.frs.application.dto.RecipeDTO;
import com.frs.application.dto.ReportDTO;
import com.frs.application.logic.IAccountLogic;
import com.frs.application.logic.IRecipeLogic;
import com.frs.application.logic.IReportLogic;
import com.frs.application.payload.request.report.ReportCreateRequest;
import com.frs.application.payload.response.ReportResponse;
import com.frs.application.service.IReportService;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements IReportService {

    private final IReportLogic reportLogic;
    private final IAccountLogic accountLogic;
    private final IRecipeLogic recipeLogic;

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
                        .reportStatus(reportDTO.getReportStatus())
                        .adminResponseDate(reportDTO.getAdminResponseDate())
                        .adminResponse(reportDTO.getAdminResponse())
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
                .build();
            reportDTO = reportLogic.save(reportDTO);
        reportDTO = reportLogic.save(reportDTO);
        return ReportResponse.builder()
                .id(reportDTO.getId())
                .accountId(reportDTO.getAccountId())
                .recipeId(reportDTO.getRecipeId())
                .description(reportDTO.getDescription())
                .build();
    }

    @Override
    public ReportResponse update(Long id, String description) {
        ReportDTO reportDTO = reportLogic.getById(id);
        if(Objects.isNull(reportDTO)){
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.account.not-existed"));
        }
        reportDTO.setDescription(description);
        reportDTO = reportLogic.save(reportDTO);
        return ReportResponse.builder()
                .id(reportDTO.getId())
                .accountId(reportDTO.getAccountId())
                .recipeId(reportDTO.getRecipeId())
                .description(reportDTO.getDescription())
                .lastModifiedDate(reportDTO.getLastModifiedDate())
                .createdDate(reportDTO.getCreatedDate())
                .build();
    }

    @Override
    public void delete(Long id) {
        ReportDTO reportDTO = reportLogic.getById(id);
        if (Objects.isNull(reportDTO ))
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.service.not-existed"));
        reportDTO.setDeleted(true);
        reportLogic.save(reportDTO);
    }

}
