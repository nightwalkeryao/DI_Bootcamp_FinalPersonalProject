import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { HttpService } from 'src/app/services/http.service';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
	items: MenuItem[] = [];

	// username: string = "";
	// email: string = "";
	// password: string = "";
	// firstname: string = "";
	// lastname: string = "";
	// city: string = "";
	// role: Number | string = "";

	constructor(private api: HttpService) { }

	// handleSubmit(e: Event) {
	// 	alert("Submit");
	// }

	async ngOnInit() {
		this.items = [{
			label: 'Compte utilisateur',
			routerLink: 'user'
		},
		{
			label: 'Rôle',
			routerLink: 'role'
		},
		{
			label: 'Informations de base',
			routerLink: 'basics'
		},
		{
			label: 'Confirmation',
			routerLink: 'confirmation'
		}];

		// seed statuses
		this.api.get('/statuses').subscribe({
			next: async (res: any) => {
				if(!res.length) {
					let statuses = await require('/src/app/domain/dataset/statuses.json');
					statuses.forEach((_s: any) => {
						this.api.post('/statuses', _s).subscribe({
							next: (__s: any) => {
								console.log(__s);
							}
						});
					});
				}
			}
		})
	}
}
