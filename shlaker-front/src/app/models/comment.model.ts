import { User } from './user.model';

export class Comment {
    id: string;
    text: string;
    creationDate: Date;
    user: User;
}
