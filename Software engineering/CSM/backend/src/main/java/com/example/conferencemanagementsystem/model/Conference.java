package com.example.conferencemanagementsystem.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Conference {

    @Id
    @GeneratedValue
    private int id;
    private int numberReviewers;
    private String name;
    @Size(min=10, max=250)
    private String callForPapers;
    private Date firstDay;
    private Date lastDay;
    private Date abstractDeadline;
    private Date fullPaperDeadline;
    private Date biddingDeadline;
    private Date reviewingDeadline;
    private int participationFee;
    @OneToMany
    private List<ProgramCommitteeMember> programCommittee;
    @ManyToMany
    private List<User> steeringCommittee;
    @OneToMany
    private List<Paper> papers;
    @OneToMany
    private List<User> participants;

    public void addParticipant(User user){
        if (!participants.contains(user))
            this.participants.add(user);
    }

    public void addPaper(Paper paper) {
        this.papers.add(paper);
    }
    //TODO: SECTIONS FIELD (ON CLIENT SIDE TOO)

}
