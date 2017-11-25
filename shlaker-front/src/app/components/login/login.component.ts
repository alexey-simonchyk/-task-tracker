import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user-service';
import { Router } from '@angular/router';

@Component({
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

    user: User = new User();

    constructor(private userService: UserService,
                private router: Router) { }

    ngOnInit() {

    }

    onSubmit() {
        this.userService
            .login(this.user.email, this.user.password)
            .then(data => {
                this.userService.getUserInfo()
                    .then(data => {
                        this.router.navigate(['/']);
                    });
            });
    }

}
