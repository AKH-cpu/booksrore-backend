package com.akhadam.kitabi.serviceImpl;

import com.akhadam.kitabi.dto.SubCategoryDto;
import com.akhadam.kitabi.entity.CategoryEntity;
import com.akhadam.kitabi.entity.SubCategoryEntity;
import com.akhadam.kitabi.exception.UserException;
import com.akhadam.kitabi.exception.responses.ErrorMessages;
import com.akhadam.kitabi.repository.CategoryRepository;
import com.akhadam.kitabi.repository.SubCategoryRepository;
import com.akhadam.kitabi.service.CategoryService;
import com.akhadam.kitabi.service.SubCategoryService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;


    @Override
    public SubCategoryEntity save(SubCategoryEntity subCategoryEntity, CategoryEntity categoryEntity) {

        SubCategoryEntity foundedSubCategory = subCategoryRepository.findByName(subCategoryEntity.getName());

        if (foundedSubCategory != null) throw new UserException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

        CategoryEntity category = categoryRepository.findByName(categoryEntity.getName());
        if (category == null) throw new RuntimeException("This category doesn't exist ");
        subCategoryEntity.setCategory(category);

        return subCategoryRepository.save(subCategoryEntity);
    }

    @Override
    public List<SubCategoryEntity> findAll() {
        List<SubCategoryEntity> subCategories = subCategoryRepository.findAll();
        return subCategories;
    }

    @Override
    public List<SubCategoryEntity> findByCategoryReference(String reference) {
        return subCategoryRepository.findByCategoryReference(reference);
    }

    @Override
    public int validateSubCategory(@NotNull List<SubCategoryEntity> subCategories) {
        for (int i = 0; i < subCategories.size(); i++) {
            CategoryEntity category = categoryService.findByReference(subCategories.get(i).getCategory().getReference());
            if (category == null) {
                return i;
            } else {
                subCategories.get(i).setCategory(category);
            }

        }

        return -1;
    }

    @Override
    public void save(CategoryEntity category, List<SubCategoryEntity> subCategoryEntities) {
        for (int i = 0; i < subCategoryEntities.size(); i++) {
            SubCategoryEntity subCategory = subCategoryEntities.get(i);
            subCategoryRepository.save(subCategory);
        }
    }

    @Override
    public SubCategoryDto findByName(String name) {
        ModelMapper modelMapper= new ModelMapper();
        SubCategoryEntity subCategoryEntity = subCategoryRepository.findByName(name);
        return modelMapper.map(subCategoryEntity,SubCategoryDto.class);
    }
}
