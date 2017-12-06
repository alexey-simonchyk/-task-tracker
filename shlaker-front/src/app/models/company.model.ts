import { Project } from './project.model';
import { Command } from './command.model';
import { User } from './user.model';

export class Company {
    id: string;
    name: string;

    projects: Project[];
    commands: Command[];
    developers: User[];
}
