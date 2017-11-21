import { Routes }  from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { LoginComponent } from './components/login/login.component';
import { MAIN_ROUTE } from './components/routing';



export const APP_ROUTE: Routes = [
    {
        path: '',
        component: MainComponent,
        children: MAIN_ROUTE
    },
    {
        path: 'login',
        component: LoginComponent
    }
];
