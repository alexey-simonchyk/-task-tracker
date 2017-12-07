import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../../services/project-service';
import { Project } from '../../models/project.model';
import { NgRedux, select } from 'ng2-redux';
import { IAppState } from '../../app.store';
import { User } from '../../models/user.model';
import { Observable } from 'rxjs/Observable';

@Component({
    templateUrl: './company.component.html',
    styleUrls: ['./company.component.css'],
})
export class CompanyComponent implements OnInit {

    protected companyProjects: Project[];
    @select('user') user: Observable<User>;

    constructor(private projectService: ProjectService,
                private ngRedux: NgRedux<IAppState>) {
    }

    ngOnInit() {
        this.user.subscribe((user: User) => {
            if (user) {
                this.projectService.getAllCompanyProjects(user.company.id).then((data: Project[]) => {
                    this.companyProjects = data;
                });
            }
        });
    }

}
