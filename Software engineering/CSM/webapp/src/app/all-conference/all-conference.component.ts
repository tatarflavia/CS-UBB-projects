import {Component, Input, OnInit} from '@angular/core';
import {User} from "../model/user";
import {UserService} from "../service/user-service";
import {Router} from "@angular/router";
import {Conference} from "../model/conference";
import {ConferenceService} from "../service/conference-service";

@Component({
  selector: 'app-all-conference',
  templateUrl: './all-conference.component.html',
  styleUrls: ['./all-conference.component.css']
})

export class AllConferenceComponent implements OnInit {

  user: User;
  conferences: Array<Conference>;
  currentDate:Date;

  constructor(private userService: UserService, private conferenceService: ConferenceService, private router: Router) {
    this.user = this.userService.getCurrentUser();
  }

  ngOnInit(): void {
    this.getConferences();
    this.getDate();
  }

  visitPage(id): void {
    this.router.navigate(["/conference-overview", id]);
  }

  getConferences(): void {
    this.conferenceService.getConferences().subscribe(
      (conferences) => {
        this.conferences = conferences.map(function (conf) {
          conf.firstDay = new Date(conf.firstDay);
          conf.lastDay = new Date(conf.lastDay);
          conf.abstractDeadline = new Date(conf.abstractDeadline);
          conf.fullPaperDeadline = new Date(conf.fullPaperDeadline);
          conf.biddingDeadline = new Date(conf.biddingDeadline);
          conf.reviewingDeadline = new Date(conf.reviewingDeadline);

          return conf;
        }); });
      }

    getDate(): Date{
      this.currentDate=new Date();
      return this.currentDate;
      }
}
