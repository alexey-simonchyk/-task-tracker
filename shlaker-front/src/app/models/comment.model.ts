import { User } from './user.model';

export class Comment {
    id: string;
    text: string;
    creationTime: Date;
    user: User;
}
