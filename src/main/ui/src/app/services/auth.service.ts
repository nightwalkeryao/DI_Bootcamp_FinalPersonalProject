import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Subject, Subscription } from 'rxjs';
import { HttpService } from './http.service';

@Injectable({
	providedIn: 'root'
})
export class AuthService {
	user: any = {};
	token: string = '';

	subscription: Subscription = new Subscription();
	private toaster = new Subject<any>();
	toast$ = this.toaster.asObservable();

	constructor(private api: HttpService, private messageService: MessageService, private router: Router) {
		this.getUser();
	}

	login(payload: any) {
		if(localStorage.getItem('user'))
			localStorage.removeItem('user');
		if(localStorage.getItem('token'))
			localStorage.removeItem('token');
		this.api.post('/users/auth/login', payload).subscribe({
			next: (res: any) => {
				res.role = require('/src/app/domain/dataset/roles.json')[1];
				this.user = res;
				this.token = res.token;
				localStorage.setItem('user', JSON.stringify(this.user));
				localStorage.setItem('token', this.token);
				this.router.navigate(['app/profile']);
			},
			error: (err: any) => {
				this.subscription = this.toast$.subscribe((err) => {
					this.messageService.add({ severity: 'error', summary: "La connexion a échoué.", detail: err.message });
				});
				this.toaster.next(err);
			}
		})
	}

	getUser() {
		let userStr = localStorage.getItem('user');
		if(userStr) {
			this.user = JSON.parse(userStr);
			this.token = this.user.token;
			return this.user;
		}
		return null;
	}

	getToken() {
		return this.getUser() !== null ? this.token : null;
	}

	isAuthenticated(): boolean {
		return this.getToken() !== null;
	}
}
