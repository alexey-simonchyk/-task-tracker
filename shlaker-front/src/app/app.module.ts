import { NgRedux, NgReduxModule } from 'ng2-redux';
import {
    IAppState, INITIAL_STATE, projectReducer,
    selectedTaskReducer, selectedProjectReducer
} from './app.store';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { combineReducers } from 'redux';
import { MainModule } from './components/main.module';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
      BrowserAnimationsModule,
    NgReduxModule,
      HttpClientModule,

    MainModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
    constructor(ngRedux: NgRedux<IAppState>) {
        ngRedux.configureStore(combineReducers({
            projects: projectReducer,
            selectedTask: selectedTaskReducer,
            selectedProject: selectedProjectReducer
        }), INITIAL_STATE);
    }
}
