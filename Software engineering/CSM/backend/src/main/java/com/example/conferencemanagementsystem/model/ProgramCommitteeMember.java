package com.example.conferencemanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProgramCommitteeMember {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private User user;
    private String name;
    private String affiliation;
    private String email;
    private String webPage;
    private boolean coChair;
    private boolean hasRegistered;
}
