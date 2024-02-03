package com.frs.application.service;

import com.frs.application.payload.request.step.StepCreateRequest;
import com.frs.application.payload.response.StepResponse;

import java.util.List;

public interface IStepService {
    StepResponse update(Long id, StepCreateRequest request);

    List<StepResponse> getAllByRecipeId(Long id);
}
