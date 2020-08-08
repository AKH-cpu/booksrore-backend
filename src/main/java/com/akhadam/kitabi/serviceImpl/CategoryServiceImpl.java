package com.akhadam.kitabi.serviceImpl;

import com.akhadam.kitabi.entity.CategoryEntity;
import com.akhadam.kitabi.exception.UserException;
import com.akhadam.kitabi.exception.responses.ErrorMessages;
import com.akhadam.kitabi.repository.CategoryRepository;
import com.akhadam.kitabi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

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
}
