package com.frs.application.payload.request.report;
<<<<<<< HEAD
import com.frs.application.constants.enums.ReportStatus;
=======
<<<<<<< HEAD
import com.frs.core.constants.enums.UserRole;
=======
import com.frs.application.constants.enums.ReportStatus;
>>>>>>> 159515139248e121841ac3aa613227faab6bd7ff
>>>>>>> origin/main
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ReportCreateRequest {
    private Long recipeId;
    private String description;
    private ReportStatus status;

<<<<<<< HEAD
}

=======
}
>>>>>>> 159515139248e121841ac3aa613227faab6bd7ff
