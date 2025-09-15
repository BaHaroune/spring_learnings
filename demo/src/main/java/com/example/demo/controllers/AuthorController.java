package com.example.demo.controllers;

import com.example.demo.domain.AuthorEntity;
import com.example.demo.domain.dto.AuthorDto;
import com.example.demo.mappers.impl.AuthorMapper;
import com.example.demo.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class AuthorController {
    private AuthorService authorService;
    private AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path = "/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        try {
            AuthorEntity authorEntity = authorMapper.toEntity(authorDto);
            AuthorEntity saved = authorService.createAuthor(authorEntity);
            return authorMapper.toDto(saved);
        } catch (Exception e) {
            log.error("Erreur lors de la création de l'auteur: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Accès non autorisé", e);
        }
    }

    @GetMapping(path = "authors/all")
    public List<AuthorDto> getAuthors() {
        List<AuthorEntity> authors = authorService.getAuthors();
        List<AuthorDto> authorDtos = new ArrayList<>();
        if(authors.isEmpty()) {
            return authorDtos;
        } else {
            for(AuthorEntity author : authors) {
                authorDtos.add(authorMapper.toDto(author));
            }
        }
        return authorDtos;
    }

    @GetMapping(path = "authors/{id}")
    public ResponseEntity<AuthorDto> findAuthorById(@PathVariable Long id) {
        Optional<AuthorEntity> foundAuthor = authorService.findOne(id);
        if(foundAuthor.isPresent()) {
            AuthorDto authorDto = authorMapper.toDto(foundAuthor.get());
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/authors")
    public List<AuthorDto> getAllAuthors() {
        List<AuthorEntity> authors = authorService.getAuthors();
        List<AuthorDto> authorDtos = new ArrayList<>();
        for (AuthorEntity a : authors){
            authorDtos.add(authorMapper.toDto(a));
        }
        return authorDtos;
    }

    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<String> UpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        AuthorEntity authorEntity = authorService.partialUpdate(id, authorMapper.toEntity(authorDto));
        if(authorEntity != null) {
            return new ResponseEntity<>("Author updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping (path = "/authors/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long id) {
        AuthorEntity authorEntity = authorService.deleteAuthor(id);
        if (authorEntity != null) {
            return new ResponseEntity<>("Author deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
        }
    }
}
