import { Skill } from "../Skill";
import { Techno } from "../Techno";

let skills: Skill[] = [];
let technos: Techno[] = require("./techno.json");
let usedtechnos: Number[] = [];

for (let i = 0; i < (Math.max(6, Math.floor(Math.random() * 14))); i++) {
	let skill: Skill = new Skill();
	skill.id = i+1;
	skill.experience = Math.floor(Math.random() * 14) + 1;
	skill.level = [
		1, //"Beginner",
		2, //"Intermediate",
		3, //"Advanced",
		4, //"Professional",
		5, //"Wizard",
	][Math.floor(Math.random() * 5)];
	let ut = technos.filter(t => usedtechnos.indexOf(t.id!) == -1);
	let st = ut[Math.floor(Math.random() * ut.length)];
	usedtechnos.push(st.id!);
	skill.techno = st;

	skills.push(skill);
}

export default skills;
