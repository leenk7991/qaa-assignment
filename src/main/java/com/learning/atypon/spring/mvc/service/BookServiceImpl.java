package com.learning.atypon.spring.mvc.service;

import com.learning.atypon.spring.mvc.domain.entity.BookEntity;
import com.learning.atypon.spring.mvc.repository.BookRepository;
import com.learning.atypon.spring.mvc.converter.BookConverter;
import com.learning.atypon.spring.mvc.domain.entity.AuthorEntity;
import com.learning.atypon.spring.mvc.domain.dto.BookDTO;
import com.learning.atypon.spring.mvc.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Override
    public BookDTO findById(Long id) {
        BookEntity book;
        BookDTO bookDTO;
        try {
            book = bookRepository.findById(id).get();
        } catch (NoSuchElementException nsee) {
            throw new BookNotFoundException("Book not found", nsee.getCause());
        }
        bookDTO = BookConverter.convert(book);

        return bookDTO;
    }

    @Override
    public List<BookDTO> findAll() {
        // TODO: Add null check
        return bookRepository.findAll().stream()
                .map(BookConverter::convert)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookEntity> findByAuthor(AuthorEntity author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<BookEntity> findByAuthorAndYear(AuthorEntity author, String year) {
        return bookRepository.findByAuthorAndYear(author, year);
    }

    @Override
    public List<BookDTO> findByTitle(String title) {
        return bookRepository.findByTitleContaining(title).stream()
                .map(BookConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {
        BookEntity book = BookConverter.convert(bookDTO);
        if (bookDTO.getAuthorId() != null) {
            AuthorEntity author = authorService.findById(bookDTO.getAuthorId());
            book.setAuthor(author);
        }

        bookRepository.save(book);

        return BookConverter.convert(book);
    }


}
