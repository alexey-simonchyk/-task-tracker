import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgRedux } from 'ng2-redux';
import { IAppState } from '../app.store';
import { SELECT_TASK, UPDATE_TASK_STATUS } from '../actions';

@Injectable()
export class TaskService {

  constructor(
      private http: HttpClient,
      private ngRedux: NgRedux<IAppState>
  ) { }

  getTask(taskId: string) {
      this
          .http
          .get(`http://localhost:8090/shlaker/task/${taskId}`)
          .toPromise()
          .then(data => this.ngRedux.dispatch({ type: SELECT_TASK, task: data }));
  }

  updateTaskStatus(taskId: string, taskStatus: string) {
      this
          .http
          .patch(`http://localhost:8090/shlaker/task/${taskId}/status`, {status: taskStatus}, {responseType: 'text'})
          .toPromise()
          .then(res => this.ngRedux.dispatch({ type: UPDATE_TASK_STATUS, taskId: taskId, status: taskStatus}),
            err => console.log(err));
  }

}
