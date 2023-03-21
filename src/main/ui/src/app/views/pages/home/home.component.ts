import { Component } from '@angular/core';

@Component({
	selector: 'home-page',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.scss']
})
export class HomeComponent {
	navOpen: boolean = false;

	toggleNav(): void {
		this.navOpen = !this.navOpen;
		console.log('NavOpen:', this.navOpen);
	}
}
