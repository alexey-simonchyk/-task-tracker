import { User } from './user.model';

export class Project {
    id: string;
    name: string;
    description: string;
    status: string;
    startTime: Date;
    endTime: Date;

    comments: Comment[];
    developers: User[];
}