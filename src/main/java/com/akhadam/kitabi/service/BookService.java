package com.akhadam.kitabi.service;

import com.akhadam.kitabi.dto.BookDto;


import java.util.List;

public interface BookService {

 //   BookDto save(BookDto book, StockDto stock);
    BookDto save(BookDto book);

    List<List<BookDto>> findAll();
}
