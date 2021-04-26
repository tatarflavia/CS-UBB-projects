import {Component, Input, OnInit} from '@angular/core';
import {ProgramCommitteeMember} from "../model/program-committee-member";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {ConferenceService} from "../service/conference-service";
import {Conference} from "../model/conference";
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserService} from "../service/user-service";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-pcmember-registration-conference',
  templateUrl: './pcmember-registration-conference.component.html',
  styleUrls: ['./pcmember-registration-conference.component.css']
})
export class PcmemberRegistrationConferenceComponent implements OnInit {

  conference: Conference;
  formRegister: FormGroup;
  errorMessage: string;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, private conferenceService: ConferenceService, private formBuilder: FormBuilder) {
    this.formRegister = this.formBuilder.group({
      name: [],
      affiliation: [],
      email: [],
      webPage: [],
    });
    this.errorMessage = "";
  }

  ngOnInit(): void {
    this.route.params.pipe(
      switchMap((params: Params) => this.conferenceService.getConference(+params['id'])))
      .subscribe(conf => {
        conf.firstDay = new Date(conf.firstDay);
        conf.lastDay = new Date(conf.lastDay);
        conf.abstractDeadline = new Date(conf.abstractDeadline);
        conf.fullPaperDeadline = new Date(conf.fullPaperDeadline);
        conf.biddingDeadline = new Date(conf.biddingDeadline);
        conf.reviewingDeadline = new Date(conf.reviewingDeadline);
        this.conference = conf;
      });
  }

  onSubmit() {
    console.log(this.conference);
    const name = this.formRegister.value["name"];
    const affiliation = this.formRegister.value["affiliation"];
    const email = this.formRegister.value["email"];
    const webPage = this.formRegister.value["webPage"];
    const user = this.userService.getCurrentUser();
    const pcMember = {id: 0, user: user, name: name, affiliation: affiliation, email: email, webPage: webPage, coChair: false, hasRegistered: true};
    console.log(pcMember);

    this.conferenceService.registerPCMember(pcMember, this.conference.id).subscribe(
      (message) => {
        console.log("message " + message.message);
        if (message.message !== "okay") {
          this.errorMessage = message.message;
        } else {
          this.router.navigate(["/conference-overview", this.conference.id]);
        }
      }, (error) => {
        console.log("error " + error);
      }
    );
  }

  goBack() {
    this.router.navigateByUrl("/all-conferences");
  }
}
