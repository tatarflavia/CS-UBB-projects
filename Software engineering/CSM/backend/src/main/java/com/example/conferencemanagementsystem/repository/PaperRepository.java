package com.example.conferencemanagementsystem.repository;

import com.example.conferencemanagementsystem.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper, Integer> {
}
