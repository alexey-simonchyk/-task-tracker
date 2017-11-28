import { User } from './user.model';

export class Task {
    id: string;
    description: string;
    name: string;
    status: TaskStatus;
    comments: Comment[];

    startTime: Date;
    endTime: Date;
    developers: User[];
}

export enum TaskStatus {
    APPOINTED, PERFORMING, FULFILLED, VERIFIED
}
