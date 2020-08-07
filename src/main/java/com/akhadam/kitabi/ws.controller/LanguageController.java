package com.akhadam.kitabi.ws.controller;


import com.akhadam.kitabi.dto.LanguageDto;
import com.akhadam.kitabi.requests.LanguageRequest;
import com.akhadam.kitabi.responses.LanguageResponse;
import com.akhadam.kitabi.service.LanguageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    LanguageService languageService;


    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LanguageResponse> save(@RequestBody  LanguageRequest languageRequest) {
        ModelMapper modelMapper = new ModelMapper();
        LanguageDto languageDto = modelMapper.map(languageRequest, LanguageDto.class);
        LanguageDto language = languageService.save(languageDto);
        LanguageResponse languageResponse = modelMapper.map(language, LanguageResponse.class);
        return new ResponseEntity<LanguageResponse>(languageResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LanguageResponse>> findAll() {

        ModelMapper modelMapper = new ModelMapper();
        List<LanguageResponse> languageResponses = new ArrayList<>();

        List<LanguageDto> languageDtos = languageService.findAll();

        for (LanguageDto languageDto : languageDtos) {
            LanguageResponse languageResponse = modelMapper.map(languageDto, LanguageResponse.class);
            languageResponses.add(languageResponse);
        }
        return new ResponseEntity<List<LanguageResponse>>(languageResponses, HttpStatus.OK);
    }

    public LanguageDto update(LanguageDto languageDto) {
        return languageService.update(languageDto);
    }

    public LanguageDto findByName(String name) {
        return languageService.findByName(name);
    }

    public void delete(String name) {
        languageService.delete(name);
    }
}
