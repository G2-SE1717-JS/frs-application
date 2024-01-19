package com.frs.application.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FollowAccountResponse implements Serializable {
    private Long id;
    private Long accountId;
    private Long followedAccountId;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
