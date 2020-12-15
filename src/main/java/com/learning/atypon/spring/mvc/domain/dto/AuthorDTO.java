package com.learning.atypon.spring.mvc.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class AuthorDTO {
    private Long id;

    @NotNull(message = "First name cannot be empty")
    @Size(min = 5, message = "First name should have at least 5 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be empty")
    @Size(min = 5, message = "Last name should have at least 5 characters")
    private String lastName;

    private List<BookDTO> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
