import { NgModule } from '@angular/core';
import { MainComponent } from './main/main.component';
import { CommentService } from '../services/comment-service';
import { UserService } from '../services/user-service';
import { TaskService } from '../services/task-service';
import { ProjectService } from '../services/project-service';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './menu/menu.component';
import { ProjectComponent } from './project/project.component';
import { CommentsComponent } from './comments/comments.component';
import { ProjectTasksComponent } from './project/project-tasks/project-tasks.component';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { TaskComponent } from './task/task.component';
import { ProjectCreateComponent } from './project/project-create/project-create.component';
import { EmptyComponent } from './main/empty/empty.component';
import { InnerTaskComponent } from './project/project-tasks/inner-task/inner-task.component';
import { CommentComponent } from './comments/comment/comment.component';
import { FormsModule } from '@angular/forms';
import { TaskCreateComponent } from './task/task-create/task-create.component';
import { ProjectDescriptionComponent } from './project/project-description/project-description.component';


@NgModule({
    declarations: [
        MainComponent,
        MenuComponent,
        ProjectComponent,
        CommentsComponent,
        ProjectTasksComponent,
        TaskComponent,
        ProjectCreateComponent,
        EmptyComponent,
        InnerTaskComponent,
        CommentComponent,
        TaskCreateComponent,
        ProjectDescriptionComponent,
    ],
    imports: [
        BrowserModule,
        CommonModule,
        RouterModule,
        FormsModule
    ],
    exports: [
        MainComponent
    ],
    providers: [
        CommentService,
        UserService,
        TaskService,
        ProjectService
    ]
})
export class MainModule {

}
