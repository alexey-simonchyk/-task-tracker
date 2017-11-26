import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user-service';
import { Router } from '@angular/router';

@Component({
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {

    passwordsEquals: boolean = true;
    newUser: RegistrationUser = new RegistrationUser();
    correctEmail: boolean = true;

    constructor(private userService: UserService, private router: Router) { }

    ngOnInit() {
    }

    onSubmit() {
        if (this.newUser.password !== this.newUser.repeatPassword) {
            this.passwordsEquals = true;
            return;
        }

        this
            .userService
            .registrate(this.newUser)
            .then(data => {
                this.userService
                    .login(this.newUser.email, this.newUser.password)
                    .then(data => {
                        this.userService.getUserInfo()
                            .then(data => {
                                this.router.navigate(['/']);
                            });
                    });
            }, error => {
                if (error.status === 400) {
                    switch (error.error) {
                        case 'Such user exists':
                            this.correctEmail = false;
                            break;

                        case 'Password and repeated password not equal':
                            this.passwordsEquals = false;
                            break;
                    }
                }
            });


    }

}

class RegistrationUser {
    email: string;
    password: string;
    repeatPassword: string;
    nick: string;
}
