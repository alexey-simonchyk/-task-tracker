import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { UserService } from '../services/user-service';


@Injectable()
export class AuthenticatedGuard implements CanActivate {

    constructor(private userService: UserService, private router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        return this.userService.checkToken(true)
            .then(result => {
                if (result === true) {
                    return true;
                } else {
                    this.router.navigate(['/login']);
                    return false;
                }
            });
    }

}
