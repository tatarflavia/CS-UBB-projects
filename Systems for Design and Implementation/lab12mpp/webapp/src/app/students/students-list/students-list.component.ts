import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Student} from '../shared/student.model';
import {StudentService} from '../shared/student.service';

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.css']
})
export class StudentsListComponent implements OnInit {
  students: Array<Student>;
  errorMessage: string;
  constructor(private router: Router, private studentService: StudentService) { }

  ngOnInit(): void {
    this.getStudents();
  }
  getStudents() {
    this.studentService.getStudents()
      .subscribe(
        students => this.students = students,
        error => this.errorMessage = (error as any)
      );
  }

}
