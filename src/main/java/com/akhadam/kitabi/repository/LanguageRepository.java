package com.akhadam.kitabi.repository;

import com.akhadam.kitabi.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {

    LanguageEntity findByName(String name);
}
