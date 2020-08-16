package com.akhadam.kitabi.repository;

import com.akhadam.kitabi.entity.BookEntity;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {

    BookEntity findByIsbn(String isbn);


}
