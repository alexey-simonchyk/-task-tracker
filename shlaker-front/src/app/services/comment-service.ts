import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgRedux } from 'ng2-redux';
import { IAppState } from '../app.store';
import { Comment } from '../models/comment.model';
import { ADD_COMMENT_TO_PROJECT, ADD_COMMENT_TO_TASK } from '../actions';

@Injectable()
export class CommentService {

    constructor(
      private http: HttpClient,
      private ngRedux: NgRedux<IAppState>
    ) { }

    addCommentToTask(taskId: string, comment: Comment) {
        this
            .http
            .post(`http://localhost:8090/shlaker/comment/task/${taskId}`, comment)
            .toPromise()
            .then(data => this.ngRedux.dispatch({ type: ADD_COMMENT_TO_TASK, comment: data }),
                    err => console.log(err));
    }

    addCommentToProject(projectId: string, comment: Comment) {
        this
            .http
            .post(`http://localhost:8090/shlaker/comment/project/${projectId}`, comment)
            .toPromise()
            .then(data => this.ngRedux.dispatch({ type: ADD_COMMENT_TO_PROJECT, comment: data }),
                err => console.log(err));
    }

}
