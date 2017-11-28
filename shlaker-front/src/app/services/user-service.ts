import { environment } from '../../environments/environment';
import { IAppState } from '../app.store';
import { NgRedux } from 'ng2-redux';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ADD_TOKEN, REMOVE_TOKEN, ADD_USER } from '../actions';

@Injectable()
export class UserService {

    private loginEndPoint: string;
    private token: string;
    private userEndPoint: string;

    constructor(private http: HttpClient,
                private ngRedux: NgRedux<IAppState>) {
        this.userEndPoint = `${environment.apiUrl}/user`;
        this.loginEndPoint = `${environment.apiUrl}/oauth`;
        this.ngRedux.select("token").subscribe((token: any) => {
            this.token = token;
        });
    }

    login(email: string, password: string) {
        const body = `username=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}&grant_type=password`;

        return this
            .http
            .post(this.loginEndPoint + '/token', body, { headers: this.getLoginHeaders() })
            .toPromise()
            .then((res: any) => {
                if (res.access_token) {
                    this.ngRedux.dispatch({ type: ADD_TOKEN, token: res.access_token });
                }
            }, err => {
                if (err.status === 401) {
                    this.ngRedux.dispatch({ type: REMOVE_TOKEN });
                }
            });
    }

     checkToken(isAuthenticated: boolean): Promise<boolean> {
        if (!this.token) {
            return new Promise((resolve) => resolve(!isAuthenticated));
        }
        return this
            .http
            .get(`${this.loginEndPoint}/check_token?token=${this.token}`, { headers: this.getLoginHeaders() })
            .toPromise()
            .then(data => {
                return isAuthenticated;
            }, error => {
                this.ngRedux.dispatch({ type: REMOVE_TOKEN });
                return !isAuthenticated;
            });
    }

    getDevelopers() {
        if (!this.token) return;
        return this
            .http
            .get(`${this.userEndPoint}/developers`, { headers: this.getAuthenticationHeader() })
            .toPromise();
    }

    registrate(newUser) {
        return this
            .http
            .post(`${environment.apiUrl}/sign-up`, newUser)
            .toPromise();
    }

    getUserInfo() {
        if (!this.token) return;
        return this
            .http
            .get(`${environment.apiUrl}/me`, {headers: this.getAuthenticationHeader()})
            .toPromise()
            .then(data => {
                this.ngRedux.dispatch({type: ADD_USER, user: data})
            },
            err => {
                if (err.status === 401) {
                    this.ngRedux.dispatch({ type: REMOVE_TOKEN });
                }
            }
        );
    }

    getLoginHeaders() {
        return new HttpHeaders({
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': 'Basic ' + btoa(`${environment.webClientId}:${environment.webClientSecret}`)
        });
    }

    private getAuthenticationHeader(): HttpHeaders {
        return new HttpHeaders({
            'Authorization': `Bearer ${this.token}`
        });
    }

}
