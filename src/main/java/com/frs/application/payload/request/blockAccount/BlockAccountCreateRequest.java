package com.frs.application.payload.request.blockAccount;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlockAccountCreateRequest {
    private Long blockedAccountId;
}
