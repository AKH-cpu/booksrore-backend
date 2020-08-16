package com.akhadam.kitabi.dto;

import java.io.Serializable;

public class StockDto implements Serializable {

    private static final long serialVersionUID = 2416363227445960138L;

    private Long id;
    private String stockId;
    private int quantity;
    private BookDto book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }
}
