package com.akhadam.kitabi.repository;

import com.akhadam.kitabi.entity.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    Page<AuthorEntity> findByName(Pageable pageRequest, String name);

    AuthorEntity findByAuthorId(String authorId);

}
