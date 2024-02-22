package com.frs.application.logic;

import com.frs.application.dto.CategoryDTO;
import com.frs.core.base.BaseLogic;

import java.util.List;

public interface ICategoryLogic extends BaseLogic<CategoryDTO, Long> {

    CategoryDTO findByName(String name);

    List<CategoryDTO> findAll();
}
