package com.akhadam.kitabi.service;

import com.akhadam.kitabi.dto.LanguageDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LanguageService {

    LanguageDto save(LanguageDto languageDto);

    List<LanguageDto> findAll();

    LanguageDto update(LanguageDto languageDto);

    LanguageDto findByName(String name);

    void delete(String name);


}

