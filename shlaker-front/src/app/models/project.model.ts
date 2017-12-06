import { Task } from './task.model';
import { Command } from './command.model';

export class Project {
    id: string;
    name: string;
    description: string;
    status: ProjectStatus;
    startTime: Date;
    endTime: Date;

    comments: Comment[];
    command: Command;
    tasks: Task[];
}

export enum ProjectStatus {
    DEVELOPING, COMPLETED
}
