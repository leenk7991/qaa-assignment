package com.learning.atypon.spring.mvc.controller;

import com.learning.atypon.spring.mvc.domain.dto.BookDTO;
import com.learning.atypon.spring.mvc.domain.enumerator.Category;
import com.learning.atypon.spring.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {
    private static final String BOOKS_TEMPLATE = "/book/books";
    private static final String EDIT_BOOK_TEMPLATE = "/book/editBook";
    private static final String CREATE_BOOK_TEMPLATE = "/book/createBook";

    private static final String BOOKS_ATTR = "books";
    private static final String BOOK_ATTR = "bookForm";

    private static final String BOOKS_URL = "/books";

    @Autowired
    BookService bookService;

    @GetMapping(value = "/books")
    public String findAll(Model model) {
        List<BookDTO> bookList = bookService.findAll();
        model.addAttribute(BOOKS_ATTR, bookList);

        return BOOKS_TEMPLATE;
    }

    @GetMapping(value = "/books/{id}/edit")
    public String edit(@PathVariable("id") Long bookId, Model model) {
        BookDTO bookDTO = bookService.findById(bookId);
        model.addAttribute(BOOK_ATTR, bookDTO);

        return EDIT_BOOK_TEMPLATE;
    }

    @GetMapping(value = "/books/{id}/delete")
    public String delete(@PathVariable("id") Long bookId) {
        bookService.deleteById(bookId);

        return redirect(BOOKS_URL);
    }

    @GetMapping(value = "/books/create")
    public String showCreateView(Model model) {
        model.addAttribute(BOOK_ATTR, new BookDTO());

        return CREATE_BOOK_TEMPLATE;
    }

    @PostMapping(value = "/books")
    public String createOrUpdate(@ModelAttribute(BOOK_ATTR) @Valid BookDTO bookDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(BOOK_ATTR, bookDTO);

            return bookDTO.getId() == null ? CREATE_BOOK_TEMPLATE : EDIT_BOOK_TEMPLATE;
        }
        // TODO: Remove this and add all information to UI as a drop down
        bookDTO.setCategory(Category.COMEDY.name());
        bookDTO.setAuthorId(1L);

        bookService.save(bookDTO);

        return redirect(BOOKS_URL);
    }

    private String redirect(String uri) {
        return String.format("redirect:%s", uri);
    }
}
