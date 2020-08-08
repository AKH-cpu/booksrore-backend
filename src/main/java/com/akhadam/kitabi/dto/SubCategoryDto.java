package com.akhadam.kitabi.dto;

import com.akhadam.kitabi.entity.CategoryEntity;

import java.io.Serializable;

public class SubCategoryDto implements Serializable {
    private static final long serialVersionUID = 4175021379084033461L;

    String name;

    CategoryEntity category;

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
