package com.frs.application.service;

import com.frs.application.payload.request.tool.ToolCreateRequest;
import com.frs.application.payload.request.tool.ToolDeleteRequest;
import com.frs.application.payload.request.tool.ToolUpdateRequest;
import com.frs.application.payload.response.ToolResponse;

import java.util.List;

public interface IToolService {
    List<ToolResponse> getAll();
    ToolResponse create(ToolCreateRequest request);
    ToolResponse getById(Long id);
    ToolResponse update(Long id, ToolUpdateRequest request);
//    ToolResponse delete(Long id, ToolDeleteRequest request);
    void delete (Long id);
}
