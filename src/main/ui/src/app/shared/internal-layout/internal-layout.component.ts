import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
	selector: 'app-internal-layout',
	templateUrl: './internal-layout.component.html',
	styleUrls: ['./internal-layout.component.scss']
})
export class InternalLayoutComponent {
	items: MenuItem[] = [];

	ngOnInit() {
		this.items = [
			{
				label: 'Experts',
				icon: 'pi pi-fw pi-star',
				items: [
					{
						label: 'Trouver un mentor',
						// icon: 'pi pi-fw pi-search',
						routerLink: '/app/mentors'
					},
					{
						label: 'Recruter un expert',
						// icon: 'pi pi-fw pi-briefcase'
					}
				]
			},
			{
				label: 'Débutants',
				icon: 'pi pi-fw pi-users',
			},
			{
				label: 'Annonce d\'emplois',
				icon: 'pi pi-fw pi-bell',
			},
			{
				label: 'Evènements',
				icon: 'pi pi-fw pi-calendar',
			}
		];
	}
}
