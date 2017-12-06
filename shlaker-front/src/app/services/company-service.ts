import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NgRedux } from 'ng2-redux';
import { IAppState } from '../app.store';
import { REMOVE_TOKEN} from '../actions';
import { Router } from '@angular/router';

@Injectable()
export class CompanyService {

    private companyEndPoint: string;
    private token: string;

    constructor(
        private http: HttpClient,
        private ngRedux: NgRedux<IAppState>,
        private router: Router
    ) {
        this.companyEndPoint = `${environment.apiUrl}/company`;
        this.ngRedux.select('token').subscribe((token: any) => {
            this.token = token;
        });
    }

    getCompanies() {
        return this
            .http
            .get(`${this.companyEndPoint}/list`)
            .toPromise()
            .then(data => data, err => {
                switch (err.status) {
                    case 401:
                        this.ngRedux.dispatch({ type: REMOVE_TOKEN });
                        break;
                    case 400:
                        this.router.navigate(['/']);
                        break;
                }
            });
    }

    private getAuthenticationHeader(): HttpHeaders {
        return new HttpHeaders({
            'Authorization': `Bearer ${this.token}`
        });
    }

}
