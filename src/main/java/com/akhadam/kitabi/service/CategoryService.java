package com.akhadam.kitabi.service;

import com.akhadam.kitabi.entity.CategoryEntity;
import com.akhadam.kitabi.entity.SubCategoryEntity;

import java.util.List;

public interface CategoryService {

    CategoryEntity save(CategoryEntity category);

    List<CategoryEntity> findAll();

    CategoryEntity findByReference(String reference);

    int save(CategoryEntity category, List<SubCategoryEntity> subCategories);
}
