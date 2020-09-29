package com.example.conferencemanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private int id;
    private int grade; // from 1 to 7
    private String review;
    @OneToOne
    private Paper paper;
    @OneToOne
    private ProgramCommitteeMember programCommitteeMember;
}
