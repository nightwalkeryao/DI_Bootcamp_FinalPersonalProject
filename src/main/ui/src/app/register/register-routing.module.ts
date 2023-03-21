import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from '../views/pages/register/register.component';
import { BasicsComponent } from './basics/basics.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { RoleComponent } from './role/role.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
	{
		path: '',
		// redirectTo: 'user',
		// pathMatch: 'full',
		component: RegisterComponent,
		children: [
			{
				path: '',
				redirectTo: 'user',
				pathMatch: 'full',
			},
			{
				path: 'user',
				component: UserComponent,
			},
			{
				path: 'role',
				component: RoleComponent,
			}, {
				path: 'basics',
				component: BasicsComponent,
			}, {
				path: 'confirmation',
				component: ConfirmationComponent,
			}
		],
	}
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule],
})
export class RegisterRoutingModule { }
