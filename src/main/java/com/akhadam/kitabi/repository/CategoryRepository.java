package com.akhadam.kitabi.repository;

import com.akhadam.kitabi.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByName(String name);
}
