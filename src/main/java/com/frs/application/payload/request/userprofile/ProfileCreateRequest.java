package com.frs.application.payload.request.userprofile;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProfileCreateRequest {
    private String fullName;
}
