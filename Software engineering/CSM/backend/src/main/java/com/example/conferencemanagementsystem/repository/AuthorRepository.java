package com.example.conferencemanagementsystem.repository;

import com.example.conferencemanagementsystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
