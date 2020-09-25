import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Student} from './student.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private studentsUrl = 'http://localhost:8080/api/students';
  constructor(private httpClient: HttpClient) { }
  getStudents(): Observable<Student[]> {
    return this.httpClient
      .get<Array<Student>>(this.studentsUrl);
  }
}
