package com.akhadam.kitabi.repository;

import com.akhadam.kitabi.entity.BookEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    BookEntity findByIsbn(String isbn);

    @Query(value = "SELECT * FROM books WHERE sales > 5", nativeQuery = true)
    List<BookEntity> findBySalesGreaterThanFive();

}
