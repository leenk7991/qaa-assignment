package com.learning.atypon.spring.mvc.domain.enumerator;

public enum Category {
    DEFAULT("Undefined"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    HORROR_FICTION("Horror Fiction"),
    LITERARY_REALISM("Literary Realism"),
    ROMANCE("Romance"),
    SATIRE("Satire"),
    TRAGEDY("Tragedy"),
    TRAGICOMEDY("Tragicomedy"),
    FANTASY("Fantasy"),
    MYTHOLOGY("Mythology");

    private String fullName;

    Category(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

}
