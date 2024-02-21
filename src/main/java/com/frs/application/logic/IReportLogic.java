package com.frs.application.logic;


import com.frs.application.dto.ReportDTO;
import com.frs.core.base.BaseLogic;

import java.util.List;

public interface IReportLogic extends BaseLogic<ReportDTO, Long> {

    List<ReportDTO> getAllReportByUser(Long accountId);

    List<ReportDTO> getAllReportByAdmin();

}
