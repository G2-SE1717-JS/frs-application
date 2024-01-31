package com.frs.application.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileResponse {
    private Long id;
    private Long accountId;
    private String fullName;
    private String biography;
    private String origin;
    private String profilePicture;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
<<<<<<< HEAD:src/main/java/com/frs/application/payload/response/report/ReportResponse.java
    private ReportStatus reportStatus;

=======
>>>>>>> origin/main:src/main/java/com/frs/application/payload/response/UserProfileResponse.java
}
