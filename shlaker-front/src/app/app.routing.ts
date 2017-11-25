import { RegistrationComponent } from './components/registration/registration.component';
import { Routes }  from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { LoginComponent } from './components/login/login.component';
import { MAIN_ROUTE } from './components/routing';
import { AuthenticatedGuard } from './guards/authenticated-guard';



export const APP_ROUTE: Routes = [
    {
        path: '',
        component: MainComponent,
        children: MAIN_ROUTE,
        canActivate: [AuthenticatedGuard]
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'registration',
        component: RegistrationComponent
    },
    {
        path: '**',
        redirectTo: ''
    }
];
