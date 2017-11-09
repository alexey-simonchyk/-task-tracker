import { NgRedux, NgReduxModule } from 'ng2-redux';
import {
    IAppState, INITIAL_STATE, taskReducer, projectReducer,
    selectedTaskReducer, selectedProjectReducer
} from './app.store';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { combineReducers } from 'redux';
import { MainModule } from './components/main.module';
import {MainAppRout} from "./router.module";
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    NgReduxModule,
      HttpClientModule,

    MainModule,
    MainAppRout
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
    constructor(ngRedux: NgRedux<IAppState>) {
        ngRedux.configureStore(combineReducers({
            tasks: taskReducer,
            projects: projectReducer,
            selectedTask: selectedTaskReducer,
            selectedProject: selectedProjectReducer
        }), INITIAL_STATE);
    }
}
