package com.frs.application.payload.request.followaccount;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowAccountDeleteRequest {
    @NotBlank
    private Long followAccountID;

}
