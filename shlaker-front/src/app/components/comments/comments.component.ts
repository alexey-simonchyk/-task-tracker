import { Comment } from '../../models/comment.model';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css'],
})
export class CommentsComponent implements OnInit {

  @Input('comments') public comments: Comment[];
  isCollapsed: boolean = true;

  constructor() { }

  ngOnInit() {
  }

  changeCollapse() {
      this.isCollapsed = !this.isCollapsed;
  }

}
