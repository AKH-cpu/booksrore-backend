package com.akhadam.kitabi.repository;


import com.akhadam.kitabi.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {

    SubCategoryEntity findByName(String name);

    List<SubCategoryEntity> findByCategoryReference(String reference);
}
