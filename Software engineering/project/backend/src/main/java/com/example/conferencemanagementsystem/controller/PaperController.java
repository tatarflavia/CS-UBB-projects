package com.example.conferencemanagementsystem.controller;

import com.example.conferencemanagementsystem.exception.MyException;
import com.example.conferencemanagementsystem.model.Message;
import com.example.conferencemanagementsystem.model.Paper;
import com.example.conferencemanagementsystem.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaperController {
    @Autowired
    PaperService paperService;

    @RequestMapping(value = "/papers/{id}/updatePaper", method = RequestMethod.POST)
    ResponseEntity<Message> addPaper(@PathVariable int id, @RequestBody Paper paper) {
        paperService.updatePaper(id, paper);
        return new ResponseEntity<>(new Message("okay"), HttpStatus.OK);
    }
}
