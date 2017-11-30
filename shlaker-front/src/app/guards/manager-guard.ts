import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { NgRedux } from 'ng2-redux';
import { IAppState } from '../app.store';
import { Observable } from 'rxjs/Observable';
import { User } from '../models/user.model';


@Injectable()
export class ManagerGuard implements CanActivate{

    private user: User;

    constructor(private ngRedux: NgRedux<IAppState>, private router: Router) {
        this.ngRedux.select('user').subscribe((user: User) => {
            console.log(user);
            this.user = user
        });
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        /*if (this.user.role === 'manager') {
            return true;
        } else {
            this.router.navigate(['/']);
            return false;
        }*/
        return true;
        /*this.ngRedux.select('user').subscribe((user: User) => {
            if (user.role === 'manager') {
                return true;
            } else {
                this.router.navigate(['/']);
                return false;
            }
        });*/

    }

}
