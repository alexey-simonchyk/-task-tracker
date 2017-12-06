import { User } from './user.model';
import { Project } from './project.model';

export class Command {
    id: string;
    name: string;
    developers: User[];
    projects: Project[];
 }
