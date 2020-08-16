package com.akhadam.kitabi.serviceImpl;

import com.akhadam.kitabi.entity.CategoryEntity;
import com.akhadam.kitabi.entity.SubCategoryEntity;
import com.akhadam.kitabi.exception.UserException;
import com.akhadam.kitabi.exception.responses.ErrorMessages;
import com.akhadam.kitabi.repository.CategoryRepository;
import com.akhadam.kitabi.service.CategoryService;
import com.akhadam.kitabi.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubCategoryService subCategoryService;

    @Override
    public CategoryEntity save(CategoryEntity category) {
        CategoryEntity foundedCategory = categoryRepository.findByName(category.getName());
        if (foundedCategory != null) throw new UserException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        CategoryEntity savedCategory = categoryRepository.save(category);

        return savedCategory;
    }

    @Override
    public List<CategoryEntity> findAll() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories;
    }

    @Override
    public CategoryEntity findByReference(String reference) {
        return categoryRepository.findByReference(reference);
    }

    @Override
    public int save(CategoryEntity category, List<SubCategoryEntity> subCategories) {
       int validate = subCategoryService.validateSubCategory(subCategories);
        if (validate == -1) {
            CategoryEntity savedCategory = categoryRepository.save(category);
            subCategoryService.save(category, subCategories);
            return 1;

       }
        return -1;

    }
}
