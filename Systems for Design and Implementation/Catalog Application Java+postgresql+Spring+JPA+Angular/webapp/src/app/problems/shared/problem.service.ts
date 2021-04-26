import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Problem} from './problem.model';

@Injectable({
  providedIn: 'root'
})
export class ProblemService {
  private problemsUrl = 'http://localhost:8080/api/problems';
  constructor(private httpClient: HttpClient) { }

  getProblems(): Observable<Problem[]> {
    return this.httpClient
      .get<Array<Problem>>(this.problemsUrl);
  }
}
