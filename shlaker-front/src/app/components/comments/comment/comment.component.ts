import { environment } from '../../../../environments/environment';
import { Component, Input, OnInit } from '@angular/core';
import { Comment } from '../../../models/comment.model';

@Component({
    selector: 'comment',
    templateUrl: './comment.component.html',
    styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

    @Input('comment') comment: Comment;
    imageEndPoint: string = `${environment.defaultImageEndPoint}`;

    constructor() { }

    ngOnInit() {
    }

}
