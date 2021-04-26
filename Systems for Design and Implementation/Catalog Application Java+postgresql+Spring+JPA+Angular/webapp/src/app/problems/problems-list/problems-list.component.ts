import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ProblemService} from '../shared/problem.service';
import {Problem} from '../shared/problem.model';

@Component({
  selector: 'app-problems-list',
  templateUrl: './problems-list.component.html',
  styleUrls: ['./problems-list.component.css']
})
export class ProblemsListComponent implements OnInit {
  problems: Array<Problem>;
  errorMessage: string;
  constructor(private router: Router, private problemService: ProblemService) { }

  ngOnInit(): void {
    this.getProblems();
  }
  getProblems() {
    this.problemService.getProblems()
      .subscribe(
        problems => this.problems = problems,
        error => this.errorMessage = (error as any)
      );
  }

}
