import { IAppState } from './app.store';
import { Component } from '@angular/core';
import { NgRedux } from 'ng2-redux/lib/components/ng-redux';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(private ngRedux: NgRedux<IAppState>) {

  }
}
