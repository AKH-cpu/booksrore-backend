package com.akhadam.kitabi.requests;

import javax.validation.constraints.NotBlank;

public class LanguageRequest {

    @NotBlank(message = "name is required")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
