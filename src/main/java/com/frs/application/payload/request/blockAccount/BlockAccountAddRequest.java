package com.frs.application.payload.request.blockAccount;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlockAccountAddRequest {
    private Long accountId;
    private Long blockedAccountId;
}
