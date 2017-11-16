import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IAppState } from '../app.store';
import { NgRedux } from 'ng2-redux';
import { ADD_PROJECT, LOAD_PROJECTS, SELECT_PROJECT } from '../actions';

@Injectable()
export class ProjectService {

  constructor(private http: HttpClient,
              private ngRedux: NgRedux<IAppState> ) { }

  getProjects() {
      this
          .http
          .get('http://localhost:8090/shlaker/project/')
          .toPromise()
          .then(data => this.ngRedux.dispatch({type: LOAD_PROJECTS, projects: data}));
  }

  getProject(projectId) {
      this
          .http
          .get(`http://localhost:8090/shlaker/project/${projectId}`)
          .toPromise()
          .then(data => this.ngRedux.dispatch({type: SELECT_PROJECT, project: data}));
  }

  createProject(project) {
      return this
          .http
          .post(`http://localhost:8090/shlaker/project/`, project)
          .toPromise()
          .then(data => this.ngRedux.dispatch({type: ADD_PROJECT, project: data}));
  }

}
