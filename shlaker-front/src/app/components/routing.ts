import { Routes }  from '@angular/router';
import { ProjectComponent } from './project/project.component';
import { TaskComponent } from './task/task.component';
import { ProjectCreateComponent } from './project/project-create/project-create.component';
import { EmptyComponent } from './main/empty/empty.component';
import { TaskCreateComponent } from './task/task-create/task-create.component';
import { ProjectDescriptionComponent } from './project/project-description/project-description.component';
import { ManagerGuard } from '../guards/manager-guard';



export const MAIN_ROUTE: Routes = [
    {
        path: '',
        component: EmptyComponent
    },
    {
        path: 'project/create',
        component: ProjectCreateComponent,
        canActivate: [ManagerGuard]
    },
    {
        path: 'project/:projectId/task/create',
        component: TaskCreateComponent
    },
    {
        path: 'project/:projectId',
        component: ProjectComponent,
        children: [
            {
                path: '',
                component: ProjectDescriptionComponent
            },
            {
                path: 'task/:taskId',
                component: TaskComponent
            }
        ]
    }
];
