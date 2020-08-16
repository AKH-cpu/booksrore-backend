package com.akhadam.kitabi.requests;

public class BookRequest {

    private String isbn;
    private String title;
    private String description;
    private String image;
    private Double price;
    private int quantity;
    private SubCategoryRequest subCategory;
    private AuthorRequest author;
    private LanguageRequest language;
    // private StockRequest stock;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public SubCategoryRequest getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryRequest subCategory) {
        this.subCategory = subCategory;
    }

    public AuthorRequest getAuthor() {
        return author;
    }

    public void setAuthor(AuthorRequest author) {
        this.author = author;
    }

    public LanguageRequest getLanguage() {
        return language;
    }

    public void setLanguage(LanguageRequest language) {
        this.language = language;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
