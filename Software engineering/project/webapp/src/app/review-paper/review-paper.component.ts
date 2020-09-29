import {Component, OnInit} from "@angular/core";
import {Conference} from "../model/conference";
import {FormBuilder, FormGroup} from "@angular/forms";
import {User} from "../model/user";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {ConferenceService} from "../service/conference-service";
import {switchMap} from "rxjs/operators";
import {UserService} from "../service/user-service";
import {ReviewService} from "../service/review-service";
import {ProgramCommitteeMember} from "../model/program-committee-member";
import {Paper} from "../model/paper";

@Component({
  selector: 'app-review-paper',
  templateUrl: './review-paper.component.html',
  styleUrls: ['./review-paper.component.css']
})
export class ReviewPaperComponent implements OnInit {
  conference: Conference;
  formReview: FormGroup;
  user: User;
  reviewer: ProgramCommitteeMember;
  errorMessage: string;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private conferenceService: ConferenceService,
              private formBuilder: FormBuilder,
              private reviewService: ReviewService,
              private userService: UserService) {
              // maybe also userService and paperService
    this.formReview = this.formBuilder.group({
      paper: [],
      grade: [],
      review: []
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
    this.user = this.userService.getCurrentUser();
    for (const programCommitteeMember of this.conference.programCommittee) {
      if (programCommitteeMember.user === this.user) {
        this.reviewer = programCommitteeMember;
        break;
      }
    }
  }

  onSubmit(): void {
    this.errorMessage = "";
    if (this.formReview.value["paper"] === null) {
      this.errorMessage += "You must select the paper.\n";
    } if (this.formReview.value["grade"] === null) {
      this.errorMessage += "You must grade the paper.\n";
    } if (this.formReview.value["review"] === null) {
      this.errorMessage += "You must upload the review.\n";
    }
    const paperId = this.formReview.value["paper"];
    const paper = this.conference.papers.find(ppr => {
      // tslint:disable-next-line:triple-equals
      if (ppr.id == paperId) { return ppr; }
    });
    const grade = this.formReview.value["grade"];
    const reviewDocPath = this.formReview.value["review"].split("\\");
    const reviewDoc = reviewDocPath[reviewDocPath.length - 1];

    this.reviewService.addReview({
      id: 0,
      grade: grade,
      review: reviewDoc,
      paper: paper,
      programCommitteeMember: this.reviewer
    }).subscribe((message) => {
        console.log("message: " + message.message);
        if (message.message !== "okay") {
          this.errorMessage += message.message;
        } else { this.router.navigate(["/conference-overview", this.conference.id]); }
      }, (error) => { console.log("error " + error); }
    );
  }

}
