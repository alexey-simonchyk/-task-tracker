

import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { NgRedux } from 'ng2-redux';
import { IAppState } from '../app.store';

@Injectable()
export class NotAuthenticatedGuard implements CanActivate {

    constructor(private ngRedux: NgRedux<IAppState>) {

    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        return this.ngRedux.select("token").map(token => {
            return !token;
        })
    }

}
