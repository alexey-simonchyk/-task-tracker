import { Component, Input, OnInit } from '@angular/core';
import { Comment } from '../../../models/comment.model';

@Component({
  selector: 'task-comments',
  templateUrl: './task-comments.component.html',
  styleUrls: ['./task-comments.component.css']
})
export class TaskCommentsComponent implements OnInit {

    @Input("comments") comments: Comment[];

    constructor() { }

    ngOnInit() {
    }

}
