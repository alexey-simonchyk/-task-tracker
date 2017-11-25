import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IAppState } from '../app.store';
import { NgRedux } from 'ng2-redux';
import { ADD_PROJECT, LOAD_PROJECTS, SELECT_PROJECT } from '../actions';
import { environment } from '../../environments/environment';

@Injectable()
export class ProjectService {

    private projectEndPoint: string;
    private token: string;

    constructor(private http: HttpClient,
                private ngRedux: NgRedux<IAppState> ) {
        this.projectEndPoint = `${environment.apiUrl}/project`
        this.ngRedux.select("token").subscribe((token: any) => {
            this.token = token;
        });
    }

    getProjects() {
        this
            .http
            .get(`${this.projectEndPoint}/`, {headers: this.getAuthenticationHeader()})
            .toPromise()
            .then(data => this.ngRedux.dispatch({type: LOAD_PROJECTS, projects: data}));
    }

    getProject(projectId) {
        this
            .http
            .get(`${this.projectEndPoint}/${projectId}`, {headers: this.getAuthenticationHeader()})
            .toPromise()
            .then(data => {
                this.ngRedux.dispatch({type: SELECT_PROJECT, project: data})
            },
                err => {
                    console.log(err);
                });
    }

    createProject(project) {
        return this
            .http
            .post(`${this.projectEndPoint}/`, project, {headers: this.getAuthenticationHeader()})
            .toPromise()
            .then(data => this.ngRedux.dispatch({type: ADD_PROJECT, project: data}));
    }

    private getAuthenticationHeader(): HttpHeaders {
        return new HttpHeaders({
            'Authorization': `Bearer ${this.token}`
        });
    }

}
