import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenubarModule } from 'primeng/menubar';
import { InternalLayoutComponent } from '../shared/internal-layout/internal-layout.component';
import { InternalRoutingModule } from './internal-routing.module';
import {AvatarModule} from 'primeng/avatar';
import {AvatarGroupModule} from 'primeng/avatargroup';

@NgModule({
  declarations: [
	InternalLayoutComponent
  ],
  imports: [
    CommonModule,
	MenubarModule,
	InternalRoutingModule,
	AvatarModule,
	AvatarGroupModule,
  ]
})
export class InternalModule { }
