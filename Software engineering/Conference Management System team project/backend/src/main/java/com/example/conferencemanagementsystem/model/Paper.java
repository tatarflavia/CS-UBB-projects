package com.example.conferencemanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Paper {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String keywords;
    private String topics;
    private String abstractPaper;
    private String fullPaper;
    private String presentation;
    @OneToMany
    private List<Author> authors;
}
