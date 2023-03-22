import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';
import { HomeComponent } from './views/pages/home/home.component';
import { LoginComponent } from './views/pages/login/login.component';
import { RegisterComponent } from './views/pages/register/register.component';
import { FormsModule } from '@angular/forms';
import { ProfileComponent } from './views/pages/profile/profile.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PanelModule } from 'primeng/panel';
import { TabViewModule } from 'primeng/tabview';
import { SplitterModule } from 'primeng/splitter';
import { FieldsetModule } from 'primeng/fieldset';
import { DataViewModule } from 'primeng/dataview';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';
import { RatingModule } from 'primeng/rating';
import { InternalModule } from './internal/internal.module';
import { StyleClassModule } from 'primeng/styleclass';
import { HomeBlocksComponent } from './views/components/home-blocks/home-blocks.component';
import { StepsModule } from 'primeng/steps';
import { RegisterModule } from './register/register.module';
import { ToastModule } from 'primeng/toast';
import { HttpService } from './services/http.service';
import { AuthService } from './services/auth.service';
import { MentorsComponent } from './views/pages/mentors/mentors.component';
import { CardModule } from 'primeng/card';
import { MentorComponent } from './views/pages/mentor/mentor.component';
import { MessagesModule } from 'primeng/messages';
import { ConversationComponent } from './views/components/conversation/conversation.component';
import { SlideMenuModule } from 'primeng/slidemenu';
import { EditorModule } from 'primeng/editor';
import { BadgeModule } from 'primeng/badge';

@NgModule({
	declarations: [
		AppComponent,
		HomeComponent,
		LoginComponent,
		RegisterComponent,
		ProfileComponent,
		HomeBlocksComponent,
		MentorsComponent,
		MentorComponent,
		ConversationComponent
	],
	imports: [
		BrowserModule,
		BrowserAnimationsModule,
		AppRoutingModule,
		FormsModule,
		SharedModule,
		InternalModule,
		RegisterModule,
		PanelModule,
		TabViewModule,
		SplitterModule,
		FieldsetModule,
		DataViewModule,
		ButtonModule,
		TooltipModule,
		RatingModule,
		StyleClassModule,
		StepsModule,
		ToastModule,
		CardModule,
		MessagesModule,
		SlideMenuModule,
		EditorModule,
		BadgeModule,
	],
	providers: [
		HttpService,
		AuthService,
	],
	bootstrap: [AppComponent]
})
export class AppModule { }
