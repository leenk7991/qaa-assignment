package com.learning.atypon.spring.mvc.converter;

import com.learning.atypon.spring.mvc.domain.entity.BookEntity;
import com.learning.atypon.spring.mvc.domain.enumerator.Category;
import com.learning.atypon.spring.mvc.domain.dto.AuthorDTO;
import com.learning.atypon.spring.mvc.domain.entity.AuthorEntity;
import com.learning.atypon.spring.mvc.domain.dto.BookDTO;

public class BookConverter {

    public static BookDTO convert(BookEntity book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setYear(book.getYear());
        // bookDTO.setAuthor(convert(book.getAuthor()));
        bookDTO.setAuthorId((book.getAuthor() != null) ? book.getAuthor().getId() : 0);
        bookDTO.setCategory(book.getCategory().name());

        return bookDTO;
    }

    public static BookEntity convert(BookDTO bookDTO) {
        BookEntity book = new BookEntity();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setYear(bookDTO.getYear());
        //bookDTO.setAuthor(convert(book.getAuthor()));
        book.setCategory(Category.valueOf(bookDTO.getCategory()));

        return book;
    }

    // TODO: Move it to AuthorConverter
    private static AuthorDTO convert(AuthorEntity author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setLastName(author.getLastName());
        authorDTO.setFirstName(author.getFirstName());

        return authorDTO;
    }
}
