package com.frs.application.repository;

import com.frs.application.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRespsitory extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

}
