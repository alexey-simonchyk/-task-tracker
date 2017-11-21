import { NgRedux, NgReduxModule, DevToolsExtension } from 'ng2-redux';
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
import { RouterModule } from '@angular/router';
import { APP_ROUTE } from './app.routing';
import { LoginComponent } from "./components/login/login.component";


@NgModule({
  declarations: [
    AppComponent,
      LoginComponent
  ],
  imports: [
    BrowserModule,
      BrowserAnimationsModule,
    NgReduxModule,
      HttpClientModule,

    MainModule,
      RouterModule.forRoot(APP_ROUTE)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
    constructor(ngRedux: NgRedux<IAppState>, ngReduxDev: DevToolsExtension) {
        let enhancers = [];

        if (ngReduxDev.isEnabled()) {
            enhancers = [ ...enhancers, ngReduxDev.enhancer() ];
        }
        ngRedux.configureStore(combineReducers({
            projects: projectReducer,
            selectedTask: selectedTaskReducer,
            selectedProject: selectedProjectReducer
        }), INITIAL_STATE, [], enhancers);
    }
}
