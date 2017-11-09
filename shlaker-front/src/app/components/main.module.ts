import { NgModule } from '@angular/core';
import { MainComponent } from './main/main.component';
import { CommentService } from '../services/comment-service';
import { UserService } from '../services/user-service';
import { TaskService } from '../services/task-service';
import { ProjectService } from '../services/project-service';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './menu/menu.component';
import { ProjectComponent } from './project/project.component';


@NgModule({
    declarations: [
        MainComponent,
        MenuComponent,
        ProjectComponent
    ],
    imports: [
        CommonModule
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
