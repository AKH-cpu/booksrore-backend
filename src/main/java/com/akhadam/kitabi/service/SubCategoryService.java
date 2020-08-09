package com.akhadam.kitabi.service;


import com.akhadam.kitabi.entity.CategoryEntity;
import com.akhadam.kitabi.entity.SubCategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SubCategoryService {

    SubCategoryEntity save(SubCategoryEntity subCategoryEntity, CategoryEntity categoryEntity);

    List<SubCategoryEntity> findAll();

    List<SubCategoryEntity> findByCategoryReference(String reference);
}
