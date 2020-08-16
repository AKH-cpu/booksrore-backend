package com.akhadam.kitabi.dto;

import com.akhadam.kitabi.entity.CategoryEntity;

import java.io.Serializable;

public class SubCategoryDto implements Serializable {

    private static final long serialVersionUID = 4175021379084033461L;

    private Long id;
    private String name;
    private CategoryEntity category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
