import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user-service';
import { Router } from '@angular/router';
import { CompanyService } from '../../services/company-service';
import { Company } from '../../models/company.model';

@Component({
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {

    newUser: RegistrationUser = new RegistrationUser();

    companies: Company[];
    errorMessage: string = '';
    registrateNewCompany: boolean = false;
    constructor(private userService: UserService,
                private router: Router,
                private companyService: CompanyService) { }

    ngOnInit() {
        this.companyService.getCompanies().then((data: any) => this.companies = data);
    }

    onSubmit() {
        this.errorMessage = '';
        if (this.newUser.password !== this.newUser.repeatPassword) {
            this.errorMessage = 'Passwords must be the same';
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
                    this.errorMessage = error.error;
                    this.newUser.email = '';
                    this.newUser.password = '';
                    this.newUser.repeatPassword = '';
                }
            });


    }

}

class RegistrationUser {
    email: string;
    password: string;
    repeatPassword: string;
    nick: string;
    companyName: string;
    companyId: string;
}
