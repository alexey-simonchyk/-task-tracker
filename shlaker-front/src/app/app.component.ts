import { UserService } from './services/user-service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { select } from 'ng2-redux';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

    @select("token") token: Observable<string>;

    constructor(private userService: UserService, private router: Router) {

    }

    ngOnInit() {
        this.userService.getUserInfo();
        this.token.subscribe(token => {
            if (!token) {
                this.router.navigate(['/login'])
            }
        })
    }
}
