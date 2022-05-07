import { Component, EventEmitter, Output } from '@angular/core';
import { Donor } from './common/donor';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'E_Donation';

  @Output() newItemValue = new EventEmitter<number>();
}
