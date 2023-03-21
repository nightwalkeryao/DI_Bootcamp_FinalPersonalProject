import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpService } from 'src/app/services/http.service';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.scss']
})
export class RoleComponent implements OnInit {
	selectedRole: string = "";
	roles: any = [];

	constructor(private registrationService: RegistrationService, private router: Router, private api: HttpService){ }

	async ngOnInit(): Promise<void> {
		this.selectedRole = this.registrationService.payload.user.role;
		this.registrationService.fetchRoles().subscribe({
			next: async (r: any) => {
				if(!r.length) {
					let roles = await require('/src/app/domain/dataset/roles.json');
					roles.forEach((_r: any) => {
						this.api.post('/roles', _r).subscribe({
							next: (_role: any) => {
								this.roles.push(_role);
							}
						});
					});
				} else {
					this.roles = r;
				}
			}
		});
	}

	nextPage() {
        if (this.selectedRole.length) {
            this.registrationService.payload.user.role = parseInt(this.selectedRole);
			this.api.get('/roles/' + this.selectedRole).subscribe({
				next: (role: any) => {
					localStorage.setItem('role', JSON.stringify(role));
					this.router.navigate(['register/basics']);
				}
			})
        } else {
			alert("Veuillez choisir un rôle.")
		}
    }

    prevPage() {
        this.router.navigate(['register/user']);
    }
}
