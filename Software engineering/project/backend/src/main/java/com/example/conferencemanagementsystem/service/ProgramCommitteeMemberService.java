package com.example.conferencemanagementsystem.service;

import com.example.conferencemanagementsystem.exception.MyException;
import com.example.conferencemanagementsystem.model.ProgramCommitteeMember;
import com.example.conferencemanagementsystem.repository.ProgramCommitteeMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProgramCommitteeMemberService {
    @Autowired
    ProgramCommitteeMemberRepository programCommitteeMemberRepository;

    public void addPCMember(ProgramCommitteeMember member) {
        programCommitteeMemberRepository.save(member);
    }
    public void updatePCMember(ProgramCommitteeMember member, int id) {
        Optional<ProgramCommitteeMember> optional = programCommitteeMemberRepository.findByUsername(member.getUser().getUsername(), id);
        optional.ifPresent(m -> {
            m.setUser(member.getUser());
            m.setName(member.getName());
            m.setAffiliation(member.getAffiliation());
            m.setEmail(member.getEmail());
            m.setWebPage(member.getWebPage());
            //m.setCoChair(member.isCoChair());
            m.setHasRegistered(member.isHasRegistered());
            programCommitteeMemberRepository.save(m);
        });
        optional.orElseThrow(() -> {
            try {
                throw new MyException("No such PC member");
            } catch (MyException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
