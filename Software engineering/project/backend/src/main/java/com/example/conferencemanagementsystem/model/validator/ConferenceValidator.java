package com.example.conferencemanagementsystem.model.validator;

import com.example.conferencemanagementsystem.exception.MyException;
import com.example.conferencemanagementsystem.model.*;
import com.example.conferencemanagementsystem.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ConferenceValidator {
    public void validateConference(Conference conference, UserService userService) throws MyException {
        String errorMessage="";
        String name=conference.getName();
        if (name==null || name.length()==0) {
            errorMessage+="The conference name cannot be empty. \n";
        }
        String callForPapers=conference.getCallForPapers();
        if (callForPapers==null || callForPapers.length()<10) {
            errorMessage+="The call for papers must be at least 10 characters long. \n";
            throw new MyException(errorMessage);
        }
        if (callForPapers.length()>250) {
            errorMessage+="The call for papers must be at most 250 characters long. \n";
        }
        Date firstDay=conference.getFirstDay();
        Date lastDay=conference.getLastDay();
        Date abstractDeadline=conference.getAbstractDeadline();
        Date fullPaperDeadline=conference.getFullPaperDeadline();
        Date biddingDeadline=conference.getBiddingDeadline();
        Date reviewingDeadline=conference.getReviewingDeadline();
        if (firstDay == null || lastDay == null || abstractDeadline == null || fullPaperDeadline == null || biddingDeadline == null || reviewingDeadline == null) {
            errorMessage+="All dates must be specified. \n";
            throw new MyException(errorMessage);
        }
        if (lastDay.before(firstDay)) {
            errorMessage+="The last day of the conference cannot be before the first day. \n";
        }
        if(fullPaperDeadline.before(abstractDeadline)) {
            errorMessage+="The deadline for the full paper cannot be before the deadline for the abstract paper. \n";
        }
        if(!biddingDeadline.after(abstractDeadline)) {
            errorMessage+="The bidding deadline must be after the abstract paper submission deadline. \n";
        }
        if(!reviewingDeadline.after(biddingDeadline)) {
            errorMessage+="The reviewing deadline must be after the bidding deadline. \n";
        }
        if(!reviewingDeadline.after(fullPaperDeadline)) {
            errorMessage+="The reviewing deadline must be after the full paper submission deadline. \n";
        }
        if(!reviewingDeadline.before(firstDay)) {
            errorMessage+="The reviewing deadline must be before the first day of the conference. \n";
        }
        int participationFee=conference.getParticipationFee();
        System.out.println("fee "+participationFee);
        if(participationFee<0) {
            errorMessage+="The participation fee must be non-negative. \n";
        }
        List<ProgramCommitteeMember> programCommitteeMemberList=conference.getProgramCommittee();
        for (ProgramCommitteeMember member: programCommitteeMemberList) {
            if(!userService.userExists(member.getUser())) {
                errorMessage+="User "+member.getUser().getUsername()+" does not exist. \n";
            }
        }
        if (programCommitteeMemberList.size()<5) {
            errorMessage+="There must be at least 5 Program Committee members. \n";
        }
        List<User> steeringCommittee=conference.getSteeringCommittee();
        for (User user: steeringCommittee) {
            if (!userService.userExists(user)) {
                errorMessage+="User "+user.getUsername()+" does not exist. \n";
            }
        }
        if (errorMessage.length()>0) {
            throw new MyException(errorMessage);
        }
    }

    public void validatePaper(Paper paper, UserService userService, List<Author> existingAuthors) throws MyException {
        System.out.println(paper);
        String errorMessage = "";
        if (paper.getTitle()==null || paper.getTitle().length()==0) {
            errorMessage+="The paper must have a title.\n";
        }
        List<Author> authors = paper.getAuthors();
        for(Author author: authors) {
            if (!userService.userExists(author.getUser())) {
                errorMessage+="User "+author.getUser().getUsername()+" does not exist. \n";
            }
            for (Author existingAuthor: existingAuthors) {
                if (author.getUser().equals(existingAuthor.getUser())) {
                    errorMessage+="User "+author.getUser().getUsername()+" already is an author at this conference. \n";
                }
            }
        }
        if (errorMessage.length()>0) {
            throw new MyException(errorMessage);
        }
    }
}
