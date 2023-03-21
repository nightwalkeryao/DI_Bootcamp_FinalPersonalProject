import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
	username: string = 'nightwalker'
	password: string = 'Pa$$w0rd!'

	constructor(private authService: AuthService) { }

	handleSubmit(e: any): void {
		this.authService.login({username: this.username, password: this.password});
	}
}
