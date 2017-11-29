import { Task } from './task.model';
import { User } from './user.model';

export class Project {
    id: string;
    name: string;
    description: string;
    status: ProjectStatus;
    startTime: Date;
    endTime: Date;

    comments: Comment[];
    developers: User[];
    tasks: Task[];
}

export enum ProjectStatus {
    DEVELOPING, COMPLETED
}
