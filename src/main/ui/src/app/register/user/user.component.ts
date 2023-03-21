import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
	submitted: boolean = false;
	user: any = null;

	constructor(private registrationService: RegistrationService, private router: Router) { }

	ngOnInit() {
        this.user = this.registrationService.payload.user;
    }

	nextPage() {
        if (this.user.username && this.user.email && this.user.password && this.user.password == this.user.password_confirmation) {
            this.registrationService.payload.user = this.user;
            this.router.navigate(['register/role']);

            return;
        }

        this.submitted = true;
    }
}
