package com.example.conferencemanagementsystem.service;

import com.example.conferencemanagementsystem.model.Review;
import com.example.conferencemanagementsystem.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    
    public void addReview(Review review) { this.reviewRepository.save(review); }
}
