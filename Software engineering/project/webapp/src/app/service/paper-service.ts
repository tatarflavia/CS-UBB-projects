import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Paper} from "../model/paper";
import {Message} from "../model/message";

@Injectable()
export class PaperService {
  private papersUrl = 'http://localhost:8080/papers';

  constructor(private httpClient: HttpClient) {
  }

  updatePaper(paper: Paper) {
    return this.httpClient.post<Message>(`${this.papersUrl}/${paper.id}/updatePaper`, paper);
  }
}
