package com.example.conferencemanagementsystem.repository;

import com.example.conferencemanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
