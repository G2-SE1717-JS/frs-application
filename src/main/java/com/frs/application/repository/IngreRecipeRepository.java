package com.frs.application.repository;

import com.frs.application.domain.IngreRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IngreRecipeRepository extends JpaRepository<IngreRecipe, Long>, JpaSpecificationExecutor<IngreRecipe> {
}
