package com.frs.application.repository;

import com.frs.application.domain.StepImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StepImgRepository extends JpaRepository<StepImg, Long>, JpaSpecificationExecutor<StepImg> {
}
