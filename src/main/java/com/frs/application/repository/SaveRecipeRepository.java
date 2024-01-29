package com.frs.application.repository;

import com.frs.application.domain.SaveRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SaveRecipeRepository extends JpaRepository<SaveRecipe, Long>, JpaSpecificationExecutor<SaveRecipe> {
}
