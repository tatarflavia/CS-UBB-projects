import {Component, Input, OnInit} from '@angular/core';
import {UserService} from "../service/user-service";
import {Router} from "@angular/router";
import {User} from "../model/user";
import {Conference} from "../model/conference";
import {ProgramCommitteeMember} from "../model/program-committee-member";

@Component({
  selector: 'app-conference-menu',
  templateUrl: './conference-menu.component.html',
  styleUrls: ['./conference-menu.component.css']
})
export class ConferenceMenuComponent implements OnInit {

  user: User;
  @Input()
  conference: Conference;
  showRegisterPCM: boolean;
  showSubmitPaper: boolean;
  showUploadAbstract: boolean;
  showUploadFull: boolean;
  showUploadPresentation: boolean;
  showReviewPaper: boolean;
  showParticipate: boolean;

  constructor(private userService: UserService, private router: Router) {
    this.user = this.userService.getCurrentUser();
    this.showRegisterPCM = false;
    this.showSubmitPaper = true;
    this.showUploadAbstract = false;
    this.showUploadFull = false;
    this.showUploadPresentation = false;
    this.showReviewPaper = false;
    this.showParticipate = true;
  }

  ngOnInit(): void {
    // showRegisterPCM
    this.conference.programCommittee.forEach(member => {
      if (member.user.username === this.user.username && member.hasRegistered === false) {
        this.showRegisterPCM = true;
      }
    });
    // showReviewPaper
    this.conference.programCommittee.forEach(member => {
      if (member.user.username === this.user.username) {
        this.showReviewPaper = true;
      }
    });
    // showSubmitPaper
    this.conference.papers.forEach(paper => {
      paper.authors.forEach(author => {
        if (author.user.username === this.user.username) {
          this.showSubmitPaper = false;
        }
      });
    });
    // showUploadAbstract
    this.conference.papers.forEach(paper => {
      paper.authors.forEach(author => {
        if (author.user.username === this.user.username) {
          this.showUploadAbstract = true;
        }
      });
    });
    // showUploadFull
    this.conference.papers.forEach(paper => {
      paper.authors.forEach(author => {
        if (author.user.username === this.user.username) {
          this.showUploadFull = true;
        }
      });
    });
    // showUploadPresentation
    this.conference.papers.forEach(paper => {
      paper.authors.forEach(author => {
        if (author.user.username === this.user.username) {
          this.showUploadPresentation = true;
        }
      });
    });
    // showParticipate
    this.conference.participants.forEach(participant => {
      if (participant.username === this.user.username) {
        this.showParticipate = false;
      }
    });
  }

}
