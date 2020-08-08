package com.akhadam.kitabi.service;

import com.akhadam.kitabi.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    CategoryEntity save(CategoryEntity category);

    List<CategoryEntity> findAll();
}
