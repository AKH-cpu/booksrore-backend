package com.akhadam.kitabi.ws.controller;


import com.akhadam.kitabi.entity.CategoryEntity;
import com.akhadam.kitabi.entity.SubCategoryEntity;
import com.akhadam.kitabi.responses.SubCategoryResponse;
import com.akhadam.kitabi.service.SubCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subcategories")
public class SubCategoryController {

    @Autowired
    SubCategoryService subCategoryService;

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<SubCategoryResponse> save(@RequestBody SubCategoryEntity subCategoryEntity) {
        SubCategoryEntity subCategory = subCategoryService.save(subCategoryEntity, subCategoryEntity.getCategory());
        SubCategoryResponse subCategoryResponse = modelMapper.map(subCategory, SubCategoryResponse.class);
        return new ResponseEntity<SubCategoryResponse>(subCategoryResponse, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<SubCategoryResponse>> findAll() {

        List<SubCategoryResponse> subCategoryResponses = new ArrayList<>();

        List<SubCategoryEntity> subCategoryEntities = subCategoryService.findAll();

        for (SubCategoryEntity subCategoryEntity : subCategoryEntities) {
            SubCategoryResponse subCategoryResponse = modelMapper.map(subCategoryEntity, SubCategoryResponse.class);
            subCategoryResponses.add(subCategoryResponse);
        }

        return new ResponseEntity<List<SubCategoryResponse>>(subCategoryResponses, HttpStatus.OK);
    }

    @GetMapping("/{reference}")
    public List<SubCategoryEntity> findByCategoryReference(@PathVariable String reference) {
        return subCategoryService.findByCategoryReference(reference);
    }
}
