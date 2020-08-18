package com.akhadam.kitabi.service;

import com.akhadam.kitabi.dto.BookDto;


import java.util.List;

public interface BookService {

    BookDto save(BookDto book);

    BookDto update(String isbn, BookDto book);

    void delete(String isbn);

    BookDto findByIsbn(String isbn);

    List<BookDto> findAll(int page, int limit);

    List<BookDto> findBySalesGreaterThanFive();


}
