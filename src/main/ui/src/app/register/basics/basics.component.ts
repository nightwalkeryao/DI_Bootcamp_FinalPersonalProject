import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Subscription } from 'rxjs';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-basics',
  templateUrl: './basics.component.html',
  styleUrls: ['./basics.component.scss']
})
export class BasicsComponent implements OnInit {
	role: string = '';
    basics: any;
	subscription: Subscription = new Subscription();

    constructor(public registrationService: RegistrationService, private messageService: MessageService, private router: Router) { }

    ngOnInit() {
        this.basics = this.registrationService.payload.basics;
		this.role = this.registrationService.payload.user.role;
    }

    nextPage() {
        if (this.basics.firstname && this.basics.lastname) {
            this.registrationService.payload.basics = this.basics;
            this.router.navigate(['register/confirmation']);
        } else {
			this.subscription = this.registrationService.badInput$.subscribe((input) => {
				this.messageService.clear();
				this.messageService.add({ severity: 'warn', summary: 'Entrées invalides.', detail: 'Veuillez renseigner au moins le nom et le prénom.' });
			});
			this.registrationService.prevent();
		}
    }

    prevPage() {
        this.router.navigate(['register/role']);
    }
}
