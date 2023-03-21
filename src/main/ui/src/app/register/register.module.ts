import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserComponent } from './user/user.component';
import { RoleComponent } from './role/role.component';
import { BasicsComponent } from './basics/basics.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { RegisterRoutingModule } from './register-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { RadioButtonModule } from 'primeng/radiobutton';
import { ToastModule } from 'primeng/toast';
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { CardModule } from 'primeng/card';
import { MessageService } from 'primeng/api';
import { RegistrationService } from './registration.service';
import {InputTextModule} from 'primeng/inputtext';

@NgModule({
	declarations: [
		UserComponent,
		RoleComponent,
		BasicsComponent,
		ConfirmationComponent
	],
	imports: [
		CommonModule,
		RegisterRoutingModule,
		HttpClientModule,
		RadioButtonModule,
		ToastModule,
		ButtonModule,
		FormsModule,
		CardModule,
		InputTextModule,
	],
	providers: [
		MessageService,
		RegistrationService,
	]
})
export class RegisterModule { }
