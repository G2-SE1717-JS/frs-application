package com.frs.application.repository;

import com.frs.application.domain.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StepRepository extends JpaRepository<Step, Long>, JpaSpecificationExecutor<Step> {
}
