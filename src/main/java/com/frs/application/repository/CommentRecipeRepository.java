package com.frs.application.repository;

import com.frs.application.domain.CommentRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRecipeRepository extends JpaRepository<CommentRecipe, Long>, JpaSpecificationExecutor<CommentRecipe> {
}
