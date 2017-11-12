import { NgModule } from '@angular/core';
import { MainComponent } from './main/main.component';
import { CommentService } from '../services/comment-service';
import { UserService } from '../services/user-service';
import { TaskService } from '../services/task-service';
import { ProjectService } from '../services/project-service';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './menu/menu.component';
import { ProjectComponent } from './project/project.component';
import { ProjectCommentsComponent } from './project/project-comments/project-comments.component';
import { ProjectTasksComponent } from './project/project-tasks/project-tasks.component';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { MAIN_ROUTE } from './routing';
import { TaskComponent } from './task/task.component';
import { TaskCommentsComponent } from './task/task-comments/task-comments.component';
import { ProjectCreateComponent } from './project/project-create/project-create.component';
import { EmptyComponent } from './main/empty/empty.component';
import { InnerTaskComponent } from './project/project-tasks/inner-task/inner-task.component';


@NgModule({
    declarations: [
        MainComponent,
        MenuComponent,
        ProjectComponent,
        ProjectCommentsComponent,
        ProjectTasksComponent,
        TaskComponent,
        TaskCommentsComponent,
        ProjectCreateComponent,
        EmptyComponent,
        InnerTaskComponent,
    ],
    imports: [
        BrowserModule,
        CommonModule,
        RouterModule.forRoot(MAIN_ROUTE)
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
