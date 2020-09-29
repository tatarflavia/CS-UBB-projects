import {Conference} from "../model/conference";
import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {switchMap} from "rxjs/operators";
import {ConferenceService} from "../service/conference-service";
import {User} from "../model/user";
import {UserService} from "../service/user-service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";





@Component({
  selector: 'app-participate',
  templateUrl: './participate.component.html',
  styleUrls: ['./participate.component.css']
})
export class ParticipateComponent implements OnInit {

  conference: Conference;
  user: User;
  formParticipate: FormGroup;
  errorMessage: string;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private conferenceService: ConferenceService,
    private formBuilder: FormBuilder,
    private userService: UserService) { 
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
        this.user = this.userService.getCurrentUser();
        this.formParticipate = this.formBuilder.group({
        fullName: []
      });
      this.errorMessage = "";
    }

  ngOnInit(): void {
  }

  onSubmit(): void {

    if (this.formParticipate.value["fullName"] === null) {
      this.errorMessage = "You must add your full name.";
    } else {
      this.conferenceService.addParticipant(this.conference.id, this.user).subscribe(
        (message) => {
          console.log("message: " + message.message);
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
  }



}
