package com.frs.application.repository;

import com.frs.application.domain.Ingredients;
import com.frs.application.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {
    //Mẫu query
    @Query("SELECT r FROM Recipe r WHERE r.title like ?1")
    List<Recipe> findByName(String name);
}
