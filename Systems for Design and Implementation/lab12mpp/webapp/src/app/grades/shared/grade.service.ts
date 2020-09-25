import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Grade} from './grade.model';

@Injectable({
  providedIn: 'root'
})
export class GradeService {
  private gradesUrl = 'http://localhost:8080/api/grades';
  constructor(private httpClient: HttpClient) { }
  getGrades(): Observable<Grade[]> {
    return this.httpClient
      .get<Array<Grade>>(this.gradesUrl);
  }


}
