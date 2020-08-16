package com.akhadam.kitabi.dto;

import java.io.Serializable;

public class AuthorDto implements Serializable {

    private static final long serialVersionUID = 1373806401907518363L;

    private Long id;
    private String name;
    private String authorId;

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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
