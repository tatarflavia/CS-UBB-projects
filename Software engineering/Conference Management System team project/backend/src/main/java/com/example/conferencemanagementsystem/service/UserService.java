package com.example.conferencemanagementsystem.service;

import com.example.conferencemanagementsystem.model.User;
import com.example.conferencemanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUser(String username) {
        return userRepository.findById(username).get();
    }

    public boolean userExists(User user) {
        Optional<User> foundUser = userRepository.findById(user.getUsername());
        AtomicBoolean answer = new AtomicBoolean(false);
        foundUser.ifPresentOrElse(u -> {
            answer.set(true);}, () -> {
            answer.set(false);
        });
        return answer.get();
    }

    public boolean addUser(User user) {
        if (this.userExists(user)) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public boolean correctUser(User user) {
        if (!this.userExists(user)) {
            return false;
        }
        User foundUser = userRepository.findById(user.getUsername()).get();
        return foundUser.getPassword().equals(user.getPassword());
    }
}
