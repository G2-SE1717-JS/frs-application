package com.frs.application.payload.request.userprofile;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProfileUpdateRequest {
    private String fullName;
    private String biography;
    private String origin;
    private String profilePicture;
}
