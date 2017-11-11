import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgRedux } from 'ng2-redux';
import { IAppState } from '../app.store';
import { SELECT_TASK } from '../actions';

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

}
