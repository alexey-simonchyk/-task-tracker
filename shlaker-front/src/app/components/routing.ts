import { TASK_ID, PROJECT_ID } from './routing.const';
import { Routes }  from '@angular/router';
import { ProjectComponent } from './project/project.component';
import { TaskComponent } from './task/task.component';



export const MAIN_ROUTE: Routes = [
    {
        path: `project/:${PROJECT_ID}`,
        component: ProjectComponent
    },
    {
        path: `task/:${TASK_ID}`,
        component: TaskComponent
    }
];
