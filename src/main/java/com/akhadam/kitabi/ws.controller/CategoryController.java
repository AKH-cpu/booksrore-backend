package com.akhadam.kitabi.ws.controller;


import com.akhadam.kitabi.entity.CategoryEntity;
import com.akhadam.kitabi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public CategoryEntity save(@RequestBody CategoryEntity category) {
        return categoryService.save(category);
    }

    @GetMapping
    public List<CategoryEntity> findAll() {
        return categoryService.findAll();
    }
}
