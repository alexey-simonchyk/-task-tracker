export class Task {
    id: string;
    description: string;
    name: string;
    status: TaskStatus;
    
    startTime: Date;
    endtime: Date;
}

export enum TaskStatus {
    APPOINTED, PERFORMING, FULFILLED, VERIFIED
}