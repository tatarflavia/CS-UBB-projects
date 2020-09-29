import { Component, OnInit } from '@angular/core';
import {Conference} from "../model/conference";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {ConferenceService} from "../service/conference-service";
import {switchMap} from "rxjs/operators";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {User} from "../model/user";
import {UserService} from "../service/user-service";

@Component({
  selector: 'app-submit-paper',
  templateUrl: './submit-paper.component.html',
  styleUrls: ['./submit-paper.component.css']
})
export class SubmitPaperComponent implements OnInit {

  conference: Conference;
  formPaper: FormGroup;
  user: User;
  authorNumber: number;
  authors: Array<number>;
  abstract: string;
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
    this.formPaper = this.formBuilder.group({
      title: [],
      keywords: [],
      topics: [],
      yourName: [],
      yourAffiliation: [],
      yourEmail: [],
      abstract: []
    });
    this.authorNumber = 0;
    this.authors = [];
    this.errorMessage = "";
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (this.formPaper.value["abstract"] === null) {
      this.errorMessage = "You must upload the abstract paper.";
    } else {
      const abstractPath = this.formPaper.value["abstract"].split("\\");
      const abstract = abstractPath[abstractPath.length - 1];
      const authors = [];
      this.authors.forEach(author => {
        console.log();
        authors.push({
          id: 0,
          name: this.formPaper.value["name" + author],
          user: {username: this.formPaper.value["username" + author], password: ""},
          affiliation: this.formPaper.value["affiliation" + author],
          email: this.formPaper.value["email" + author]
        });
      });
      authors.push({
        id: 0,
        name: this.formPaper.value["yourName"],
        user: this.user,
        affiliation: this.formPaper.value["yourAffiliation"],
        email: this.formPaper.value["yourEmail"]
      });
      this.conferenceService.addPaper(this.conference.id, {
        id: 0,
        title: this.formPaper.value["title"],
        keywords: this.formPaper.value["keywords"],
        topics: this.formPaper.value["topics"],
        abstractPaper: abstract,
        fullPaper: "",
        presentation: "",
        authors: authors
      }).subscribe(
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

  addAuthor(): void {
    this.authorNumber += 1;
    console.log(this.authorNumber);
    this.formPaper.addControl("name" + this.authorNumber, new FormControl(''));
    this.formPaper.addControl("username" + this.authorNumber, new FormControl(''));
    this.formPaper.addControl("email" + this.authorNumber, new FormControl(''));
    this.formPaper.addControl("affiliation" + this.authorNumber, new FormControl(''));
    this.authors.push(this.authorNumber);
  }

}
