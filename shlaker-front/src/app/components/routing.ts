import { Routes }  from '@angular/router';
import { ProjectComponent } from './project/project.component';
import { TaskComponent } from './task/task.component';
import { ProjectCreateComponent } from './project/project-create/project-create.component';
import { EmptyComponent } from './main/empty/empty.component';
import { TaskCreateComponent } from './task/task-create/task-create.component';
import { ProjectDescriptionComponent } from './project/project-description/project-description.component';



export const MAIN_ROUTE: Routes = [
    {
        path: '',
        component: EmptyComponent
    },
    {
        path: 'project/create',
        component: ProjectCreateComponent
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
    },
    {
        path: 'project/:projectId/task/create',
        component: TaskCreateComponent
    }
];
