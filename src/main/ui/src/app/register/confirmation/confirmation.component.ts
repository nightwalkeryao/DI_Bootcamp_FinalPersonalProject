import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationService } from '../registration.service';

@Component({
	selector: 'app-confirmation',
	templateUrl: './confirmation.component.html',
	styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent implements OnInit {

	user: any;
	basics: any;
	selectedRole: any = {};

	constructor(public registrationService: RegistrationService, private router: Router) { }

	async ngOnInit() {
		let pl = this.registrationService.payload;
		this.user = pl.user;
		this.basics = pl.basics;
		if (this.user.role) {
			this.registrationService.fetchRoles().subscribe({
				next: (res: any) => {
					this.selectedRole = res.find((r: any) => r.id == this.user.role);
				}
			});
		}
	}

	complete() {
		this.registrationService.complete();
	}

	prevPage() {
		this.router.navigate(['register/basics']);
	}
}
