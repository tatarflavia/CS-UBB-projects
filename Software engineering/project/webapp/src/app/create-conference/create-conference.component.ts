import { Component, OnInit } from '@angular/core';
import {UserService} from "../service/user-service";
import {Router} from "@angular/router";
import {User} from "../model/user";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Conference} from "../model/conference";
import {Author} from "../model/author";
import {ProgramCommitteeMember} from "../model/program-committee-member";
import {ConferenceService} from "../service/conference-service";

@Component({
  selector: 'app-create-conference',
  templateUrl: './create-conference.component.html',
  styleUrls: ['./create-conference.component.css']
})
export class CreateConferenceComponent implements OnInit {

  user: User;
  formCreate: FormGroup;
  errorMessage: string;

  constructor(private userService: UserService, private conferenceService: ConferenceService, private router: Router,  private formBuilder: FormBuilder) {
    this.user = this.userService.getCurrentUser();
    this.formCreate = this.formBuilder.group({
      name: [],
      callForPapers: [],
      numberReviewers: [],
      participationFee: [],
      firstDay: [],
      lastDay: [],
      abstractDeadline: [],
      fullDeadline: [],
      biddingDeadline: [],
      reviewingDeadline: [],
      PCMembers: [],
      coChairs: [],
      steeringCommittee: [],
      participants: []
    });
    this.errorMessage = "";
    this.formCreate.patchValue({"numberReviewers": "3"});
  }

  ngOnInit(): void {
  }

  makeConference (name, callForPapers, numberReviewers, participationFee, firstDay, lastDay, abstractDeadline, fullDeadline, biddingDeadline, reviewingDeadline, PCMembers, coChairs, steeringCommittee): Conference {
    const programCommittee = new Array<ProgramCommitteeMember>();
    if (PCMembers !== null) {
      const pCommittee1 = PCMembers.split(/\r?\n/);
      for (const member of pCommittee1) {
        if (member.length > 0) {
          programCommittee.push({
            id: 0,
            affiliation: "",
            email: "",
            hasRegistered: false,
            coChair: false,
            name: "",
            user: {username: member, password: ""},
            webPage: ""
          });
        }
      }
    }
    if (coChairs !== null) {
      const pCommittee2 = coChairs.split(/\r?\n/);
      for (const member of pCommittee2) {
        if (member.length > 0) {
          programCommittee.push({
            id: 0,
            affiliation: "",
            email: "",
            hasRegistered: false,
            coChair: true,
            name: "",
            user: {username: member, password: ""},
            webPage: ""
          });
        }
      }
    }
    const steeringCommitteeFinal = new Array<User>();
    if (steeringCommittee !== null) {
      const sCommittee = steeringCommittee.split(/\r?\n/);
      for (const member of sCommittee) {
        if (member.length > 0) {
          steeringCommitteeFinal.push({
            username: member,
            password: "", ...{}
          });
        }
      }
    }
    steeringCommitteeFinal.push({
      username: this.userService.getCurrentUser().username,
      password: "", ... {
      }});
    return {
      abstractDeadline: abstractDeadline,
      biddingDeadline: biddingDeadline,
      firstDay: firstDay,
      fullPaperDeadline: fullDeadline,
      id: 0,
      lastDay: lastDay,
      participationFee: participationFee,
      programCommittee: programCommittee,
      reviewingDeadline: reviewingDeadline,
      steeringCommittee: steeringCommitteeFinal,
      name: name,
      callForPapers: callForPapers,
      numberReviewers: numberReviewers,
      papers: [],
      participants: []
    };
  }

  onSubmit() {
    const name = this.formCreate.value["name"];
    const callForPapers = this.formCreate.value["callForPapers"];
    const numberReviewers = this.formCreate.value["numberReviewers"];
    const participationFee = this.formCreate.value["participationFee"];
    const firstDay = this.formCreate.value["firstDay"];
    const lastDay = this.formCreate.value["lastDay"];
    const abstractDeadline = this.formCreate.value["abstractDeadline"];
    const fullDeadline = this.formCreate.value["fullDeadline"];
    const biddingDeadline = this.formCreate.value["biddingDeadline"];
    const reviewingDeadline = this.formCreate.value["reviewingDeadline"];
    const PCMembers = this.formCreate.value["PCMembers"];
    const coChairs = this.formCreate.value["coChairs"];
    const steeringCommittee = this.formCreate.value["steeringCommittee"];
    const conference = this.makeConference(name, callForPapers, numberReviewers, participationFee, firstDay, lastDay, abstractDeadline, fullDeadline, biddingDeadline, reviewingDeadline, PCMembers, coChairs, steeringCommittee);
    this.conferenceService.addConference(conference).subscribe(
      (message) => {
        console.log("message " + message.message);
        if (message.message !== "okay") {
          this.errorMessage = message.message;
        } else {
          this.router.navigateByUrl("/all-conferences");
        }
      }, (error) => {
        console.log("error " + error);
      }
    );
  }

}
