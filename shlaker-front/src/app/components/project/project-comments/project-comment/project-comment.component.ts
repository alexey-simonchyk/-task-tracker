import { Component, Input, OnInit } from '@angular/core';
import { Comment } from '../../../../models/comment.model';

@Component({
    selector: 'project-comment',
    templateUrl: './project-comment.component.html',
    styleUrls: ['./project-comment.component.css']
})
export class ProjectCommentComponent implements OnInit {

    @Input('comment') comment: Comment;

    constructor() { }

    ngOnInit() {
    }

}
