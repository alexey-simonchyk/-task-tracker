export class Task {
    id: string;
    description: string;
    name: string;
    status: TaskStatus;
    
    startTime: Date;
    endTime: Date;
}

export enum TaskStatus {
    APPOINTED, PERFORMING, FULFILLED, VERIFIED
}