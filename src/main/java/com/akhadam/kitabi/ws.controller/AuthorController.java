package com.akhadam.kitabi.ws.controller;

import com.akhadam.kitabi.dto.AuthorDto;
import com.akhadam.kitabi.entity.AuthorEntity;
import com.akhadam.kitabi.exception.UserException;
import com.akhadam.kitabi.exception.responses.ErrorMessages;
import com.akhadam.kitabi.requests.AuthorRequest;
import com.akhadam.kitabi.responses.AuthorResponse;
import com.akhadam.kitabi.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<AuthorResponse> save(@RequestBody AuthorRequest authorRequest) {

        if (authorRequest.getName().isEmpty())
            throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        AuthorDto authorDto = modelMapper.map(authorRequest, AuthorDto.class);
        AuthorDto savedAuthor = authorService.save(authorDto);
        AuthorResponse authorResponse = modelMapper.map(savedAuthor, AuthorResponse.class);

        return new ResponseEntity<AuthorResponse>(authorResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                        @RequestParam(value = "limit", defaultValue = "15") int limit) {

        List<AuthorResponse> authorResponses = new ArrayList<AuthorResponse>();
        List<AuthorDto> authorDtos = authorService.findAll(page, limit);
        for (AuthorDto authorDto : authorDtos) {
            AuthorResponse authorResponse = modelMapper.map(authorDto, AuthorResponse.class);
            authorResponses.add(authorResponse);
        }

        return new ResponseEntity<List<AuthorResponse>>(authorResponses, HttpStatus.OK);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorResponse> update(@PathVariable String authorId, @RequestBody AuthorRequest authorRequest) {

        AuthorDto authorDto = modelMapper.map(authorRequest, AuthorDto.class);
        AuthorDto authorDtoUpdated = authorService.update(authorId, authorDto);
        AuthorResponse authorResponse = modelMapper.map(authorDtoUpdated, AuthorResponse.class);

        return new ResponseEntity<AuthorResponse>(authorResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Object> delete(@PathVariable String authorId) {
        authorService.delete(authorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
