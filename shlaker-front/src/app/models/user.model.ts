import { Company } from './company.model';

export class User {
    id: string;
    email: string;
    password: string;
    nick: string;
    imageId: string;
    role: string;
    company: Company;
}
