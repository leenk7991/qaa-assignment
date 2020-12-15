package com.learning.atypon.spring.mvc.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

@Entity
@Table(name = "AUTHOR", uniqueConstraints = {@UniqueConstraint(columnNames = {"firstname", "lastname"})})
public class AuthorEntity {

    private static final int MAX_NAME_LENGTH = 60;

    @Id
    @Column(name = "author_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", length = MAX_NAME_LENGTH)
    private String firstName;

    @Column(name = "lastname", length = MAX_NAME_LENGTH)
    private String lastName;

    @OneToMany(mappedBy = "author", targetEntity = BookEntity.class)
    private List<BookEntity> books;

    public AuthorEntity(String firstName, String lastName, List<BookEntity> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }

    public AuthorEntity() {
    }

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

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
