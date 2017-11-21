import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgRedux } from 'ng2-redux';
import { IAppState } from '../app.store';
import { Comment } from '../models/comment.model';
import { ADD_COMMENT_TO_PROJECT, ADD_COMMENT_TO_TASK } from '../actions';
import { environment } from '../../environments/environment';

@Injectable()
export class CommentService {

    private commentEndPoint: string;

    constructor(
      private http: HttpClient,
      private ngRedux: NgRedux<IAppState>
    ) {
        this.commentEndPoint = `${environment.apiUrl}/comment`
     }

    addCommentToTask(taskId: string, comment: Comment) {
        this
            .http
            .post(`${this.commentEndPoint}/task/${taskId}`, comment)
            .toPromise()
            .then(data => this.ngRedux.dispatch({ type: ADD_COMMENT_TO_TASK, comment: data }),
                    err => console.log(err));
    }

    addCommentToProject(projectId: string, comment: Comment) {
        this
            .http
            .post(`${this.commentEndPoint}/project/${projectId}`, comment)
            .toPromise()
            .then(data => this.ngRedux.dispatch({ type: ADD_COMMENT_TO_PROJECT, comment: data }),
                err => console.log(err));
    }

}
