package com.frs.application.logic;

import com.frs.application.dto.ToolDTO;
import com.frs.core.base.BaseLogic;

import java.util.List;

public interface IToolLogic extends BaseLogic<ToolDTO,Long> {
    ToolDTO findByName(String name);
    List<ToolDTO> findAll();

}
