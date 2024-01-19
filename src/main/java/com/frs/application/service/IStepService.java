package com.frs.application.service;

import com.frs.application.payload.request.step.StepCreateRequest;
import com.frs.application.payload.response.StepResponse;

public interface IStepService {
    StepResponse update(Long id, StepCreateRequest request);
}
