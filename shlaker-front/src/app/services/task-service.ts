import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NgRedux } from 'ng2-redux';
import { IAppState } from '../app.store';
import { ADD_TASK, REMOVE_TOKEN, SELECT_TASK, UPDATE_TASK_STATUS } from '../actions';
import { Task } from '../models/task.model';

@Injectable()
export class TaskService {

    private taskEndpoint: string;
    private token: string;

    constructor(
        private http: HttpClient,
        private ngRedux: NgRedux<IAppState>
    ) {
        this.taskEndpoint = `${environment.apiUrl}/task`;
        this.ngRedux.select("token").subscribe((token: any) => {
            this.token = token;
        });
     }

    getTask(taskId: string) {
        if (!this.token) return;
        return this
            .http
            .get(`${this.taskEndpoint}/${taskId}`, {headers: this.getAuthenticationHeader()})
            .toPromise()
            .then(data => {
                this.ngRedux.dispatch({ type: SELECT_TASK, task: data })
            }, err => {
                if (err.status === 401) {
                    this.ngRedux.dispatch({ type: REMOVE_TOKEN });
                }
            });
    }

    updateTaskStatus(taskId: string, taskStatus: string) {
        if (!this.token) return;
        return this
            .http
            .patch(`${this.taskEndpoint}/${taskId}/status`, {status: taskStatus}, {responseType: 'text', headers: this.getAuthenticationHeader()})
            .toPromise()
            .then(res => this.ngRedux.dispatch({ type: UPDATE_TASK_STATUS, taskId: taskId, status: taskStatus}),
                err => {
                    if (err.status === 401) {
                        this.ngRedux.dispatch({ type: REMOVE_TOKEN });
                    }
                });
    }

    createTask(projectId: string, newTask: Task) {
        if (!this.token) return;
        return this
            .http
            .post(`${this.taskEndpoint}/project/${projectId}`, newTask, {headers: this.getAuthenticationHeader()})
            .toPromise()
            .then(data => {
                this.ngRedux.dispatch({ type: ADD_TASK, task: data })
            },err => {
                if (err.status === 401) {
                    this.ngRedux.dispatch({ type: REMOVE_TOKEN });
                }
            });
    }

    private getAuthenticationHeader(): HttpHeaders {
        return new HttpHeaders({
            'Authorization': `Bearer ${this.token}`
        });
    }

}
