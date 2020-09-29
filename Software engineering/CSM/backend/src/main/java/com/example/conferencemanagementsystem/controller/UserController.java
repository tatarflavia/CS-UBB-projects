package com.example.conferencemanagementsystem.controller;

import com.example.conferencemanagementsystem.model.User;
import com.example.conferencemanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users/exists", method = RequestMethod.POST)
    ResponseEntity<User> userExists(@RequestBody User user) {
        if (userService.correctUser(user)) {
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    ResponseEntity<User> saveUser(@RequestBody User user) {
        boolean couldAdd = userService.addUser(user);
        if (!couldAdd) {
            return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
