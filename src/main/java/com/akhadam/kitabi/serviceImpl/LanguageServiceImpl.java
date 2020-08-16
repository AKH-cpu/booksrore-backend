package com.akhadam.kitabi.serviceImpl;

import com.akhadam.kitabi.dto.LanguageDto;
import com.akhadam.kitabi.entity.LanguageEntity;
import com.akhadam.kitabi.repository.LanguageRepository;
import com.akhadam.kitabi.service.LanguageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    ModelMapper modelMapper = new ModelMapper();


    @Override
    public LanguageDto save(LanguageDto languageDto) {
        ModelMapper modelMapper = new ModelMapper();

        LanguageEntity foundedLanguage = languageRepository.findByName(languageDto.getName());
        if (foundedLanguage != null) throw new RuntimeException("Language already exist");

        LanguageEntity language = modelMapper.map(languageDto, LanguageEntity.class);
        LanguageEntity savedLanguage = languageRepository.save(language);
        return modelMapper.map(savedLanguage, LanguageDto.class);
    }

    @Override
    public List<LanguageDto> findAll() {

        List<LanguageDto> languageDtoList = new ArrayList<>();
        List<LanguageEntity> languages = languageRepository.findAll();

        for (LanguageEntity languageEntity : languages) {
            LanguageDto languageDto = modelMapper.map(languageEntity, LanguageDto.class);
            languageDtoList.add(languageDto);
        }

        return languageDtoList;
    }

    @Override
    public LanguageDto update(LanguageDto languageDto) {
        return null;
    }

    @Override
    public LanguageDto findByName(String name) {
        LanguageEntity foundedLanguage = languageRepository.findByName(name);
        if (foundedLanguage == null) throw new RuntimeException("Record not found");
        return modelMapper.map(foundedLanguage, LanguageDto.class);
    }

    @Override
    public void delete(String name) {

    }
}
