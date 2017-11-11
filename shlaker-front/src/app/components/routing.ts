import { Routes }  from '@angular/router';
import { ProjectComponent } from './project/project.component';
import { TaskComponent } from './task/task.component';



export const MAIN_ROUTE: Routes = [
    {
        path: 'project/:projectId',
        component: ProjectComponent
    },
    {
        path: 'task/:taskId',
        component: TaskComponent
    }
];
