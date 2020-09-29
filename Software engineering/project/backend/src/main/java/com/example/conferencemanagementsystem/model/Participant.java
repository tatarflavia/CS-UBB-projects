package com.example.conferencemanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Participant {
    @Id
    private String id;

    public Participant(String id) {
        this.id = id;
    }

    public Participant() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
