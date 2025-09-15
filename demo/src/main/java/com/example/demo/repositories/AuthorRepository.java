package com.example.demo.repositories;

import com.example.demo.domain.AuthorEntity;
import com.example.demo.domain.dto.AuthorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
