package com.akhadam.kitabi.ws.controller;


import com.akhadam.kitabi.entity.CategoryEntity;
import com.akhadam.kitabi.responses.CategoryResponse;
import com.akhadam.kitabi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public CategoryEntity save(@RequestBody CategoryEntity category) {
        return categoryService.save(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {

        List<CategoryResponse> categoryResponses = new ArrayList<>();
        List<CategoryEntity> categoryEntities = categoryService.findAll();

        for (CategoryEntity categoryEntity : categoryEntities) {
            CategoryResponse categoryResponse = modelMapper.map(categoryEntity, CategoryResponse.class);
            categoryResponses.add(categoryResponse);
        }
        return new ResponseEntity<List<CategoryResponse>>(categoryResponses, HttpStatus.OK);
    }
}
