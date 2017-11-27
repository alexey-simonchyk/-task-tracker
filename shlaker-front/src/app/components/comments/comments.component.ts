import { Comment } from '../../models/comment.model';
import { Component, Input, OnInit } from '@angular/core';
import { CommentService } from '../../services/comment-service';
import { select } from 'ng2-redux';
import { Observable } from 'rxjs/Observable';
import { User } from '../../models/user.model';

@Component({
    selector: 'comments',
    templateUrl: './comments.component.html',
    styleUrls: ['./comments.component.css'],
})
export class CommentsComponent implements OnInit {

    @Input('itemId') public itemId: string;
    @Input('itemType') public itemType: string;

    @select('user') user: Observable<User>;

    @Input('comments') public comments: Comment[];
    isCollapsed: boolean = true;
    comment: Comment;

    constructor(private commentService: CommentService) {
    }

    ngOnInit() {
        this.initNewComment();
    }

    changeCollapse() {
        this.isCollapsed = !this.isCollapsed;
    }

    private initNewComment() {
        this.comment = new Comment();
        this.comment.text = '';
    }

    sendMessage() {
        if (this.comment.text !== '') {
            this.user.subscribe(user => {
                this.comment.user = user;
                switch (this.itemType) {
                    case 'project':
                        this.commentService.addCommentToProject(this.itemId, this.comment);
                        this.initNewComment();
                        break;
                    case 'task':
                        this.commentService.addCommentToTask(this.itemId, this.comment);
                        this.initNewComment();
                        break;
                }
            });
        }
    }

}
