package com.akhadam.kitabi.serviceImpl;


import com.akhadam.kitabi.dto.AuthorDto;
import com.akhadam.kitabi.dto.UserDto;
import com.akhadam.kitabi.entity.AuthorEntity;
import com.akhadam.kitabi.entity.UserEntity;
import com.akhadam.kitabi.exception.UserException;
import com.akhadam.kitabi.exception.responses.ErrorMessages;
import com.akhadam.kitabi.repository.AuthorRepository;
import com.akhadam.kitabi.service.AuthorService;
import com.akhadam.kitabi.shared.Utils;
import org.apache.tomcat.jni.Error;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    Utils utils;

    ModelMapper modelMapper = new ModelMapper();


    @Override
    public AuthorDto save(AuthorDto author) {
        AuthorEntity foundedAuthor = authorRepository.findByAuthorId(author.getAuthorId());
        if (foundedAuthor != null) throw new RuntimeException("User Already exists");

        AuthorEntity authorEntity = modelMapper.map(author, AuthorEntity.class);
        authorEntity.setAuthorId(utils.generateId(30));
        AuthorEntity savedAuthor = authorRepository.save(authorEntity);
        return modelMapper.map(savedAuthor, AuthorDto.class);
    }

    @Override
    public AuthorDto update(String authorId, AuthorDto author) {
        AuthorEntity foundedAuthor = authorRepository.findByAuthorId(authorId);
        if (foundedAuthor == null) throw new UserException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        foundedAuthor.setName(author.getName());
        AuthorEntity updatedAuthor = authorRepository.save(foundedAuthor);
        return modelMapper.map(updatedAuthor, AuthorDto.class);
    }

    @Override
    public void delete(String authorId) {
        AuthorEntity foundedAuthor =  authorRepository.findByAuthorId(authorId);
        if( foundedAuthor == null ) throw  new UserException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        authorRepository.delete(foundedAuthor);
    }

    @Override
    public List<AuthorDto> findByName(String name) {
        return null;
    }


    @Override
    public List<AuthorDto> findAll(int page, int limit) {
        if (page > 0) page -= 1;

        List<AuthorDto> authorDtoList = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, limit);
        Page<AuthorEntity> authorEntityPage = authorRepository.findAll(pageable);

        List<AuthorEntity> authorEntityList = authorEntityPage.getContent();

        for (AuthorEntity authorEntity : authorEntityList) {
            AuthorDto authorDto = modelMapper.map(authorEntity, AuthorDto.class);
            authorDtoList.add(authorDto);
        }

        return authorDtoList;
    }
}
