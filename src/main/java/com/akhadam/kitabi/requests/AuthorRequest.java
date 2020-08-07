package com.akhadam.kitabi.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthorRequest {


    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters")
    @NotNull(message = "Name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
