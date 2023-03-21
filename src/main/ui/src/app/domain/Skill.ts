import { Techno } from "./Techno";

export class Skill {
	id?: Number;
	techno: Techno = new Techno();
	level?: string | Number;
	experience?: Number;
}
