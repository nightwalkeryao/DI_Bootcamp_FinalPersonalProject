import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Subject, Subscription } from 'rxjs';
import skills from 'src/app/domain/dataset/SkillsSet';
import { Skill } from 'src/app/domain/Skill';
import { HttpService } from 'src/app/services/http.service';

@Component({
	selector: 'app-mentor',
	templateUrl: './mentor.component.html',
	styleUrls: ['./mentor.component.scss']
})
export class MentorComponent {
	mentor: any = {};
	skills: Skill[] = [];
	subscription: Subscription = new Subscription();
	settedAsMentor: boolean = false;

	constructor(private api: HttpService, private messageService: MessageService, private router: Router) {
		this.mentor = {
			id: 7,
			name: "Kurtis Weissnat",
			profilePicture: '/assets/images/users/300_' + (Math.max(1, Math.floor(Math.random() * 24))) + '.jpg',
		}
	}

	private settingDone = new Subject<any>();

	settingDone$ = this.settingDone.asObservable();

	ngOnInit() {
		this.skills = skills.map((skill) => {
			skill.level = Number(skill.level) >= 4 ? skill.level : 5;
			return skill;
		});
	}

	setAsMentor(mentor: any) {
		localStorage.setItem('mentor', JSON.stringify(mentor));
		this.messageService.add({ severity: 'success', summary: `${mentor.name} est à présent votre mentor !`, detail: 'Vous pouvez maintenant démarrer un sujet.' });
		this.settedAsMentor = true;
	}
}
