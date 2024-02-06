package com.frs.application.payload.request.admindashboard;

import com.frs.application.constants.enums.ReportStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
public class CountRecipesGetRequest {
    private Long days;
}
