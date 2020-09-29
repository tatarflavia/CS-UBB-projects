import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Review} from "../model/review";
import {Message} from "../model/message";
import {Observable} from "rxjs";

@Injectable()
export class ReviewService {
  private reviewsUrl = 'http://localhost:8080/reviews';

  constructor(private httpClient: HttpClient) {}

  addReview(review: Review): Observable<Message> {
    return this.httpClient.post<Message>(this.reviewsUrl, review);
  }
}
