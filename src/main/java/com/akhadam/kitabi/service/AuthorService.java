package com.akhadam.kitabi.service;

import com.akhadam.kitabi.dto.AuthorDto;
import com.akhadam.kitabi.dto.UserDto;
import com.akhadam.kitabi.entity.AuthorEntity;

import java.util.List;

public interface AuthorService {

    AuthorDto save(AuthorDto author);

    AuthorDto update(String authorId,AuthorDto author);

    void delete(String authorId);

    List<AuthorDto> findByName(String name);

    List<AuthorDto> findAll(int page, int limit);
}
