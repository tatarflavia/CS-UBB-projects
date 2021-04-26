import {User} from "./user";

export class ProgramCommitteeMember {
  id: number;
  user: User;
  name: string;
  affiliation: string;
  email: string;
  webPage: string;
  coChair: boolean;
  hasRegistered: boolean;
}
