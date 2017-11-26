import { Router } from '@angular/router';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { UserService } from '../services/user-service';

@Injectable()
export class NotAuthenticatedGuard implements CanActivate {

    constructor(private userService: UserService, private router: Router) {

    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        return this.userService.checkToken(false).then(res => {
            if (res === true) {
                return true;
            } else {
                this.router.navigate(['/']);
                return false;
            }
        });
    }

}
