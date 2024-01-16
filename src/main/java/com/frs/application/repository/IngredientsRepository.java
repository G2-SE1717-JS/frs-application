package com.frs.application.repository;

import com.frs.application.domain.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long>, JpaSpecificationExecutor<Ingredients> {

}
