package com.example.conferencemanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
