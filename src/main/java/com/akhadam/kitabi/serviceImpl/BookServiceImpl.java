package com.akhadam.kitabi.serviceImpl;

import com.akhadam.kitabi.dto.*;
import com.akhadam.kitabi.entity.BookEntity;
import com.akhadam.kitabi.exception.responses.ErrorMessages;
import com.akhadam.kitabi.repository.BookRepository;
import com.akhadam.kitabi.service.*;
import com.akhadam.kitabi.shared.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    LanguageService languageService;

    @Autowired
    SubCategoryService subCategoryService;

    @Autowired
    StockService stockService;

    @Autowired
    Utils utils;

    @Override
    public BookDto save(BookDto bookDto) {

        ModelMapper modelMapper = new ModelMapper();

        BookEntity foundedBook = bookRepository.findByIsbn(bookDto.getIsbn());

        AuthorDto foundedAuthor = authorService.findByName(bookDto.getAuthor().getName());
        SubCategoryDto foundedSubCategory = subCategoryService.findByName(bookDto.getSubCategory().getName());
        LanguageDto foundedLanguage = languageService.findByName(bookDto.getLanguage().getName());


        if (foundedBook != null) {
            throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        } else if (foundedAuthor == null) {
            throw new RuntimeException("this author doesn't exist");
        } else if (foundedSubCategory == null) {
            throw new RuntimeException("this category doesn't exist");
        } else if (foundedLanguage == null) {
            throw new RuntimeException("this language doesn't exist");
        } else {

            bookDto.setBookId(utils.generateId(30));
            bookDto.setAuthor(foundedAuthor);
            bookDto.setLanguage(foundedLanguage);
            bookDto.setSubCategory(foundedSubCategory);

            BookEntity bookEntity = modelMapper.map(bookDto, BookEntity.class);
            BookEntity newBook = bookRepository.save(bookEntity);

            return modelMapper.map(newBook, BookDto.class);
        }
    }

    @Override
    public List<List<BookDto>> findAll() {
        return null;
    }
}
