import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InternalLayoutComponent } from '../shared/internal-layout/internal-layout.component';
import { MentorComponent } from '../views/pages/mentor/mentor.component';
import { MentorsComponent } from '../views/pages/mentors/mentors.component';
import { ProfileComponent } from '../views/pages/profile/profile.component';

const routes: Routes = [
	{
		path: '',
		component: InternalLayoutComponent,
		children: [
			{
				path: 'profile',
				component: ProfileComponent,
			},
			{
				path: 'mentors',
				component: MentorsComponent,
			},
			{
				path: 'mentors/:id',
				component: MentorComponent,
			},
			{
				path: '',
				redirectTo: 'profile',
				pathMatch: 'full'
			}
		],
	}
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule],
})
export class InternalRoutingModule { }
