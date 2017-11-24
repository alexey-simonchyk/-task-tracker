import { environment } from '../../environments/environment';
import { IAppState } from '../app.store';
import { NgRedux } from 'ng2-redux';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class UserService {

    private loginEndPoint: string;

    constructor(private http: HttpClient,
                private ngRedux: NgRedux<IAppState>) {
        this.loginEndPoint = `${environment.apiUrl}/oauth/token`
    }

    login(email: string, password: string) {
        const body = `username=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}&grant_type=password`;

        this.http.post(this.loginEndPoint, body, { headers: this.getLoginHeaders() })
            .toPromise()
            .then((res: any) => {
                console.log(res);
            }, err => console.log(err));
    }

    getLoginHeaders() {
        return new HttpHeaders({
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': 'Basic ' + btoa(`${environment.webClientId}:${environment.webClientSecret}`)
        });
    }

}
