import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Subject, Subscription } from 'rxjs';
import { HttpService } from 'src/app/services/http.service';

@Component({
	selector: 'app-mentors',
	templateUrl: './mentors.component.html',
	styleUrls: ['./mentors.component.scss']
})
export class MentorsComponent implements OnInit {
	mentors: any[] = [];
	subscription: Subscription = new Subscription();
	private settingDone = new Subject<any>();
	settingDone$ = this.settingDone.asObservable();

	constructor(private api: HttpService, private messageService: MessageService, private router: Router) { }

	ngOnInit() {
		fetch("https://jsonplaceholder.typicode.com/users")
			.then((response) => response.json())
			.then((users: any[]) => {
				let technos: any[] = require('/src/app/domain/dataset/techno.json');
				users.forEach((user) => {

					this.mentors.push({
						id: user.id,
						name: user.name,
						profilePicture: '/assets/images/users/300_' + (Math.max(1, Math.floor(Math.random() * 24))) + '.jpg',
						technos: this.getMultipleRandom(technos, Math.max(3, Math.floor(Math.random() * 6))).map(t => t.name),
					})
				});
			});
	}

	getMultipleRandom(arr: any[], num: number) {
		const shuffled = [...arr].sort(() => 0.5 - Math.random());

		return shuffled.slice(0, num);
	}

	setAsMentor(mentor: any) {
		// localStorage.setItem('mentor', JSON.stringify(mentor));
		// this.subscription = this.settingDone$.subscribe((_mentor) => {
		// 	this.messageService.add({ severity: 'success', summary: `${_mentor.name} est à présent votre mentor !`, detail: 'Visitez son profil pour démarrer un nouveau sujet.' });
		// });
		// this.settingDone.next(mentor);
		setTimeout(() => this.router.navigate(['/app/mentors/' + mentor.id]), 50);
	}
}
