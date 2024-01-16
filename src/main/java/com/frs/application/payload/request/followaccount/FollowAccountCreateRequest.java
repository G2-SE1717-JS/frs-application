package com.frs.application.payload.request.followaccount;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowAccountCreateRequest {
    @NotBlank
    private Long followAccountID;

}
