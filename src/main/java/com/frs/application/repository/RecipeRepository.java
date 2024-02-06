package com.frs.application.repository;

import com.frs.application.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {
    //Mẫu query
    @Query("SELECT r FROM Recipe r WHERE r.title like ?1")
    List<Recipe> findByName(String name);

    @Query("SELECT DATE(r.createdDate), COUNT(r) FROM Recipe r WHERE DATE(r.createdDate) >= ?1 AND DATE(r.createdDate) < ?2 GROUP BY DATE(r.createdDate) ORDER BY DATE(r.createdDate) ASC")
    List<Object[]> countRecipesByDateRange(LocalDate startDate, LocalDate endDate);
}
