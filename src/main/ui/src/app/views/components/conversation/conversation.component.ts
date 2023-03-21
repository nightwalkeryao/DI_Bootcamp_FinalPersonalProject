import { Component, OnInit } from '@angular/core';
import { MenuItem, MessageService } from 'primeng/api';

@Component({
	selector: 'mentor-conversation',
	templateUrl: './conversation.component.html',
	styleUrls: ['./conversation.component.scss']
})
export class ConversationComponent implements OnInit {
	items: MenuItem[] = [];
	text: string = "Hello, Mentor!";

	constructor(private messageService: MessageService) { }

    ngOnInit() {
        this.items = [
            {
               label:'Merci 1x (5dc)',
               icon:'pi pi-circle-fill',
			   command: () => {
				this.messageService.add({ severity: 'info', summary: `5dc envoyés avec succès !`, detail: 'Un badge de 5dc a été déduit de votre compte.' });
			   }
            },
            {
               label:'Merci 2x (7dc)',
               icon:'pi pi-box',
			   command: () => {
				this.messageService.add({ severity: 'info', summary: `7dc envoyés avec succès !`, detail: 'Un badge de 7dc a été déduit de votre compte.' });
			   }
            },
            {
               label:'Merci 3x (10dc)',
               icon:'pi pi-dollar',
			   command: () => {
				this.messageService.add({ severity: 'info', summary: `10dc envoyés avec succès !`, detail: 'Un badge de 10dc a été déduit de votre compte.' });
			   }
            },
            {
               label:'Merci 5x (15dc)',
               icon:'pi pi-bitcoin',
			   command: () => {
				this.messageService.add({ severity: 'info', summary: `15dc envoyés avec succès !`, detail: 'Un badge de 15dc a été déduit de votre compte.' });
			   }
            },
        ];
    }

	sendMessage() {
		this.messageService.add({ severity: 'info', summary: `Message envoyé !`, detail: 'Votre message a bien été envoyé au mentor.' });
		this.text = "";
	}
}
