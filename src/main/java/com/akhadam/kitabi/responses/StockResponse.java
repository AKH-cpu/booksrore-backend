package com.akhadam.kitabi.responses;

import com.akhadam.kitabi.dto.BookDto;

public class StockResponse {

    private String stockId;

    private BookResponse book;

    private int quantity;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public BookResponse getBook() {
        return book;
    }

    public void setBook(BookResponse book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
