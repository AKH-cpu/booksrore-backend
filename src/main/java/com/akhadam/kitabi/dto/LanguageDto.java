package com.akhadam.kitabi.dto;



import java.io.Serializable;

public class LanguageDto implements Serializable {

    private static final long serialVersionUID = -6616303185039142155L;
    private Long id;
    private String name;

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

  /*  public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

   */
}
