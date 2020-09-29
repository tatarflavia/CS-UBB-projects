import {Author} from "./author";

export class Paper {
  id: number;
  title: string;
  keywords: string;
  topics: string;
  abstractPaper: string;
  fullPaper: string;
  presentation: string;
  authors: Array<Author>;
}
