import { RegistrationComponent } from './components/registration/registration.component';
import { NgRedux, NgReduxModule, DevToolsExtension } from 'ng2-redux';
import {
    IAppState, INITIAL_STATE, projectReducer,
    selectedTaskReducer, selectedProjectReducer, tokenReducer, userReducer
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
import { FormsModule } from '@angular/forms';
import { AuthenticatedGuard } from './guards/authenticated-guard';


@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        RegistrationComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        NgReduxModule,
        HttpClientModule,
        FormsModule,

        MainModule,
        RouterModule.forRoot(APP_ROUTE)
    ],
    providers: [
        AuthenticatedGuard
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
    constructor(ngRedux: NgRedux<IAppState>, ngReduxDev: DevToolsExtension) {
        let enhancers = [];

        if (ngReduxDev.isEnabled()) {
            enhancers = [ ...enhancers, ngReduxDev.enhancer() ];
        }
        ngRedux.configureStore(combineReducers({
            user: userReducer,
            token: tokenReducer,
            projects: projectReducer,
            selectedTask: selectedTaskReducer,
            selectedProject: selectedProjectReducer
        }), INITIAL_STATE, [], enhancers);
    }
}
