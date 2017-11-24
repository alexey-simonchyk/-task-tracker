import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user-service';

@Component({
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

    user: User = new User();

    constructor(private userService: UserService) { }

    ngOnInit() {

    }

    onSubmit() {
        this.userService.login(this.user.email, this.user.password);
    }

}
