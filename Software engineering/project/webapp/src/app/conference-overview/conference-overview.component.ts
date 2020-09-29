import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {switchMap} from "rxjs/operators";
import {ConferenceService} from "../service/conference-service";
import {Conference} from "../model/conference";

@Component({
  selector: 'app-conference-overview',
  templateUrl: './conference-overview.component.html',
  styleUrls: ['./conference-overview.component.css']
})
export class ConferenceOverviewComponent implements OnInit {

  conference: Conference;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private conferenceService: ConferenceService) {
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

  ngOnInit(): void {

  }

}
