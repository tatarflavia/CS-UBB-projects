package com.example.conferencemanagementsystem.controller;

import com.example.conferencemanagementsystem.exception.MyException;
import com.example.conferencemanagementsystem.model.Message;
import com.example.conferencemanagementsystem.model.Review;
import com.example.conferencemanagementsystem.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @RequestMapping(value = "/reviews", method = RequestMethod.POST)
    ResponseEntity<Message> saveReview(@RequestBody Review review) {
        this.reviewService.addReview(review);
        return new ResponseEntity<>(new Message("okay"), HttpStatus.OK);
    }
}
