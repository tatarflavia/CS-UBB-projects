// todo

import {Paper} from "./paper";
import {ProgramCommitteeMember} from "./program-committee-member";

export class Review {
  id: number;
  grade: number;
  review: string;
  paper: Paper;
  programCommitteeMember: ProgramCommitteeMember;
}
