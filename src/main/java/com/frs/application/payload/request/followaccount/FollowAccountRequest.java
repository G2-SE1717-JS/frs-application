package com.frs.application.payload.request.followaccount;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowAccountRequest {
    private Long followedAccountId;
}
