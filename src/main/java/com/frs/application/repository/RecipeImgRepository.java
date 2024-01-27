package com.frs.application.repository;

import com.frs.application.domain.RecipeImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RecipeImgRepository extends JpaRepository<RecipeImg, Long>, JpaSpecificationExecutor<RecipeImg> {
}
