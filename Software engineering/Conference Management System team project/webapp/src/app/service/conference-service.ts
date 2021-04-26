import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Conference} from "../model/conference";
import {Message} from "../model/message";
import {map} from "rxjs/operators";
import {ProgramCommitteeMember} from "../model/program-committee-member";
import {Paper} from "../model/paper";
import {User} from "../model/user";

@Injectable()
export class ConferenceService {
  private conferencesUrl = 'http://localhost:8080/conferences';

  constructor(private httpClient: HttpClient) {
  }

  addConference(conference: Conference): Observable<Message> {
    return this.httpClient.post<Message>(this.conferencesUrl, conference);
  }

  getConferences(): Observable<Array<Conference>> {
    return this.httpClient.get<Array<Conference>>(this.conferencesUrl);
  }

  getConference(id: number): Observable<Conference> {
    return this.getConferences().pipe(
      map(conferences => conferences.find(conference => conference.id === id)));
  }

  registerPCMember(pcMember: ProgramCommitteeMember, id: number): Observable<Message> {
    const url = `${this.conferencesUrl}/register/${id}`;
    return this.httpClient.post<Message>(url, pcMember);
  }

  addPaper(conferenceId: number, paper: Paper): Observable<Message> {
    return this.httpClient.post<Message>(`${this.conferencesUrl}/${conferenceId}/addPaper`, paper);
  }

  addParticipant(conferenceId: number, user: User): Observable<Message> {
    console.log(user);
    return this.httpClient.post<Message>(`${this.conferencesUrl}/${conferenceId}/addParticipant`, user);
  }
}
