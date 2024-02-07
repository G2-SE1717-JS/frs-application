package com.frs.application.payload.request.admindashboard;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CountNewRecipesGetRequest {
    private Long days;
}
