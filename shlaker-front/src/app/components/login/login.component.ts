import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';

@Component({
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

    user: User = new User();

    constructor() { }

    ngOnInit() {

    }

    onSubmit() {
        console.log(this.user);
    }

}
