import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { UserService } from '../../services/user-service';
import { Component, OnInit } from '@angular/core';
import { select } from 'ng2-redux';

@Component({
    selector: 'main-component',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.css'],
})
export class MainComponent implements OnInit {

    @select("token") token: Observable<string>;

    constructor(private userService: UserService, private router: Router) { }

    ngOnInit() {
        this.userService.getUserInfo();
        this.token.subscribe(token => {
            if (!token) {
                this.router.navigate(['/login']);
            }
        });
    }

}
