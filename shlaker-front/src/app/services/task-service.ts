import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgRedux } from 'ng2-redux';
import { IAppState } from '../app.store';
import { ADD_TASK, SELECT_TASK, UPDATE_TASK_STATUS } from '../actions';
import { Task } from '../models/task.model';

@Injectable()
export class TaskService {

    private taskEndpoint: string;

    constructor(
        private http: HttpClient,
        private ngRedux: NgRedux<IAppState>
    ) {
        this.taskEndpoint = `${environment.apiUrl}/task`
     }

    getTask(taskId: string) {
        return this
            .http
            .get(`${this.taskEndpoint}/${taskId}`)
            .toPromise()
            .then(data => this.ngRedux.dispatch({ type: SELECT_TASK, task: data }));
    }

    updateTaskStatus(taskId: string, taskStatus: string) {
        return this
            .http
            .patch(`${this.taskEndpoint}/${taskId}/status`, {status: taskStatus}, {responseType: 'text'})
            .toPromise()
            .then(res => this.ngRedux.dispatch({ type: UPDATE_TASK_STATUS, taskId: taskId, status: taskStatus}),
            err => console.log(err));
    }

    createTask(projectId: string, newTask: Task) {
        return this
            .http
            .post(`${this.taskEndpoint}/project/${projectId}`, newTask)
            .toPromise()
            .then(data => this.ngRedux.dispatch({ type: ADD_TASK, task: data }));
    }

}
