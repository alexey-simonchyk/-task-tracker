import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NgRedux } from 'ng2-redux';
import { IAppState } from '../app.store';
import { Comment } from '../models/comment.model';
import { ADD_COMMENT_TO_PROJECT, ADD_COMMENT_TO_TASK, REMOVE_TOKEN } from '../actions';
import { environment } from '../../environments/environment';

@Injectable()
export class CommentService {

    private commentEndPoint: string;
    private token: string;

    constructor(
      private http: HttpClient,
      private ngRedux: NgRedux<IAppState>
    ) {
        this.commentEndPoint = `${environment.apiUrl}/comment`;
        this.ngRedux.select('token').subscribe((token: any) => {
            this.token = token;
        });
     }

    addCommentToTask(taskId: string, comment: Comment) {
        if (!this.token) {
          return;
        }
        this
            .http
            .post(`${this.commentEndPoint}/task/${taskId}`, comment, {headers: this.getAuthenticationHeader()})
            .toPromise()
            .then(data => {
                this.ngRedux.dispatch({ type: ADD_COMMENT_TO_TASK, comment: data });
            }, err => {
                if (err.status === 401) {
                    this.ngRedux.dispatch({ type: REMOVE_TOKEN });
                }
            });
    }

    addCommentToProject(projectId: string, comment: Comment) {
        if (!this.token) {
          return;
        }
        this
            .http
            .post(`${this.commentEndPoint}/project/${projectId}`, comment, {headers: this.getAuthenticationHeader()})
            .toPromise()
            .then(data => {
                this.ngRedux.dispatch({ type: ADD_COMMENT_TO_PROJECT, comment: data })
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
