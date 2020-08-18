package com.akhadam.kitabi.ws.controller;


import com.akhadam.kitabi.dto.BookDto;
import com.akhadam.kitabi.requests.BookRequest;
import com.akhadam.kitabi.responses.BookResponse;
import com.akhadam.kitabi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    ModelMapper modelMapper = new ModelMapper();


    @PostMapping
    public ResponseEntity<BookResponse> save(@RequestBody BookRequest bookRequest) {
        BookDto bookDto = modelMapper.map(bookRequest, BookDto.class);
        BookDto bookDto1 = bookService.save(bookDto);
        BookResponse bookResponse = modelMapper.map(bookDto1, BookResponse.class);
        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                      @RequestParam(value = "limit", defaultValue = "15") int limit) {
        List<BookResponse> bookResponses = new ArrayList<>();
        List<BookDto> bookDtoList = bookService.findAll(page, limit);

        for (BookDto bookDto : bookDtoList) {
            BookResponse bookResponse = modelMapper.map(bookDto, BookResponse.class);
            bookResponses.add(bookResponse);
        }
        return new ResponseEntity<>(bookResponses, HttpStatus.OK);
    }

    @GetMapping("/bestsellers")
    public ResponseEntity<List<BookResponse>> findBySalesGreaterThanFive() {
        List<BookResponse> bookResponses = new ArrayList<>();
        List<BookDto> bookDtoList = bookService.findBySalesGreaterThanFive();
        for (BookDto bookDto : bookDtoList) {
            BookResponse bookResponse = modelMapper.map(bookDto, BookResponse.class);
            bookResponses.add(bookResponse);
        }
        return new ResponseEntity<>(bookResponses, HttpStatus.OK);

    }


    @PutMapping("/update/{isbn}")
    public ResponseEntity<BookResponse> update(@PathVariable String isbn, @RequestBody BookRequest bookRequest) {

        BookDto bookDto = modelMapper.map(bookRequest, BookDto.class);

        BookDto updatedBook = bookService.update(isbn, bookDto);

        BookResponse bookResponse = modelMapper.map(updatedBook, BookResponse.class);

        return new ResponseEntity<>(bookResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponse> findByIsbn(@PathVariable String isbn) {

        BookDto bookDto = bookService.findByIsbn(isbn);

        BookResponse bookResponse = modelMapper.map(bookDto, BookResponse.class);

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

}
