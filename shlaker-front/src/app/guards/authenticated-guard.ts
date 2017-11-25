import { IAppState } from '../app.store';
import { NgRedux } from 'ng2-redux';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class AuthenticatedGuard implements CanActivate {

    constructor(private ngRedux: NgRedux<IAppState>) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        return this.ngRedux.select("token").map(token => {
            return !!token;
        });
    }

}
