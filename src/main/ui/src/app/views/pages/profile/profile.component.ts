import { Component, OnInit } from '@angular/core';
import { Skill } from 'src/app/domain/Skill';
import skills from '../../../domain/dataset/SkillsSet';

@Component({
	selector: 'app-profile',
	templateUrl: './profile.component.html',
	styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
	skills: Skill[] = [];

	ngOnInit() {
		// this.skills = skills;
		this.skills = skills.map((skill) => {
			skill.level = Number(skill.level) <= 4 ? skill.level : 3;
			return skill;
		});
	}
}
