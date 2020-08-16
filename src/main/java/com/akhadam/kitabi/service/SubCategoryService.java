package com.akhadam.kitabi.service;


import com.akhadam.kitabi.dto.SubCategoryDto;
import com.akhadam.kitabi.entity.CategoryEntity;
import com.akhadam.kitabi.entity.SubCategoryEntity;


import java.util.List;

public interface SubCategoryService {

    SubCategoryEntity save(SubCategoryEntity subCategoryEntity, CategoryEntity categoryEntity);

    List<SubCategoryEntity> findAll();

    List<SubCategoryEntity> findByCategoryReference(String reference);

    int validateSubCategory(List<SubCategoryEntity> subCategories);

    void save(CategoryEntity category,List<SubCategoryEntity> subCategoryEntities);

    SubCategoryDto findByName(String name);
}
