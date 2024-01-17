package com.frs.application.repository;

import com.frs.application.domain.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ToolRepository extends JpaRepository<Tool, Long>, JpaSpecificationExecutor<Tool> {
}
