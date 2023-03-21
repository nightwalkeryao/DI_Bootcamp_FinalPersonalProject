import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './views/pages/home/home.component';
import { LoginComponent } from './views/pages/login/login.component';
import { RegisterComponent } from './views/pages/register/register.component';

const routes: Routes = [
	{
		path: "",
		component: HomeComponent,
		pathMatch: 'full'
	}, {
		path: "login",
		component: LoginComponent
	}, {
		path: "register",
		loadChildren: () => import('./register/register.module').then(m => m.RegisterModule),
	},
	{
		path: 'app',
		loadChildren: () => import('./internal/internal.module').then(m => m.InternalModule),
		// canActivate: [...]
	},
	{
		path: '**',
		component: HomeComponent, // TODO: create a NotFoundComponent
	}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
