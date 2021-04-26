import { Component, OnInit } from '@angular/core';
import {Conference} from "../model/conference";
import {FormBuilder, FormGroup} from "@angular/forms";
import {User} from "../model/user";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {ConferenceService} from "../service/conference-service";
import {UserService} from "../service/user-service";
import {PaperService} from "../service/paper-service";
import {switchMap} from "rxjs/operators";
import {Paper} from "../model/paper";

@Component({
  selector: 'app-upload-full',
  templateUrl: './upload-full.component.html',
  styleUrls: ['./upload-full.component.css']
})
export class UploadFullComponent implements OnInit {

  conference: Conference;
  formPaper: FormGroup;
  user: User;
  full: string;
  errorMessage: string;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private conferenceService: ConferenceService,
              private formBuilder: FormBuilder,
              private userService: UserService,
              private paperService: PaperService) {
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
    this.formPaper = this.formBuilder.group({
      full: []
    });
    this.errorMessage = "";
  }

  ngOnInit(): void {
  }

  getPaper(): Paper {
    for (const paper of this.conference.papers) {
      for (const author of paper.authors) {
        if (author.user.username === this.user.username) {
          return paper;
        }
      }
    }
    return null;
  }

  onSubmit(): void {
    if (this.formPaper.value["full"] === null) {
      this.errorMessage = "You must upload the full paper.";
    } else {
      const fullPath = this.formPaper.value["full"].split("\\");
      const full = fullPath[fullPath.length - 1];
      const paper = this.getPaper();
      paper.fullPaper = full;
      this.paperService.updatePaper(paper).subscribe((message) => {
        if (message.message !== "okay") {
          this.errorMessage = message.message;
        } else {
          this.router.navigate(["/conference-overview", this.conference.id]);
        }
      });
    }
  }

}
