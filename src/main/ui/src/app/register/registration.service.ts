import { Injectable } from '@angular/core';
import { Subject, Subscription } from 'rxjs';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';
import { HttpService } from '../services/http.service';

@Injectable({
	providedIn: 'root'
})
export class RegistrationService {
	subscription: Subscription = new Subscription();

	payload: any = {
		user: {
			username: "",
			email: "",
			password: "",
			password_confirmation: "",
			role: "",
		},
		basics: {
			firstname: "",
			lastname: "",
			city: "",
			balance: 10,
			status: 1,
			company: "",
			profilePicture: "",
			phoneNumber: "",
			jobTitle: "",
		}
	};

	constructor(private api: HttpService, private messageService: MessageService, private router: Router) { }

	submit() {
		return this.api.post('/users', this.payload.user);
	}

	fetchRoles() {
		return this.api.get('/roles');
	}

	private registrationComplete = new Subject<any>();

	registrationComplete$ = this.registrationComplete.asObservable();

	private badInput = new Subject<any>();

	badInput$ = this.badInput.asObservable();

	getPayload() {
		return this.payload;
	}

	setPayload(pl: any) {
		this.payload = pl;
	}

	complete() {
		this.submit().subscribe({
			next: (res: any) => {
				this.createProfile(this.payload.user.role, res.id).subscribe({
					next: (res2: any) => {
						this.subscription = this.registrationComplete$.subscribe((credentials) => {
							this.messageService.add({ severity: 'success', summary: 'Votre compte a bien été créé.', detail: 'Vous pouvez maintenant vous connecter.' });
						});
						this.registrationComplete.next(this.payload.user);
						setTimeout(() => this.router.navigate(['/login']), 2000);
					},
					error: (err2: any) => {
						this.subscription = this.badInput$.subscribe((err) => {
							this.messageService.add({ severity: 'error', summary: "L'enregistrement a échoué (2).", detail: err.message });
						});
						this.badInput.next(err2);
					}
				})
				// this.subscription = this.registrationComplete$.subscribe((credentials) => {
				// 	this.messageService.add({ severity: 'success', summary: 'Votre compte a bien été créé.', detail: 'Bonjour ' + credentials.username + ', bienvenue sur devs.club ! Veuillez confirmer votre adresse e-mail pour activer toutes les fonctionnalités disponibles pour votre compte.' });
				// });
				// this.registrationComplete.next(this.payload.user);
				// setTimeout(() => this.router.navigate(['/login']), 2000);
			},
			error: (err) => {
				this.subscription = this.badInput$.subscribe((err) => {
					this.messageService.add({ severity: 'error', summary: "L'enregistrement a échoué (1).", detail: err.message });
				});
				this.badInput.next(err);
			},
			complete: () => {
				this.messageService.clear();
			},
		});
	}

	prevent() {
		this.badInput.next(this.payload);
	}

	createProfile(role: number = 1, uuid: number = 1) {
		let selectedRole: any = localStorage.getItem('role');
		if (selectedRole) {
			selectedRole = JSON.parse(selectedRole);
			let roles: any[] = require('/src/app/domain/dataset/roles.json');
			let _route: string = roles.find((r: any) => r.name == selectedRole.name)!.path;
			this.payload.basics.user = uuid;
			this.payload.basics.name = this.payload.basics.firstname + " " + this.payload.basics.lastname;

			return this.api.post(_route, this.payload.basics);
		} else {
			let roles: any[] = require('/src/app/domain/dataset/roles.json');
			let _route: string = roles.find((r: any) => r.id == role)!.path;
			this.payload.basics.user = uuid;
			this.payload.basics.name = this.payload.basics.firstname + " " + this.payload.basics.lastname;

			return this.api.post(_route, this.payload.basics);
		}
	}
}
