package com.frs.application.repository;

import com.frs.application.domain.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long>, JpaSpecificationExecutor<Ingredients> {
    @Query("SELECT i FROM Ingredients i ORDER BY RAND() LIMIT 5")
    List<Ingredients>findRandomIngredients();

}
