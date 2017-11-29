import { RegistrationComponent } from './components/registration/registration.component';
import { Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { LoginComponent } from './components/login/login.component';
import { MAIN_ROUTE } from './components/routing';
import { AuthenticatedGuard } from './guards/authenticated-guard';
import { NotAuthenticatedGuard } from './guards/not-authenticated-guard';



export const APP_ROUTE: Routes = [
    {
        path: '',
        component: MainComponent,
        children: MAIN_ROUTE,
        canActivate: [AuthenticatedGuard]
    },
    {
        path: 'login',
        component: LoginComponent,
        canActivate: [NotAuthenticatedGuard]
    },
    {
        path: 'registration',
        component: RegistrationComponent,
        canActivate: [NotAuthenticatedGuard]
    },
    {
        path: '**',
        redirectTo: ''
    }
];
