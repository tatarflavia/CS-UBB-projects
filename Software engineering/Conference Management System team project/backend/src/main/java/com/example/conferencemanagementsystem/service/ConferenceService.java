package com.example.conferencemanagementsystem.service;

import com.example.conferencemanagementsystem.exception.MyException;
import com.example.conferencemanagementsystem.model.*;
import com.example.conferencemanagementsystem.model.validator.ConferenceValidator;
import com.example.conferencemanagementsystem.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConferenceService {
    @Autowired
    ConferenceRepository conferenceRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProgramCommitteeMemberService programCommitteeMemberService;

    @Autowired
    ConferenceValidator conferenceValidator;

    @Autowired
    AuthorService authorService;

    @Autowired
    PaperService paperService;

    public List<Conference> getConferences() {
        return conferenceRepository.findAll();
    }

    public void addConference(Conference conference) throws MyException {
        conferenceValidator.validateConference(conference, userService);
        List<ProgramCommitteeMember> PCMembers=conference.getProgramCommittee();
        for (ProgramCommitteeMember member: PCMembers) {
            member.setUser(userService.getUser(member.getUser().getUsername()));
            programCommitteeMemberService.addPCMember(member);
        }
        conference.setProgramCommittee(PCMembers);
        List<User> SCMembers = conference.getSteeringCommittee();
        for (int i=0; i<SCMembers.size();i++) {
            SCMembers.set(i, userService.getUser(SCMembers.get(i).getUsername()));
        }
        List<User> participants = new ArrayList<>();
        conference.setParticipants(participants);
        conference.setSteeringCommittee(SCMembers);
        conferenceRepository.save(conference);
    }

    public void registerPcMember(ProgramCommitteeMember programCommitteeMember, int id) throws MyException {
        programCommitteeMemberService.updatePCMember(programCommitteeMember, id);
    }

    @Transactional
    public void addPaper(int id, Paper paper) throws MyException {
        conferenceValidator.validatePaper(paper, userService, authorService.getAll());
        for (Author author: paper.getAuthors()) {
            authorService.addAuthor(author);
        }
        paperService.addPaper(paper);
        Conference conference = conferenceRepository.getOne(id);
        conference.addPaper(paper);
    }

    @Transactional
    public void addParticipant(int id, User user) throws MyException {
        Conference conference = conferenceRepository.getOne(id);
        conference.addParticipant(user);
    }
}
