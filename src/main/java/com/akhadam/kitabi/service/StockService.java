package com.akhadam.kitabi.service;

import com.akhadam.kitabi.dto.BookDto;
import com.akhadam.kitabi.dto.StockDto;


public interface StockService {

    StockDto save(StockDto stock, BookDto book);

    StockDto findByStockId(String stockId );
}
