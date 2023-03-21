import { Component, Input } from '@angular/core';

@Component({
  selector: 'btn',
  templateUrl: './btn.component.html',
  styleUrls: ['./btn.component.scss']
})
export class BtnComponent {
	@Input() text: string = ""
	@Input() theme: string = "colored"
	@Input() type: string = "button"
}
