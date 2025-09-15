package com.example.demo.service.impl;

import com.example.demo.domain.AuthorEntity;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.service.AuthorService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity) {
        Optional<AuthorEntity> optionalAuthorEntity = authorRepository.findById(id);
        if(optionalAuthorEntity.isPresent()) {
            AuthorEntity existingAuthor = optionalAuthorEntity.get();
            if(authorEntity.getName() != null) {
                existingAuthor.setName(authorEntity.getName());
            }
            if(authorEntity.getAge() != null) {
                existingAuthor.setAge(authorEntity.getAge());
            }
            return authorRepository.save(existingAuthor);
        }
        return null;
    }

    @Override
    public AuthorEntity deleteAuthor(Long id) {
        Optional<AuthorEntity> optionalAuthorEntity = authorRepository.findById(id);
        if(optionalAuthorEntity.isPresent()) {
            AuthorEntity authorToDelete = optionalAuthorEntity.get();
            authorRepository.delete(authorToDelete);
            return authorToDelete;
        }
        return null;
    }
}
