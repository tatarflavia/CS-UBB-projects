import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {GradeService} from '../shared/grade.service';
import {Grade} from '../shared/grade.model';

@Component({
  selector: 'app-grades-list',
  templateUrl: './grades-list.component.html',
  styleUrls: ['./grades-list.component.css']
})
export class GradesListComponent implements OnInit {
  grades: Array<Grade>;
  errorMessage: string;
  constructor(private router: Router, private gradeService: GradeService) { }

  ngOnInit(): void {
    this.getGrades();
  }

  getGrades() {
    this.gradeService.getGrades()
      .subscribe(
        grades => this.grades = grades,
        error => this.errorMessage = (error as any)
      );
  }

}
