package com.example.conferencemanagementsystem.repository;

import com.example.conferencemanagementsystem.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
