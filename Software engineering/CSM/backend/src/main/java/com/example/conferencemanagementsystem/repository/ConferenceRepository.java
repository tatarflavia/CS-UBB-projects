package com.example.conferencemanagementsystem.repository;

import com.example.conferencemanagementsystem.model.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {
}
