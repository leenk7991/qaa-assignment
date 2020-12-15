package com.learning.atypon.spring.mvc.service;

import com.learning.atypon.spring.mvc.repository.AuthorRepository;
import com.learning.atypon.spring.mvc.domain.entity.AuthorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public AuthorEntity findById(Long id) {
        AuthorEntity authorEntity = authorRepository.findById(id).get();
        return authorEntity;
    }

    @Override
    public Optional<AuthorEntity> findAuthorByFirstNameAndLastName(String firstName, String lastName) {
        return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName);
    }


}
