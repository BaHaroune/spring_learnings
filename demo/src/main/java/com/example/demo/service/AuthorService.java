package com.example.demo.service;

import com.example.demo.domain.AuthorEntity;
import com.example.demo.domain.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);
    List<AuthorEntity> getAuthors();
    Optional<AuthorEntity> findOne(Long id);

    public AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity);

    AuthorEntity deleteAuthor(Long id);
}
