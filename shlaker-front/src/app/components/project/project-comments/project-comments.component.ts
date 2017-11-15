import { Comment } from '../../../models/comment.model';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'project-comments',
  templateUrl: './project-comments.component.html',
  styleUrls: ['./project-comments.component.css'],
})
export class ProjectCommentsComponent implements OnInit {

  @Input('comments') public comments: Comment[];
  isCollapsed: boolean = true;

  constructor() { }

  ngOnInit() {
  }

  changeCollapse() {
      this.isCollapsed = !this.isCollapsed;
  }

}
