package com.learning.atypon.spring.mvc.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookDTO {
    private Long id;

    @NotNull(message = "Title cannot be empty")
    //@NotNull(message = "{title.invalid}")
    @Size(min = 8, message = "Title should have at least 8 characters")
    private String title;

    private String year;

    private Long authorId;

    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
