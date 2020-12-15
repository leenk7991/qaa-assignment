package com.learning.atypon.spring.mvc.repository;

import com.learning.atypon.spring.mvc.domain.entity.AuthorEntity;
import com.learning.atypon.spring.mvc.domain.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {


    List<BookEntity> findAll();

    Page<BookEntity> findAll(Pageable pageable);

    Optional<BookEntity> findById(Long id);

    List<BookEntity> findByTitleContaining(String title);

    Page<BookEntity> findByTitleContaining(String title, Pageable pageable);

    List<BookEntity> findByAuthor(AuthorEntity author);

    List<BookEntity> findByAuthorAndYear(AuthorEntity author, String year);

    List<BookEntity> findByAuthorAndTitleAndYear(AuthorEntity author, String title, String year);

    BookEntity save(BookEntity book);

    void deleteById(Long id);


}
