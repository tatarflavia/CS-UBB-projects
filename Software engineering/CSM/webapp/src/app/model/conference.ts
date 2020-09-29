import {ProgramCommitteeMember} from "./program-committee-member";
import {User} from "./user";
import {Author} from "./author";
import {Paper} from "./paper";

export class Conference {
  id: number;
  name: string;
  numberReviewers: number;
  callForPapers: string;
  firstDay: Date;
  lastDay: Date;
  abstractDeadline: Date;
  fullPaperDeadline: Date;
  biddingDeadline: Date;
  reviewingDeadline: Date;
  participationFee: number;
  programCommittee: Array<ProgramCommitteeMember>;
  steeringCommittee: Array<User>;
  papers: Array<Paper>;
  participants: Array<User>;
}
