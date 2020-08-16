package com.akhadam.kitabi.service;

import com.akhadam.kitabi.dto.BookDto;


import java.util.List;

public interface BookService {

    BookDto save(BookDto book);

    List<BookDto> findAll(int page, int limit);
}
