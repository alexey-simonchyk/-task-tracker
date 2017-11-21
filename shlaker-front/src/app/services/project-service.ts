import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IAppState } from '../app.store';
import { NgRedux } from 'ng2-redux';
import { ADD_PROJECT, LOAD_PROJECTS, SELECT_PROJECT } from '../actions';
import { environment } from '../../environments/environment';

@Injectable()
export class ProjectService {

    private projectEndPoint: string;

    constructor(private http: HttpClient,
                private ngRedux: NgRedux<IAppState> ) {
        this.projectEndPoint = `${environment.apiUrl}/project`
    }

    getProjects() {
        this
            .http
            .get(`${this.projectEndPoint}/`)
            .toPromise()
            .then(data => this.ngRedux.dispatch({type: LOAD_PROJECTS, projects: data}));
    }

    getProject(projectId) {
        this
            .http
            .get(`${this.projectEndPoint}/${projectId}`)
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
            .post(`${this.projectEndPoint}/`, project)
            .toPromise()
            .then(data => this.ngRedux.dispatch({type: ADD_PROJECT, project: data}));
    }

}
